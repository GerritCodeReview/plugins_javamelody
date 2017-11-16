// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.javamelody;

import com.google.gerrit.httpd.AllRequestFilter;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.bull.javamelody.MonitoringFilter;

import java.io.IOException;
import java.util.StringJoiner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
class GerritMonitoringFilter extends AllRequestFilter {
  private final JavamelodyFilter monitoring;
  private final CapabilityChecker capabilityChecker;

  @Inject
  GerritMonitoringFilter(JavamelodyFilter monitoring,
      CapabilityChecker capabilityChecker) {
    this.monitoring = monitoring;
    this.capabilityChecker = capabilityChecker;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    if (!(request instanceof HttpServletRequest)
        || !(response instanceof HttpServletResponse)) {
      chain.doFilter(request, response);
      return;
    }

    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    if (canMonitor(httpRequest)) {
      monitoring.doFilter(request, response, chain);
    } else {
      httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
          "Forbidden access");
    }
  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    monitoring.init(config);
  }

  @Override
  public void destroy() {
    monitoring.destroy();
  }

  private boolean canMonitor(HttpServletRequest httpRequest) {
    if (httpRequest.getRequestURI().equals(monitoring
        .getJavamelodyUrl(httpRequest))) {
      return capabilityChecker.canMonitor();
    }
    return true;
  }

  static class JavamelodyFilter extends MonitoringFilter {
    private static String HTTP_TRANSFORM_PATTERN = "http-transform-pattern";
    private static String GLOBAL_HTTP_TRANSFORM_PATTERN = "javamelody." + HTTP_TRANSFORM_PATTERN;
    static String GERRIT_GROUPING = new StringJoiner("|")
        .add("(\\w+)~(.+)~I([0-9a-f]{40})") //change id triplet
        .add("[0-9a-f]{64}") // Long SHA for LFS
        .add("[0-9a-f]{40}") // SHA-1
        .add("[0-9A-F]{32}") // GWT cache ID
        .add("(?<=files/)(.+)/") //review files part
        .add("(?<=/projects/)(.+)/") //project name
        .add("(?<=/accounts/)(.+)/") // account id
        .add("(.+)(?=/git-upload-pack)") // Git fetch/clone
        .add("(.+)(?=/git-receive-pack)") // Git push
        .add("(.+)(?=/info/)") // Git and LFS operations
        .add("\\d+") // various ids e.g. change id
        .toString();

    @Override
    public void init(FilterConfig config) throws ServletException {
      if (isHttpTransformPatternrUndefined(config)) {
        System.setProperty(GLOBAL_HTTP_TRANSFORM_PATTERN, GERRIT_GROUPING);
      }
      super.init(config);
    }

    public String getJavamelodyUrl(HttpServletRequest httpRequest) {
      return getMonitoringUrl(httpRequest);
    }

    private boolean isHttpTransformPatternrUndefined(FilterConfig config) {
      return System.getProperty(GLOBAL_HTTP_TRANSFORM_PATTERN) == null
          && config.getServletContext().getInitParameter(GLOBAL_HTTP_TRANSFORM_PATTERN) == null
          && config.getInitParameter(HTTP_TRANSFORM_PATTERN) == null;
     }
  }
}

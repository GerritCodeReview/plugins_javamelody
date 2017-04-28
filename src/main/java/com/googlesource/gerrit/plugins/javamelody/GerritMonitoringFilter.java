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
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.bull.javamelody.MonitoringFilter;

@Singleton
class GerritMonitoringFilter extends AllRequestFilter {
  private final JavamelodyFilter monitoring;
  private final CapabilityChecker capabilityChecker;

  @Inject
  GerritMonitoringFilter(JavamelodyFilter monitoring, CapabilityChecker capabilityChecker) {
    this.monitoring = monitoring;
    this.capabilityChecker = capabilityChecker;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
      chain.doFilter(request, response);
      return;
    }

    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    if (canMonitor(httpRequest)) {
      monitoring.doFilter(request, response, chain);
    } else {
      httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden access");
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
    if (httpRequest.getRequestURI().equals(monitoring.getJavamelodyUrl(httpRequest))) {
      return capabilityChecker.canMonitor();
    }
    return true;
  }

  static class JavamelodyFilter extends MonitoringFilter {
    public String getJavamelodyUrl(HttpServletRequest httpRequest) {
      return getMonitoringUrl(httpRequest);
    }
  }
}

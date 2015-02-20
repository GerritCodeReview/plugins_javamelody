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

import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.httpd.AllRequestFilter;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.account.CapabilityControl;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.bull.javamelody.MonitoringFilter;

import java.io.IOException;

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
  private final Provider<CurrentUser> userProvider;
  private final String pluginName;

  @Inject
  GerritMonitoringFilter(JavamelodyFilter monitoring,
      Provider<CurrentUser> userProvider,
      @PluginName String pluginName) {
    this.monitoring = monitoring;
    this.userProvider = userProvider;
    this.pluginName = pluginName;
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
      if (userProvider.get().isIdentifiedUser()) {
        CapabilityControl ctl = userProvider.get().getCapabilities();
        return ctl.canAdministrateServer()
            || ctl.canPerform(String.format("%s-%s",
               pluginName, MonitoringCapability.ID));
      }
      return false;
    }
    return true;
  }

  static class JavamelodyFilter extends MonitoringFilter {
    public String getJavamelodyUrl(HttpServletRequest httpRequest) {
      return getMonitoringUrl(httpRequest);
    }
  }
}

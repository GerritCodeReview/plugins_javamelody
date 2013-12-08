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

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bull.javamelody.MonitoringFilter;

import com.google.gerrit.httpd.AllRequestFilter;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
class GerritMonitoringFilter extends AllRequestFilter {
  private final MonitoringFilter monitoring;

  @Inject
  GerritMonitoringFilter(MonitoringFilter monitoring) {
    this.monitoring = monitoring;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    monitoring.doFilter(request, response, chain);
  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    monitoring.init(config);
  }

  @Override
  public void destroy() {
    monitoring.destroy();
  }
}

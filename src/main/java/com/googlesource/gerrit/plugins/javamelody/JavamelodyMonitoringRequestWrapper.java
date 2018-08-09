// Copyright (C) 2018 The Android Open Source Project
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class JavamelodyMonitoringRequestWrapper extends HttpServletRequestWrapper {
  private final String monitoringUri;

  public JavamelodyMonitoringRequestWrapper(HttpServletRequest request, String monitoringUri) {
    super(request);
    this.monitoringUri = monitoringUri;
  }

  @Override
  public String getRequestURI() {
    String requestUri = super.getRequestURI();
    if (requestUri.endsWith(monitoringUri)) {
      return monitoringUri;
    }
    return requestUri;
  }
}

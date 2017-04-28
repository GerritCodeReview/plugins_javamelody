// Copyright (C) 2014 The Android Open Source Project
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

import com.google.common.collect.Lists;
import com.google.gerrit.extensions.client.MenuItem;
import com.google.gerrit.extensions.webui.TopMenu;
import com.google.inject.Inject;
import java.util.Collections;
import java.util.List;

public class MonitoringTopMenu implements TopMenu {
  private final List<MenuEntry> menuEntries = Lists.newArrayList();

  @Inject
  public MonitoringTopMenu(CapabilityChecker capabilityChecker) {
    if (capabilityChecker.canMonitor()) {
      menuEntries.add(
          new MenuEntry(
              "Monitoring", Collections.singletonList(new MenuItem("JavaMelody", "monitoring"))));
    }
  }

  @Override
  public List<MenuEntry> getEntries() {
    return menuEntries;
  }
}

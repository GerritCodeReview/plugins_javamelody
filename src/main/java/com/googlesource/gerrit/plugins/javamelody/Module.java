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

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.extensions.config.CapabilityDefinition;
import com.google.gerrit.extensions.registration.DynamicSet;
import com.google.gerrit.extensions.webui.TopMenu;
import com.google.gerrit.server.config.PluginConfig;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.gerrit.server.config.SitePaths;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Module extends AbstractModule {

  private final PluginConfig cfg;
  private final String pluginName;
  private final Path TEMPORARY_DIRECTORY = Paths.get(System.getProperty("java.io.tmpdir"));
  private final Path tmpDir;

  @Inject
  public Module(PluginConfigFactory cfgFactory, @PluginName String pluginName,
      SitePaths site) {
    this.cfg = cfgFactory.getFromGerritConfig(pluginName);
    this.tmpDir = site.tmp_dir;
    this.pluginName = pluginName;
  }

  @Override
  protected void configure() {
    bind(CapabilityDefinition.class)
        .annotatedWith(Exports.named(MonitoringCapability.ID))
        .to(MonitoringCapability.class);
    if (cfg.getBoolean("allowTopMenu", true)) {
      DynamicSet.bind(binder(), TopMenu.class).to(MonitoringTopMenu.class);
    }

    // default to old path for javamelody storage-directory if it exists
    // otherwise prefer the site tmp location as it is likely persistant.
    final Path directory = TEMPORARY_DIRECTORY.resolve(pluginName);
    final Path storage_directory;
    if (Files.exists(directory) && Files.isDirectory(directory)) {
      storage_directory = directory;
    } else {
      storage_directory = tmpDir.resolve(pluginName);
    }

    // avoid overriding if explicitly set by user via system properties
    if (System.getProperty(pluginName + ".storage-directory") == null) {
      System.setProperty(pluginName + ".storage-directory",
        cfg.getString("storage_directory", storage_directory.toString()));
    }
  }
}

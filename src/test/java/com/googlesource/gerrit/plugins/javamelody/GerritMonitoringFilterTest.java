// Copyright (C) 2017 The Android Open Source Project
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

import static com.google.common.truth.Truth.assertWithMessage;

import java.util.regex.Pattern;
import org.junit.Test;

public class GerritMonitoringFilterTest {
  private static final Pattern GROUPING_PATTERN =
      Pattern.compile(
          GerritMonitoringFilter.JavamelodyFilter.GERRIT_GROUPING,
          // The following flags were added to have as close to javamelody env as possible.
          // In [1] javamelody it adds them to pattern before compiling.
          // [1]
          // https://github.com/javamelody/javamelody/blob/3863b31a86c0df04f4063615f72fc6117aecf909/javamelody-core/src/main/java/net/bull/javamelody/FilterContext.java#L209
          Pattern.MULTILINE | Pattern.DOTALL);

  @Test
  public void testGroupingPattern() throws Exception {
    String dollar = "\\$";
    String result =
        GROUPING_PATTERN
            .matcher(
                "/plugins/lfs/content/default/a55dc67374da05f4e1eb736f8ad2147d0a6964ed41d28462fd7e2fe86bea78ed")
            .replaceAll(dollar);
    assertWithMessage("Long SHA (LFS) grouping")
        .that(result)
        .isEqualTo("/plugins/lfs/content/default/$");

    result =
        GROUPING_PATTERN
            .matcher("/changes/?q=05810c7a315ba6c52c150c237d84d05b2ebc3086&n=")
            .replaceAll(dollar);
    assertWithMessage("SHA-1 grouping").that(result).isEqualTo("/changes/?q=$&n=");

    result =
        GROUPING_PATTERN
            .matcher("/gerrit_ui/gwt/chrome/7CF1DE6EF2AABFEFAE4D469A16D60071.cache.css")
            .replaceAll(dollar);
    assertWithMessage("GWT cache grouping")
        .that(result)
        .isEqualTo("/gerrit_ui/gwt/chrome/$.cache.css");

    result = GROUPING_PATTERN.matcher("/files/test_dir%2Funder_dir.file/diff").replaceAll(dollar);
    assertWithMessage("Grouping by file").that(result).isEqualTo("/files/$/diff");

    result = GROUPING_PATTERN.matcher("/projects/plugins%2Fjavamelody/config").replaceAll(dollar);
    assertWithMessage("Grouping by projects").that(result).isEqualTo("/projects/$/config");

    result = GROUPING_PATTERN.matcher("/accounts/self/avatar.change.url").replaceAll(dollar);
    assertWithMessage("Grouping by account")
        .that(result)
        .isEqualTo("/accounts/$/avatar.change.url");

    result = GROUPING_PATTERN.matcher("/test_repo/git-upload-pack").replaceAll(dollar);
    assertWithMessage("Grouping git-upload-pack").that(result).isEqualTo("$/git-upload-pack");

    result = GROUPING_PATTERN.matcher("/test_repo/git-receive-pack").replaceAll(dollar);
    assertWithMessage("Grouping git-receive-pack").that(result).isEqualTo("$/git-receive-pack");

    result = GROUPING_PATTERN.matcher("/test_repo.git/info/lfs/locks").replaceAll(dollar);
    assertWithMessage("Grouping Git and LFS operations").that(result).isEqualTo("$/info/lfs/locks");

    result = GROUPING_PATTERN.matcher("/changes/30/revisions/1/commit").replaceAll(dollar);
    assertWithMessage("Grouping numbers").that(result).isEqualTo("/changes/$/revisions/$/commit");

    // grouping multiple patterns in one input
    result =
        GROUPING_PATTERN
            .matcher(
                "/changes/30/revisions/05810c7a315ba6c52c150c237d84d05b2ebc3086/files/test_dir%2Funder_dir.file/diff")
            .replaceAll(dollar);
    assertWithMessage("Grouping by number, SHA-1 and file")
        .that(result)
        .isEqualTo("/changes/$/revisions/$/files/$/diff");

    result =
        GROUPING_PATTERN
            .matcher(
                "/test_repo.git/info/lfs/locks/8020e4344e49a6928e03b6db69ead86624ffb4aeb1477a7d741a5e2fee9544cd/unlock")
            .replaceAll(dollar);
    assertWithMessage("Grouping LFS with Long SHA")
        .that(result)
        .isEqualTo("$/info/lfs/locks/$/unlock");
  }
}

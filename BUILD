load(
    "//tools/bzl:plugin.bzl",
    "gerrit_plugin",
    "PLUGIN_DEPS",
    "PLUGIN_TEST_DEPS",
)

gerrit_plugin(
    name = "javamelody",
    srcs = glob(
      ["src/main/java/**/*.java"],
      exclude = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    ),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Gerrit-PluginName: javamelody",
        "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
        "Implementation-Title: Javamelody plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
    ],
    deps = [
        "@javamelody_lib//jar",
        "@jrobin_lib//jar",
    ],
)

gerrit_plugin(
    name = "javamelody-nodep",
    srcs = glob(
      ["src/main/java/**/*.java"],
      exclude = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    ),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Gerrit-PluginName: javamelody",
        "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
        "Implementation-Title: Javamelody plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
    ],
    deps = [
        "@javamelody_lib//jar:neverlink",
        "@jrobin_lib//jar:neverlink",
    ],
)

java_binary(
  name = "javamelody-datasource-interceptor",
  main_class = "Dummy",
  runtime_deps = [":javamelody-datasource-interceptor-lib"],
)

java_library(
    name = 'javamelody-datasource-interceptor-lib',
    srcs = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    deps = [
        "@javamelody_lib//jar:neverlink",
        "@jrobin_lib//jar:neverlink",
    ] + PLUGIN_DEPS,
)

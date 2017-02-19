load("//tools/bzl:plugin.bzl", "gerrit_plugin")

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
    provided_deps = [
        "@javamelody_lib//jar",
        "@jrobin_lib//jar",
    ],
)

java_library(
    name = 'classpath',
    deps = [
        ":javamelody-nodep__plugin",
        ":javamelody__plugin",
    ],
)

java_binary(
    name = 'javamelody-deps',
    deps = [
          "@javamelody_lib//jar",
          "@jrobin_lib//jar",
    ],
)

java_binary(
    name = 'javamelody-datasource-interceptor',
    deps = [":javamelody-datasource-interceptor-lib"],
)

java_library(
    name = 'javamelody-datasource-interceptor-lib',
    srcs = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    provided_deps = [
        "@javamelody_lib//jar",
        "@jrobin_lib//jar",
    ] + PLUGIN_DEPS,
)

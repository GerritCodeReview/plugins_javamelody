load("//tools/bzl:plugin.bzl", "gerrit_plugin", "PLUGIN_DEPS")

gerrit_plugin(
    name = "javamelody",
    srcs = glob(
        ["src/main/java/**/*.java"],
        exclude = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    ),
    manifest_entries = [
        "Gerrit-PluginName: javamelody",
        "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
        "Implementation-Title: Javamelody plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = ["@javamelody_lib//jar:neverlink"],
)

java_binary(
    name = "javamelody-deps",
    main_class = "Dummy",
    runtime_deps = [":javamelody-datasource-interceptor-lib"],
)

java_library(
    name = "javamelody-datasource-interceptor-lib",
    srcs = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    deps = [
        ":plugin_api_neverlink",
        "@javamelody_lib//jar",
        "@jrobin_lib//jar",
    ],
    visibility = ["//visibility:public"],
)

java_library(
    name = "plugin_api_neverlink",
    neverlink = 1,
    exports = PLUGIN_DEPS,
)

# java_library(
#     name = "javamelody__plugin_test_deps",
#     visibility = ["//visibility:public"],
#     exports = ["@javamelody_lib//jar"],
# )

load("//tools/bzl:junit.bzl", "junit_tests")
load(
    "//tools/bzl:plugin.bzl",
    "gerrit_plugin",
    "PLUGIN_DEPS",
    "PLUGIN_DEPS_NEVERLINK",
    "PLUGIN_TEST_DEPS",
)

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
    deps = ["@javamelody-core//jar:neverlink"],
)

java_binary(
    name = "javamelody-deps",
    main_class = "Dummy",
    runtime_deps = [":javamelody-datasource-interceptor-lib"],
)

java_library(
    name = "javamelody-datasource-interceptor-lib",
    srcs = ["src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java"],
    visibility = ["//visibility:public"],
    deps = PLUGIN_DEPS_NEVERLINK + [
        "@javamelody-core//jar",
        "@jrobin//jar",
    ],
)

junit_tests(
    name = "javamelody_tests",
    srcs = glob(["src/test/java/**/*.java"]),
    tags = ["javamelody"],
    deps = [
        ":javamelody__plugin_test_deps",
    ],
)

java_library(
    name = "javamelody__plugin_test_deps",
    testonly = 1,
    visibility = ["//visibility:public"],
    exports = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [
        ":javamelody__plugin",
        "@javamelody-core//jar",
    ],
)

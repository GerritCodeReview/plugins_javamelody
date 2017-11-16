load("//tools/bzl:classpath.bzl", "classpath_collector")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS", "PLUGIN_TEST_DEPS")

classpath_collector(
    name = "main_classpath_collect",
    testonly = 1,
    deps = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [
        "//:javamelody-datasource-interceptor-lib",
    ],
)

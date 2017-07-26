load("//tools/bzl:classpath.bzl", "classpath_collector")
load("//tools/bzl:plugin.bzl", "PLUGIN_DEPS")

java_library(
    name = "classpath",
    testonly = 1,
    runtime_deps = [
        "//:javamelody-datasource-interceptor-lib",
    ],
)

classpath_collector(
    name = "main_classpath_collect",
    testonly = 1,
    deps = PLUGIN_DEPS + [
        "//:javamelody-datasource-interceptor-lib",
    ],
)

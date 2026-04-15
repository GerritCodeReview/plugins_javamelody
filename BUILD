load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_dependency_tests",
    "gerrit_plugin_tests",
)

gerrit_plugin(
    name = "javamelody",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: javamelody",
        "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
        "Implementation-Title: Javamelody plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "@javamelody_plugin_deps//:net_bull_javamelody_javamelody_core",
        "@javamelody_plugin_deps//:org_jrobin_jrobin",
    ],
)

gerrit_plugin_tests(
    name = "javamelody_tests",
    srcs = glob(["src/test/java/**/*.java"]),
    tags = ["javamelody"],
    deps = [
        ":javamelody__plugin",
        "@javamelody_plugin_deps//:net_bull_javamelody_javamelody_core",
        "@javamelody_plugin_deps//:org_jrobin_jrobin",
    ],
)

gerrit_plugin_dependency_tests(plugin = "javamelody")

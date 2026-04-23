load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin_tests_with_ext_deps",
    "gerrit_plugin_with_ext_deps",
)

EXT_DEPS = [
    "net.bull.javamelody:javamelody.core",
    "org.jrobin:jrobin",
]

gerrit_plugin_with_ext_deps(
    srcs = glob(["src/main/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    manifest_entries = [
        "Gerrit-PluginName: javamelody",
        "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
        "Implementation-Title: Javamelody plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
    ],
    plugin = "javamelody",
    resources = glob(["src/main/resources/**/*"]),
)

gerrit_plugin_tests_with_ext_deps(
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    plugin = "javamelody",
)

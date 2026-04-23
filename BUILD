load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_tests",
)

EXT_DEP = ["net.bull.javamelody:javamelody.core"]

PLUGIN = "javamelody"

gerrit_plugin(
    srcs = glob(["src/main/java/**/*.java"]),
    ext_deps = ["org.jrobin:jrobin"] + EXT_DEP,
    manifest_entries = [
        "Gerrit-PluginName: javamelody",
        "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
        "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
        "Implementation-Title: Javamelody plugin",
        "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
    ],
    plugin = PLUGIN,
    resources = glob(["src/main/resources/**/*"]),
)

gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = EXT_DEP,
    plugin = PLUGIN,
)

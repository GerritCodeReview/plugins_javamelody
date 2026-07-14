load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_tests",
)

EXT_DEPS = [
    "net.bull.javamelody:javamelody.core",
    "org.jrobin:jrobin",
]

PLUGIN = "javamelody"

SRCS = glob(["src/main/java/**/*.java"])

RESOURCES = glob(["src/main/resources/**/*"])

MANIFEST_ENTRIES = [
    "Gerrit-PluginName: javamelody",
    "Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module",
    "Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule",
    "Implementation-Title: Javamelody plugin",
    "Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody",
]

# A plain jakarta.servlet plugin: compiled against the jakarta Gerrit
# plugin API and the javamelody-core 2.x (jakarta) line. The
# javax-to-jakarta flavour machinery is retired; javax consumers stay on
# the last javax-era release.
gerrit_plugin(
    srcs = SRCS,
    ext_deps = EXT_DEPS,
    manifest_entries = MANIFEST_ENTRIES,
    plugin = PLUGIN,
    resources = RESOURCES,
)

gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    plugin = PLUGIN,
)

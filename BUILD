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

gerrit_plugin(
    srcs = SRCS,
    ext_deps = EXT_DEPS,
    manifest_entries = MANIFEST_ENTRIES,
    plugin = PLUGIN,
    resources = RESOURCES,
)

# EE10 (jakarta.servlet) flavour. The shared bazlets `flavour = "ee10"` rewrites
# the plugin's javax.servlet imports to jakarta.servlet, injects
# `Gerrit-Flavour: ee10`, compiles against the jakarta plugin API
# (in-tree: //plugins:plugin-lib-neverlink), and wraps the target in a
# flavour=ee10 transition -- so building :javamelody-ee10 self-selects the
# jakarta config with no command-line flag. External deps resolve from the
# jakarta javamelody-core 2.x repo, which is induced from the target name:
# ext_repo defaults to "javamelody-ee10_plugin_deps". Build with:
#   bazelisk build //plugins/javamelody:javamelody-ee10
# (or both flavours at once: `bazelisk build //plugins/javamelody:javamelody //plugins/javamelody:javamelody-ee10`).
# `dir_name = "javamelody"` keeps stamping/versioning shared with the default jar.
gerrit_plugin(
    name = "javamelody-ee10",
    srcs = SRCS,
    dir_name = "javamelody",
    ext_deps = EXT_DEPS,
    flavour = "ee10",
    manifest_entries = MANIFEST_ENTRIES,
    resources = RESOURCES,
)

gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    plugin = PLUGIN,
)

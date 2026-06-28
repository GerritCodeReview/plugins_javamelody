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

# The default (javax.servlet) jar. With the ee11 twin below, the plugin is
# explicitly flavour-managed: `flavour = "ee8"` stamps `Gerrit-Flavour: ee8`
# (equivalent to the unmarked jar for the loader) and guards the target to
# the ee8 configuration, so the ee11 wildcard pass skips the javax side --
# and its dependency tests, via incompatibility propagation -- instead of
# failing. Both wildcard passes stay green:
#   bazelisk test plugins/javamelody/...
#   bazelisk test --@com_googlesource_gerrit_bazlets//flags:flavour=ee11 \
#       plugins/javamelody/...
gerrit_plugin(
    srcs = SRCS,
    ext_deps = EXT_DEPS,
    flavour = "ee8",
    manifest_entries = MANIFEST_ENTRIES,
    plugin = PLUGIN,
    resources = RESOURCES,
)

# EE11 (jakarta.servlet) flavour. The shared bazlets `flavour = "ee11"` rewrites
# the plugin's javax.servlet imports to jakarta.servlet, injects
# `Gerrit-Flavour: ee11`, compiles against the jakarta plugin API
# (in-tree: //plugins:plugin-lib-neverlink), and wraps the target in a
# flavour=ee11 transition -- so building :javamelody-ee11 self-selects the
# jakarta config with no command-line flag. External deps resolve from the
# jakarta javamelody-core 2.x repo, which is induced from the target name:
# ext_repo defaults to "javamelody-ee11_plugin_deps". Build with:
#   bazelisk build //plugins/javamelody:javamelody-ee11
# (or both flavours at once: `bazelisk build //plugins/javamelody:javamelody //plugins/javamelody:javamelody-ee11`).
# `dir_name = "javamelody"` keeps stamping/versioning shared with the default jar.
gerrit_plugin(
    name = "javamelody-ee11",
    srcs = SRCS,
    dir_name = "javamelody",
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    manifest_entries = MANIFEST_ENTRIES,
    resources = RESOURCES,
)

gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    flavour = "ee8",
    plugin = PLUGIN,
)

# EE11 test twin: the same canonical test sources through the shared
# `to_jakarta` transform, executed against :javamelody-ee11__plugin, the
# jakarta plugin API and the javamelody-core 2.x repo (ext_repo defaults to
# "javamelody-ee11_plugin_deps"). A test target cannot self-transition the
# flavour, so each flavour's tests are guarded to their configuration: the
# plain wildcard pass runs the ee8 tests and skips this twin; the ee11
# wildcard pass runs this twin and skips the ee8 side.
gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    plugin = "javamelody-ee11",
)

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

# The plugin sources are jakarta.servlet-canonical. The default (EE8) jar
# is the generated backward bridge, the shape JGit and the migrated core
# plugins (gitiles, plugin-manager) pioneered: the shared bazlets `to_javax`
# transform rewrites the canonical sources jakarta->javax and compiles them
# against the javax plugin API and the javamelody-core 1.x (javax) line.
# `flavour = "ee8"` stamps `Gerrit-Flavour: ee8` (loader-equivalent to the
# previously unmarked jar) and guards the target to the ee8 configuration,
# so the ee11 wildcard pass skips the javax side -- and its dependency
# tests, via incompatibility propagation -- instead of failing. Both
# wildcard passes stay green:
#   bazelisk test plugins/javamelody/...
#   bazelisk test --@com_googlesource_gerrit_bazlets//flags:flavour=ee11 \
#       plugins/javamelody/...
gerrit_plugin(
    srcs = SRCS,
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee8",
    manifest_entries = MANIFEST_ENTRIES,
    plugin = PLUGIN,
    resources = RESOURCES,
)

# EE11 (jakarta.servlet) flavour: compiles the canonical sources directly
# (no transform), injects `Gerrit-Flavour: ee11`, compiles against the
# jakarta plugin API (in-tree: //plugins:plugin-lib-neverlink), and wraps
# the target in a flavour=ee11 transition -- so building :javamelody-ee11
# self-selects the jakarta config with no command-line flag. External deps
# resolve from the jakarta javamelody-core 2.x repo, which is induced from
# the target name: ext_repo defaults to "javamelody-ee11_plugin_deps".
# `dir_name = "javamelody"` keeps stamping/versioning shared with the
# default jar.
gerrit_plugin(
    name = "javamelody-ee11",
    srcs = SRCS,
    canonical = "jakarta",
    dir_name = "javamelody",
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    manifest_entries = MANIFEST_ENTRIES,
    resources = RESOURCES,
)

# EE8 tests: the canonical test sources through the `to_javax` transform,
# executed against the generated :javamelody__plugin and javamelody-core
# 1.x, guarded to the ee8 configuration.
gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee8",
    plugin = PLUGIN,
)

# EE11 tests: the canonical test sources compiled directly, executed
# against :javamelody-ee11__plugin, the jakarta plugin API and the
# javamelody-core 2.x repo (ext_repo defaults to
# "javamelody-ee11_plugin_deps"). A test target cannot self-transition the
# flavour, so each flavour's tests are guarded to their configuration: the
# plain wildcard pass runs the ee8 tests and skips this twin; the ee11
# wildcard pass runs this twin and skips the ee8 side.
gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    plugin = "javamelody-ee11",
)

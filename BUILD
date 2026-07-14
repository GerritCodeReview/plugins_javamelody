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

# The plugin sources are jakarta.servlet-canonical. The unsuffixed jar is
# the EE11 default: it compiles the canonical sources directly, stamps
# `Gerrit-Flavour: ee11`, compiles against the jakarta plugin API and the
# javamelody-core 2.x (jakarta) line, and self-selects the jakarta
# configuration. Its external deps resolve from the unsuffixed repo
# (ext_repo defaults to "javamelody_plugin_deps"), which carries the 2.x
# line since the flip -- unsuffixed means jakarta, mirroring the Maven
# naming scheme. Each flavour's targets are guarded to their
# configuration, so both wildcard passes stay green:
#   bazelisk test plugins/javamelody/...
#   bazelisk test --@com_googlesource_gerrit_bazlets//flags:flavour=ee11 \
#       plugins/javamelody/...
gerrit_plugin(
    srcs = SRCS,
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    manifest_entries = MANIFEST_ENTRIES,
    plugin = PLUGIN,
    resources = RESOURCES,
)

# The -ee8 jar is the legacy flavour: generated through the shared bazlets
# `to_javax` transform, stamping `Gerrit-Flavour: ee8` and compiling
# against the javax plugin API and the javamelody-core 1.x (javax) line
# from the repo induced from the target name (ext_repo defaults to
# "javamelody-ee8_plugin_deps"). Guarded to the ee8 configuration.
# `dir_name = "javamelody"` keeps stamping/versioning shared with the
# default jar.
gerrit_plugin(
    name = "javamelody-ee8",
    srcs = SRCS,
    canonical = "jakarta",
    dir_name = "javamelody",
    ext_deps = EXT_DEPS,
    flavour = "ee8",
    manifest_entries = MANIFEST_ENTRIES,
    resources = RESOURCES,
)

# EE11 tests for the unsuffixed jar: the canonical test sources compiled
# directly, executed against :javamelody__plugin, the jakarta plugin API
# and the javamelody-core 2.x repo. A test target cannot self-transition
# the flavour, so each flavour's tests are guarded to their configuration
# and run under the matching wildcard pass.
gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    plugin = PLUGIN,
)

# EE8 tests for the legacy jar: the canonical test sources through the
# `to_javax` transform, executed against the generated
# :javamelody-ee8__plugin and javamelody-core 1.x, guarded to the ee8
# configuration.
gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee8",
    plugin = "javamelody-ee8",
)

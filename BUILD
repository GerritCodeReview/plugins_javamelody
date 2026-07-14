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

# Tests: the canonical test sources compiled directly, executed against
# :javamelody__plugin, the jakarta plugin API and the javamelody-core 2.x
# repo, guarded to the ee11 configuration -- the only flavour.
gerrit_plugin_tests(
    srcs = glob(["src/test/java/**/*.java"]),
    canonical = "jakarta",
    ext_deps = EXT_DEPS,
    flavour = "ee11",
    plugin = PLUGIN,
)

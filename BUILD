load(
    "@com_googlesource_gerrit_bazlets//:gerrit_plugin.bzl",
    "gerrit_plugin",
    "gerrit_plugin_tests",
)
load(
    "@com_googlesource_gerrit_bazlets//tools:in_gerrit_tree.bzl",
    "in_gerrit_tree_enabled",
)
load(
    "@com_googlesource_gerrit_bazlets//tools:runtime_jars_allowlist.bzl",
    "runtime_jars_allowlist_test",
)
load(
    "@com_googlesource_gerrit_bazlets//tools:runtime_jars_overlap.bzl",
    "runtime_jars_overlap_test",
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

runtime_jars_allowlist_test(
    name = "check_javamelody_third_party_runtime_jars",
    allowlist = ":javamelody_third_party_runtime_jars.allowlist.txt",
    hint = ":check_javamelody_third_party_runtime_jars_manifest",
    target = ":javamelody__plugin",
)

runtime_jars_overlap_test(
    name = "javamelody_no_overlap_with_gerrit",
    against = "//:release.war.jars.txt",
    hint = "Exclude overlaps via maven.install(excluded_artifacts=[...]) and re-run this test.",
    target = ":javamelody__plugin",
    target_compatible_with = in_gerrit_tree_enabled(),
)

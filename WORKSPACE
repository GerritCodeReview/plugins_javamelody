workspace(name = "javamelody")

load("//:bazlets.bzl", "load_bazlets")

load_bazlets(
    commit = "bd8db0cf3057397bcf7287c28cc93886e663989b",
    #local_path = "/home/<user>/projects/bazlets",
)

load(
    "@com_googlesource_gerrit_bazlets//:gerrit_api.bzl",
    "gerrit_api",
)

# Load release Plugin API
gerrit_api()

# Load snapshot Plugin API
#gerrit_api(version = "3.1.xy-SNAPSHOT")

load("//:external_plugin_deps.bzl", "external_plugin_deps")

external_plugin_deps()

load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
    maven_jar(
        name = "javamelody-core",
        artifact = "net.bull.javamelody:javamelody-core:1.90.0",
        sha1 = "b769cc9be4a7b95ea7901c573ebc37e84cbe8525",
    )

    maven_jar(
        name = "jrobin",
        artifact = "org.jrobin:jrobin:1.5.9",
        sha1 = "bd9a84484c67de930fa841f23cd6a93108b05cd0",
    )

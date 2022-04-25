load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
    maven_jar(
        name = "javamelody-core",
        artifact = "net.bull.javamelody:javamelody-core:1.91.0",
        sha1 = "e708e7850f3a9929c287bc4cd670551ed3af572f",
    )

    maven_jar(
        name = "jrobin",
        artifact = "org.jrobin:jrobin:1.5.9",
        sha1 = "bd9a84484c67de930fa841f23cd6a93108b05cd0",
    )

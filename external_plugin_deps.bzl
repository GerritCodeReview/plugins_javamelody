load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
    maven_jar(
        name = "javamelody-core",
        artifact = "net.bull.javamelody:javamelody-core:1.99.3",
        sha1 = "2b45a9bf8bd1ec63905bf061671e960f099b86dd",
    )

    maven_jar(
        name = "jrobin",
        artifact = "org.jrobin:jrobin:1.5.9",
        sha1 = "bd9a84484c67de930fa841f23cd6a93108b05cd0",
    )

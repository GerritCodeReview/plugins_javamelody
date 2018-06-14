load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'javamelody_lib',
    artifact = 'net.bull.javamelody:javamelody-core:1.72.0',
    sha1 = '199beaab8db0abb45b9c3bad58bf2659d2f96499',
  )

  maven_jar(
    name = 'jrobin_lib',
    artifact = 'org.jrobin:jrobin:1.5.9',
    sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  )

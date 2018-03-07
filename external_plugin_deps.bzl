load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'javamelody_lib',
    artifact = 'net.bull.javamelody:javamelody-core:1.71.0',
    sha1 = '21b25741016ec135b7294fbd3cb5f41ace83b9ab',
  )

  maven_jar(
    name = 'jrobin_lib',
    artifact = 'org.jrobin:jrobin:1.5.9',
    sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  )

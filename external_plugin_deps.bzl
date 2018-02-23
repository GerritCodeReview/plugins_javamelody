load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  javamelody()

def javamelody():
  maven_jar(
    name = 'javamelody_lib',
    artifact = 'net.bull.javamelody:javamelody-core:1.70.0',
    sha1 = '929d487a8858ce4cf57a2dc0a3d1c2d59ad491a9',
  )

  maven_jar(
    name = 'jrobin_lib',
    artifact = 'org.jrobin:jrobin:1.5.9',
    sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  )

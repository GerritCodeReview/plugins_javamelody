load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'javamelody_lib',
    artifact = 'net.bull.javamelody:javamelody-core:1.67.0',
    sha1 = '7553233d944dd3096deb3af3d54f8a4d3c237b20',
  )

  maven_jar(
    name = 'jrobin_lib',
    artifact = 'org.jrobin:jrobin:1.5.9',
    sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  )

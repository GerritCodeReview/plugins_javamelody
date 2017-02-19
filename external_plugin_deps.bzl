load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'javamelody_lib',
    artifact = 'net.bull.javamelody:javamelody-core:1.62.0',
    sha1 = 'f1ee3fe3a023d07cd071dbd424a1b1d7bafbc59a',
  )

  maven_jar(
    name = 'jrobin_lib',
    artifact = 'org.jrobin:jrobin:1.5.9',
    sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  )

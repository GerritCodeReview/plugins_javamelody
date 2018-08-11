load("//tools/bzl:maven_jar.bzl", "maven_jar")
load("@bazel_tools//tools/build_defs/repo:java.bzl", "java_import_external")

def external_plugin_deps():
  java_import_external(
    name = "javamelody-core",
    jar_sha256 = "eb2806497676da54e257840626b64e8e53121fe2f3599457f7748e381918b2c1",
    jar_urls = [
      "https://github.com/davido/javamelody/releases/download/javamelody-core-1.73.2/javamelody-core-1.73.2.jar",
    ],
    licenses = ["notice"],
    neverlink = True,
    # This should just work, fixed in:
    # https://github.com/bazelbuild/bazel/pull/5864
    #generated_linkable_rule_name = "linkable-javamelody-core",
    # Woraround before the above PR is merged and released
    extra_build_file_content = "\n".join([
          "java_import(",
          "    name = \"linkable-javamelody-core\",",
          "    jars = [\"javamelody-core-1.73.2.jar\"],",
          ")",
    ]),
  )

  maven_jar(
    name = 'jrobin',
    artifact = 'org.jrobin:jrobin:1.5.9',
    sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  )

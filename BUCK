include_defs('//bucklets/gerrit_plugin.bucklet')
include_defs('//bucklets/maven_jar.bucklet')

INTERCEPTOR = ['src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java']
SRCS = glob(['src/main/java/**/*.java'], excludes = INTERCEPTOR)
RSRC = glob(['src/main/resources/**/*'])
DEPS = [
  ':javamelody-lib',
  ':jrobin-lib',
]

manifest_entries = [
  'Gerrit-PluginName: javamelody',
  'Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module',
  'Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule',
  'Implementation-Title: Javamelody plugin',
  'Implementation-URL: https://gerrit-review.googlesource.com/#/admin/projects/plugins/javamelody',
]

gerrit_plugin(
  name = 'javamelody',
  srcs = SRCS,
  resources = RSRC,
  manifest_entries = manifest_entries,
  deps = DEPS,
)

gerrit_plugin(
  name = 'javamelody-nodep',
  srcs = SRCS,
  resources = RSRC,
  manifest_entries = manifest_entries,
  provided_deps = DEPS,
)

java_library(
  name = 'classpath',
  deps = [
    ':javamelody-nodep__plugin',
    ':javamelody__plugin',
  ],
)

java_binary(
  name = 'javamelody-deps',
  deps = DEPS,
)

java_binary(
  name = 'javamelody-datasource-interceptor',
  deps = [':javamelody-datasource-interceptor-lib'],
)

java_library(
  name = 'javamelody-datasource-interceptor-lib',
  srcs = INTERCEPTOR,
  provided_deps = DEPS + ['//lib/gerrit:extension-api'],
)

zip_file(
  name = 'all',
  srcs = [
    ':javamelody',
    ':javamelody-datasource-interceptor',
    ':javamelody-deps',
    ':javamelody-nodep',
  ],
)

maven_jar(
  name = 'javamelody-lib',
  id = 'net.bull.javamelody:javamelody-core:1.56.0',
  sha1 = '0ae378effb19d2ad2db6f352ed91548f338b7c46',
  license = 'DO_NOT_DISTRIBUTE',
)

maven_jar(
  name = 'jrobin-lib',
  id = 'org.jrobin:jrobin:1.5.9',
  sha1 = 'bd9a84484c67de930fa841f23cd6a93108b05cd0',
  license = 'DO_NOT_DISTRIBUTE',
)

INTERCEPTOR = ['src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java']
SRCS = glob(['src/main/java/**/*.java'], excludes = INTERCEPTOR)
RSRC = glob(['src/main/resources/**/*'])
DEPS = [
  '//plugins/javamelody/lib:javamelody',
  '//plugins/javamelody/lib:jrobin',
]

manifest_entries = [
  'Gerrit-PluginName: javamelody',
  'Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module',
  'Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule',
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
  provided_deps = DEPS + ['//gerrit-extension-api:lib'],
)

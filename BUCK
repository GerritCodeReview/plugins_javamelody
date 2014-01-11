SRCS = glob(
  ['src/main/java/**/*.java'],
  excludes = ['src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java']
)

manifest_entries = [
  'Gerrit-PluginName: javamelody',
  'Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module',
  'Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule',
]

gerrit_plugin(
  name = 'javamelody',
  srcs = SRCS,
  resources = glob(['src/main/resources/**/*']),
  manifest_entries = manifest_entries,
  deps = [
    '//plugins/javamelody/lib:javamelody',
    '//plugins/javamelody/lib:jrobin',
  ],
  compile_deps = [':javamelody-datasource-interceptor'],
)

gerrit_plugin(
  name = 'javamelody-nodep',
  srcs = SRCS,
  resources = glob(['src/main/resources/**/*']),
  manifest_entries = manifest_entries,
  compile_deps = [
    '//plugins/javamelody/lib:javamelody',
    '//plugins/javamelody/lib:jrobin',
    ':javamelody-datasource-interceptor'
  ],
)

java_binary(
  name = 'javamelody-datasource-interceptor',
  deps = [':javamelody-datasource-interceptor-lib'],
)

java_library2(
  name = 'javamelody-datasource-interceptor-lib',
  srcs = ['src/main/java/com/googlesource/gerrit/plugins/javamelody/MonitoringDataSourceInterceptor.java'],
  compile_deps = [
    '//gerrit-extension-api:lib',
    '//plugins/javamelody/lib:javamelody',
  ]
)
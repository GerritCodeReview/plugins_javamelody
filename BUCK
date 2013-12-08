gerrit_plugin(
  name = 'javamelody',
  srcs = glob(['src/main/java/**/*.java']),
  resources = glob(['src/main/resources/**/*']),
  manifest_entries = [
    'Gerrit-PluginName: javamelody',
    'Gerrit-Module: com.googlesource.gerrit.plugins.javamelody.Module',
    'Gerrit-HttpModule: com.googlesource.gerrit.plugins.javamelody.HttpModule',
  ],
  deps = [
    '//plugins/javamelody/lib:javamelody',
    '//plugins/javamelody/lib:jrobin',
  ],
)

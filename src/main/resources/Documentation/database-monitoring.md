Database-Monitoring
===================

JavaMelody supports out of the box JNDI DataSource in Web container (Tomcat).
Because Gerrit instantiates its data source on its own, JavaMelody cannot
intercept it and therefore no SQL statistic reports can be gathered.

To overcome that problem a data source proxy must be installed.

Datasource interceptor JAR (that creates the data source proxy) must be
available in the bootstrap classpath for Gerrit core to load it.

Add the following line to `$gerrit_site/etc/gerrit.config` under `database` section:

```
dataSourceInterceptorClass = com.googlesource.gerrit.plugins.javamelody.MonitoringDataSourceInterceptor
```

Compile the plugin:

```
bazel build plugins/javamelody:javamelody
```

Compile the datasource interceptor artifact:

```
bazel build plugins/javamelody:javamelody-datasource_deploy.jar
```

Deploy the datasource-interceptor artifact to `$gerrit_site/lib`:

```
cp bazel-bin/plugins/javamelody/javamelody-datasource_deploy.jar `$gerrit_site/lib`
```

Deploy the plugin:

```
cp bazel-genfiles/plugins/javamelody/javamelody.jar `$gerrit_site/plugins`
```

Run Gerrit@Jetty and enjoy SQL statistics, a lá:

http://i.imgur.com/8EyAA9u.png

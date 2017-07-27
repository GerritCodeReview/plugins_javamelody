Database-Monitoring
===================

JavaMelody supports out of the box JNDI DataSource in Web container (Tomcat).
Because Gerrit instantiates its data source on its own, JavaMelody can not
intercept it and therefore no SQL statistic reports can be gathered.

To overcome that problem a data source proxy must be installed.

Datasource interceptor JAR (that creates the data source proxy) must be
available in the bootstrap classpath for Gerrit core to load it. Moreover,
because the interceptor depends on javamelody core library, it must be
provided in the bootstrap classpath too. In this case the plugin must
not contain the javamelody core library (shaded jar).

Thus the javamelody dependencies must not be packaged in the plugin itself.

Add the following line to `$gerrit_site/etc/gerrit.config` under `database` section:

```
dataSourceInterceptorClass = com.googlesource.gerrit.plugins.javamelody.MonitoringDataSourceInterceptor
```

Compile the plugin according to the instructions in the [build](build.md) page.

Deploy the plugin dependencies with datasource-interceptor to `$gerrit_site/lib`:

```
cp bazel-bin/plugins/javamelody/javamelody-deps_deploy.jar `$gerrit_site/lib`
```

Deploy the plugin without dependencies:

```
cp bazel-genfiles/plugins/javamelody/javamelody.jar `$gerrit_site/plugins`
```

Run Gerrit@Jetty and enjoy SQL statistics, a lá:

http://i.imgur.com/8EyAA9u.png

Database-Monitoring
===================

JavaMelody only supports JNDI DataSource in Web container (Tomcat).
Because Gerrit instantiates its data source on its own, JavaMelody can not
intercept it and therefore no SQL statistic reports can be gathered.

To overcome that problem a data source proxy must be installed.

JavaMelody JARs (that creates the data source proxy) must be
available in the bootstrap classpath for Gerrit core to load it.

Thus the javamelody dependencies must not be packaged in the plugin itself.

Add the following line to gerrit.config:

```
dataSourceInterceptorClass = com.googlesource.gerrit.plugins.javamelody.MonitoringDataSourceInterceptor
```

Compile the plugin without dependencies:

```
buck build plugins/javamelody:javamelody-nodep
```

Copy the datasource-interceptor to $gerrit_site/libs:

```
cp buck-out/gen/plugins/javamelody/javamelody-datasource-interceptor.jar $gerrit_site/libs
```

Copy the dependencies manually to $gerrit_site/libs:

```
cp
~/.gerritcodereview/buck-cache/jrobin-1.5.9.jar-bd9a84484c67de930fa841f23cd6a93108b05cd0
$gerrit_site/lib/jrobin.jar
cp
~/.gerritcodereview/buck-cache/javamelody-core-1.48.0.jar-4c573306061019430a735d9d58f93639f4a0ff0b
$gerrit_site/lib/javamelody.jar

```

Deploy plugin without dependencies:

```
cp buck-out/gen/plugins/javamelody/javamelody-nodep.jar $gerrit_site/plugins
```

Run Gerrit@Jetty and enjoy SQL statistics, a lá:

http://i.imgur.com/8EyAA9u.png

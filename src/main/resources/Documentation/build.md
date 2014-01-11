Build
=====

This plugin is built with Buck.
Clone or link this plugin to the plugins directory of the Gerrit tree
and issue the command:

```
  buck build plugins/javamelody:javamelody
```

If [database interception](database-monitoring.html) should be activated,
then the following targets must be used:

```
  buck build plugins/javamelody:javamelody-nodep
  buck build plugins/javamelody:javamelody-deps
  buck build plugins/javamelody:javamelody-datasource-interceptor
```

The output is created in

```
  buck-out/gen/plugins/javamelody/javamelody.jar
```

This project can be imported into the Eclipse IDE:

```
  ./tools/eclipse/project.py
```

More information about Buck can be found in the [Gerrit
documentation](../../../Documentation/dev-buck.html).

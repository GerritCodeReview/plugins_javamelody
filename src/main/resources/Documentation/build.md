Build
=====

This plugin is built with Buck.
Clone or link this plugin to the plugins directory of Gerrit tree
and issue the command:

```
  buck build plugins/javamelody:javamelody
```

The output is created in

```
  buck-out/gen/plugins/javamelody/javamelody.jar
```

This project can be imported into the Eclipse IDE:

```
  ./tools/eclipse/project.py
```

More invormation about Buck is described in the [Gerrit
documentation](../../../Documentation/dev-buck.html).

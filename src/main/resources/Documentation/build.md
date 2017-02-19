Build
=====

This plugin is built with Bazel.

Clone (or link) this plugin to the `plugins` directory of Gerrit's source tree.

Put the external dependency Bazel build file into the Gerrit /plugins directory,
replacing the existing empty one.

```
  cd gerrit/plugins
  rm external_plugin_deps.bzl
  ln -s javamelody/external_plugin_deps.bzl .
```

Then issue

```
  bazel build plugins/javamelody:javamelody
```

If [database interception](database-monitoring.md) should be activated,
then the following targets must be used instead:

```
  bazel build plugins/javamelody:javamelody-nodep
  bazel build plugins/javamelody:javamelody-deps
  bazel build plugins/javamelody:javamelody-datasource-interceptor
```

The output from the former target is:

```
  bazel-genfiles/plugins/javamelody/javamelody.jar
```

The output from the latter targets are:

```
  bazel-genfiles/plugins/javamelody/javamelody-nodep.jar
  bazel-genfiles/plugins/javamelody/javamelody-deps.jar
  bazel-genfiles/plugins/javamelody/javamelody-datasource-interceptor.jar
```

This project can be imported into the Eclipse IDE.
Add the plugin name to the `CUSTOM_PLUGINS` set in
Gerrit core in `tools/bzl/plugins.bzl`, and execute:

```
  ./tools/eclipse/project.py
```

More information about Buck can be found in the [Gerrit
documentation](../../../Documentation/dev-bazel.html).

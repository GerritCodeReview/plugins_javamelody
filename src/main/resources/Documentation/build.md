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

The output is created in:

```
  bazel-genfiles/plugins/javamelody/javamelody.jar
```

Note that adding [database interception](database-monitoring.md) capabilities to
the plugin requires building a separate artifact. To do that, issue this command:

```
  bazel build plugins/javamelody:javamelody-datasource_deploy.jar
```

The output is created in:

```
  bazel-bin/plugins/javamelody/javamelody-datasource_deploy.jar
```

To deploy the plugin with database interception capabilities follow the
instructions in [database monitoring](database-monitoring.md) page.

This project can be imported into the Eclipse IDE.
Add the plugin name to the `CUSTOM_PLUGINS` set in
Gerrit core in `tools/bzl/plugins.bzl`, and execute:

```
  ./tools/eclipse/project.py
```

More information about Bazel can be found in the [Gerrit
documentation](../../../Documentation/dev-bazel.html).

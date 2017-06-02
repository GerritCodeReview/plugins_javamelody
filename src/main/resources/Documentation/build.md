Build
=====

This plugin is built with Bazel. Currently, only in Gerrit tree build is
supported.

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

Note, that the plugin dependencies with [database interception](database-monitoring.md)
are built separately. To do that, issue this command:

```
  bazel build plugins/javamelody:javamelody-deps_deploy.jar
```

The output from the former target is:

```
  bazel-genfiles/plugins/javamelody/javamelody.jar
```

The output from the latter targets are:

```
  bazel-bin/plugins/javamelody/javamelody-deps_deploy.jar
```

This project can be imported into the Eclipse IDE.
Add the plugin name to the `CUSTOM_PLUGINS` and to the
`CUSTOM_PLUGINS_TEST_DEPS` set in Gerrit core in
`tools/bzl/plugins.bzl`, and execute:

```
  ./tools/eclipse/project.py
```

More information about Bazel can be found in the [Gerrit
documentation](../../../Documentation/dev-bazel.html).

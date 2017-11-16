Build
=====

This plugin is built with Bazel and two build modes are supported:

* Standalone
* In Gerrit tree.

Standalone build mode is recommended, as this mode doesn't require local Gerrit
tree to exist.

## Build standalone

To build the plugin, issue the following command:

```
  bazel build @PLUGIN@.jar
```

The output is created in

```
  bazel-genfiles/@PLUGIN@.jar
```

Then to build the plugin dependencies execute:

```
bazel build @PLUGIN@-deps_deploy.jar
```

The output is created in

```
  bazel-bin/@PLUGIN@-deps_deploy.jar
```

Given the @PLUGIN@.jar does not contains the plugin dependencies, the @PLUGIN@.jar
should be deployed to the `gerrit_site/plugins` folder as usual and the bazel-bin/@PLUGIN@-deps_deploy.jar should be deployed to the `gerrit_site/lib`
folder:

```
cp bazel-genfiles/plugins/@PLUGIN@/@PLUGIN@.jar `$gerrit_site/plugins`
cp bazel-bin/plugins/@PLUGIN@/@PLUGIN@-deps_deploy.jar `$gerrit_site/lib`
```

To enable the plugin database interception capabilities follow the instructions
in [database monitoring](database-monitoring.md) page.

To package the plugin sources run:

```
  bazel build lib@PLUGIN@__plugin-src.jar
```

The output is created in:

```
  bazel-bin/lib@PLUGIN@__plugin-src.jar
```

To execute the tests run:

```
  bazel test @PLUGIN@_tests
```

This project can be imported into the Eclipse IDE:

```
  ./tools/eclipse/project.sh
```

## Build in Gerrit tree

Clone or link this plugin to the plugins directory of Gerrit's
source tree. Put the external dependency Bazel build file into
the Gerrit /plugins directory, replacing the existing empty one.

```
  cd gerrit/plugins
  rm external_plugin_deps.bzl
  ln -s javamelody/external_plugin_deps.bzl .
```

Then issue

```
  bazel build plugins/javamelody:javamelody
```

Note, that the plugin dependencies with the [database interception](database-monitoring.md)
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
To execute the tests run:

```
  bazel test plugins/@PLUGIN@:@PLUGIN@_tests
```

This project can be imported into the Eclipse IDE.
Add the plugin name to the `CUSTOM_PLUGINS` set in
Gerrit core in `tools/bzl/plugins.bzl`, and execute:

```
  ./tools/eclipse/project.py
```

More information about Bazel can be found in the [Gerrit
documentation](../../../Documentation/dev-bazel.html).

[Back to @PLUGIN@ documentation index][index]

[index]: index.html

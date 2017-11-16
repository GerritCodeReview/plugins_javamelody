Configuration
=============

The configuration of the @PLUGIN@ plugin is done in the `gerrit.config`
file.

```
  [plugin "@PLUGIN@"]
    allowTopMenu = false
```

Note that [JavaMelody Optional Parameters](https://github.com/javamelody/javamelody/wiki/UserGuide#6-optional-parameters)
can be provided to `gerrit.config` as part of `container.javaOptions`
parameter e.g.:

```
  [container]
    javaOptions = -Djavamelody.log=true
```

<a id="allowTopMenu">
`plugin.@PLUGIN@.allowTopMenu`
:	Whether it is allowed to show top menu in Gerrit UI.
	By default true.


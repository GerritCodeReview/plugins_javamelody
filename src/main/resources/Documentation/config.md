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
: Whether it is allowed to show top menu in Gerrit UI.
  By default true.

<a id="prometheusToken">
`plugin.@PLUGIN@.prometheusToken`
: Bearer token for allowing Prometheus to query JavaMelody data
  through its scraper.
  See <a href="https://github.com/javamelody/javamelody/wiki/UserGuideAdvanced#exposing-metrics-to-prometheus">JavaMelody-Prometheus</a>
  configuration for more details on how to configure the integration with
  Prometheus.
  By default undefined.

<a id="storage-directory">
`plugin.@PLUGIN@.storage-directory`
: The directory in which to store data files. Javamelody, by default,
  stores data under `/tmp/javamelody` directory but it gets wiped out
  upon system restart. Therefore for fresh install (or when it was just
  wiped out after restart) it is defaulted to `GERRIT_SITE/data/@PLUGIN@`.
  Note that, in order to preserve existing configuration through
  `-Djavamelody.storage-directory` value from `container.javaOptions`,
  it has lower priority than `plugin.@PLUGIN@.storage-directory` but higher
  than default.

<a id="http-transform-pattern">
`plugin.@PLUGIN@.http-transform-pattern`
: Grouping pattern for HTTP requests statistics. Without groupping pattern
  javamelody treats each HTTP requests as distinctive therefore it is not
  possible to deduct overal site performance and what is more, on busy server,
  it may lead to
  [issue with too many open RRD files](https://stackoverflow.com/questions/19147762/javamelody-crashing-the-server-with-thousands-of-rrd-files).
  If not specified this parameter takes the value that allows javamelody to
  group all REST and GIT HTTP (incuding LFS) requests over project, account,
  SHA-1, Long Object Id (LFS), account etc. ids. However one can provide own
  regexp to cover for instance plugin extensions.
  Note that, in order to preserve existing configuration through
  `-Djavamelody.http-transform-pattern` value from `container.javaOptions`,
  it has lower priority than `plugin.@PLUGIN@.http-transform-pattern` but higher
  than default.


This plugin allows to monitor the Gerrit server.

This plugin integrates [JavaMelody](https://github.com/javamelody/javamelody) in
Gerrit in order to retrieve live instrumentation data from Gerrit.

To access the monitoring URL a user must be a member of a group that is
granted the 'Javamelody Monitoring' capability (provided by this plugin)
or the 'Administrate Server' capability.

It adds top menu item "Monitoring" to access java melody page.

Gerrit's own metrics can be accessed from within Javamelody monitoring page:
`<gerrit_host_url>/monitoring?part=mbeans`. The pre-requisite for this is
[JMX metrics plugin](https://gerrit.googlesource.com/plugins/metrics-reporter-jmx)
that must be also installed.

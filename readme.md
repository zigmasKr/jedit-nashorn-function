<h1>NashornWraper plugin</h1>

<h2>Overview</h2>
<p>The NashornWrapper plugin is a demo plugin showing how to render a Nashorn script
into a jEdit plugin. The principal functionality of the plugin is programmed in some
Nashorn script's function, and then this function is included into the plugin's Java code via
JavaScriptShell.runScriptInvocable(...).</p>

<h2>Installation of the plugin</h2>
<p>The JAR file NashornFuncWrapper.jar should be copied the usual place of jEdit's
plugins. This place can be [jEdit application directory]/jars or
[jEdit settings directory]/jars. The accompanying Nashorn script script_for_demo_plugin.js
is found in the repository subfolder /scripts. This script should be copied into
[jEdit settings directory]/scripts. This should be enough to start the plugin.
</p>

<h2>Configuring the plugin</h2>
<p>The plugin can be configured via the plugin options. The plugin's accompanying Nashorn
script contains two functions: demoBorder and demoScroller. In the options panel,
each of them can be chosen to be invoked. The behaviour of the function demoScroller depends
on the presence of the argument. In the options panel, another place of the script
can be selected.
</p>

<h2>Extending the plugin</h2>
<p>To extend or change the functionality of the plugin, one has to include other (useful)
functions into the script script_for_demo_plugin.js, or write another (useful) Nashorn script,
and then to configure the plugin in the options panel accordingly.
</p>

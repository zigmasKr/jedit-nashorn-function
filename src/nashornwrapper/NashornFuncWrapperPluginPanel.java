
package nashornwrapper;

import java.io.*;
import java.nio.file.*;
import javax.script.*;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.gjt.sp.util.Log;
import org.gjt.sp.jedit.*;
import console.*;
import javascriptshell.*;

public class NashornFuncWrapperPluginPanel extends JPanel {

	private String scriptNameDefault = "nashorn_func_demo.js";
	private String functionDefault = "demoBorder";
	private String argDefault = "";
	private String dockableTitleDefault = jEdit.getProperty("nashornfuncwrapper.title");

	private String scriptPath;
	private String function;
	private String arg;
	private String dockableTitle;

	private class NashornFuncWrapperPanel
			extends JPanel
			implements WindowConstants {
		//
		private JavaScriptShell.RetVal scriptEval = null;

		private NashornFuncWrapperPanel() {
			// https://findusages.com/search/jdk.nashorn.api.scripting.NashornScriptEngine/invokeFunction$2

			// script path
			String scriptRootDefault = jEdit.getSettingsDirectory() + FileSystems.getDefault().getSeparator() +
				"scripts";

			String pathDefault = scriptRootDefault + FileSystems.getDefault().getSeparator() +
				scriptNameDefault;
			String pathSelected = jEdit.getProperty("options.nashorn-wrapper.script-path");

			if (pathSelected != null && !pathSelected.trim().isEmpty()) {
				scriptPath = pathSelected;
			}
			else {
				scriptPath = pathDefault;
				jEdit.setProperty("options.nashorn-wrapper.script-path", pathDefault);
			}

			View currentView = jEdit.getActiveView();
			JPanel panel = null;
			boolean scriptFound = false;

			if (Files.exists(Paths.get(scriptPath), new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
				scriptFound = true;
			}
			else {
				Log.log(Log.DEBUG, NashornFuncWrapperPanel.class, "Script file |" + scriptPath + "| is NOT found");
				Macros.error(currentView, "Script file " + scriptPath + " is not found.\nPlease review the plugin options.\n");
			}

			// function name
			String functionSelected = jEdit.getProperty("options.nashorn-wrapper.function-name");
			if (functionSelected != null && !functionSelected.trim().isEmpty()) {
				function = functionSelected;
			} else {
				function = functionDefault;
				jEdit.setProperty("options.nashorn-wrapper.function-name", function);
			}

			// function arg
			String argSelected = jEdit.getProperty("options.nashorn-wrapper.function-arg");
			if (argSelected != null) {
				arg = argSelected;
			} else {
				arg = argDefault;
				jEdit.setProperty("options.nashorn-wrapper.function-arg", arg);
			}

			// dockable window title
			String dockableTitleSelected = jEdit.getProperty("options.nashorn-wrapper.dockable-title");
			if (dockableTitleSelected != null && !dockableTitleSelected.trim().isEmpty()) {
				dockableTitle = dockableTitleSelected;
			} else {
				dockableTitle = dockableTitleDefault;
				jEdit.setProperty("options.nashorn-wrapper.dockable-title", dockableTitle);
			}

			//
			if (scriptFound) {
				scriptEval = (JavaScriptShell.RetVal) JavaScriptShell.runScriptInvocable(scriptPath, currentView, function, arg);
				//Log.log(Log.DEBUG, NashornFuncWrapperPanel.class, "scriptEval: " + scriptEval.toString());
				Log.log(Log.DEBUG, NashornFuncWrapperPanel.class, "scriptEval.retVal: " + scriptEval.retVal.toString());
				panel = (JPanel) scriptEval.retVal;
				add(panel);
			}
		}

		/**
		* Sets the closeOperation attribute of this Panel object.
		* Borrowed from Calculator plugin (CalculatorPanel.java).
		*
		* @param operation  The new closeOperation value,
		* one of DISPOSE_ON_CLOSE, DO_NOTHING_ON_CLOSE, EXIT_ON_CLOSE, or HIDE_ON_CLOSE.
		*/
		private int close_operation = EXIT_ON_CLOSE;

		public void setCloseOperation(int operation) {
			switch (operation) {
				case DISPOSE_ON_CLOSE:
				case DO_NOTHING_ON_CLOSE:
				case EXIT_ON_CLOSE:
				case HIDE_ON_CLOSE:
					break;
				default:
					throw new IllegalArgumentException("Invalid close operation, see javax.swing.WindowConstants.");
			}
			close_operation = operation;
		}

		/**
		* Gets the closeOperation attribute of the ButtonDemoPanel object
		*
		* @return   The closeOperation value,
		* one of DISPOSE_ON_CLOSE, DO_NOTHING_ON_CLOSE, EXIT_ON_CLOSE, or HIDE_ON_CLOSE.
		*/
		public int getCloseOperation() {
			return close_operation;
		}
	}

	public NashornFuncWrapperPluginPanel() {
		NashornFuncWrapperPanel nhfuncPanel = new NashornFuncWrapperPanel();
		nhfuncPanel.setCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//setSize(jnhdemoPanel.getSize());
		add(nhfuncPanel);
	}
}

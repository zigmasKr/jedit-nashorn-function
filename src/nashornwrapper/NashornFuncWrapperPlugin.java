
package nashornwrapper;

import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.EditPlugin;


public class NashornFuncWrapperPlugin extends EditPlugin {
	/** Name for plugin manager */
	public final static String NAME = "Nashorn Wrapper";

	static {
		String dir = jEdit.getSettingsDirectory();
		if (dir == null)
			dir = System.getProperty("user.home");
		System.setProperty("nashornfuncwrapper.home", dir);
	}
}



package nashornfunct;

import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.EditPlugin;


public class NashornFunctionPlugin extends EditPlugin {
	/** Name for plugin manager */
	public final static String NAME = "Nashorn Function";

	static {
		String dir = jEdit.getSettingsDirectory();
		if (dir == null)
			dir = System.getProperty("user.home");
		System.setProperty("nashornfunction.home", dir);
	}
}


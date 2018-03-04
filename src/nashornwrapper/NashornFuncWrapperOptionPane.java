package nashornwrapper;
/**
 * @author Zigmantas Kryzius
 * TODO: comment
 */
//{{{ Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.gjt.sp.jedit.AbstractOptionPane;
import org.gjt.sp.jedit.browser.VFSBrowser;
import org.gjt.sp.jedit.browser.VFSFileChooserDialog;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.gui.*;
//}}}
public class NashornFuncWrapperOptionPane extends AbstractOptionPane {

	private HistoryTextField scriptPath, functionName, functionArg, dockableTitle;

	public NashornFuncWrapperOptionPane() {
		super("nashorn-wrapper");
	}

	protected void _init() {

		// component A
		scriptPath = new HistoryTextField("nashorn.wrapper.path");
		scriptPath.getModel().setMax(6);
		scriptPath.setText(jEdit.getProperty("options.nashorn-wrapper.script-path"));
		JButton browse = new JButton(jEdit.getProperty("vfs.browser.browse.label"));
		browse.addActionListener(new BrowseHandler());

		JPanel componentA = new JPanel();
		componentA.setLayout(new BoxLayout(componentA, BoxLayout.X_AXIS));
		componentA.add(scriptPath);
		componentA.add(browse);
		addComponent("Script path:", componentA);

		// components B, C
		functionName = new HistoryTextField("nashorn.wrapper.function");
		functionName.getModel().setMax(6);
		functionName.setText(jEdit.getProperty("options.nashorn-wrapper.function-name"));
		addComponent("Function name:", functionName);
		functionArg = new HistoryTextField("nashorn.wrapper.arg");
		functionArg.getModel().setMax(6);
		functionArg.setText(jEdit.getProperty("options.nashorn-wrapper.function-arg"));
		addComponent("Function arg:", functionArg);

		// component D
		dockableTitle = new HistoryTextField("nashorn.wrapper.dockable");
		dockableTitle.getModel().setMax(6);
		dockableTitle.setText(jEdit.getProperty("options.nashorn-wrapper.dockable-title"));
		JButton defaultDockableTitle = new JButton("Set default title");
		defaultDockableTitle.addActionListener(new DefaultTitleHandler());
		defaultDockableTitle.setToolTipText("Default dockable title is: Nashorn Wrapper");

		JPanel componentD = new JPanel();
		componentD.setLayout(new BoxLayout(componentD, BoxLayout.X_AXIS));
		componentD.add(dockableTitle);
		componentD.add(defaultDockableTitle);
		addComponent("Dockable title:", componentD);
	}

	protected void _save() {
		jEdit.setProperty("options.nashorn-wrapper.script-path", scriptPath.getText());
		scriptPath.addCurrentToHistory();
		jEdit.setProperty("options.nashorn-wrapper.function-name", functionName.getText());
		functionName.addCurrentToHistory();
		jEdit.setProperty("options.nashorn-wrapper.function-arg", functionArg.getText());
		functionArg.addCurrentToHistory();
		jEdit.setProperty("options.nashorn-wrapper.dockable-title", dockableTitle.getText());
		dockableTitle.addCurrentToHistory();
	}

	class BrowseHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			VFSFileChooserDialog dialog = new VFSFileChooserDialog(
				jEdit.getActiveView(),
				System.getProperty("user.dir") + FileSystems.getDefault().getSeparator(),
				VFSBrowser.OPEN_DIALOG, false, true);
			String[] files = dialog.getSelectedFiles();
			if (files != null) {
				scriptPath.setText(files[0]);
			}
		}
	}

	class DefaultTitleHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dockableTitle.setText("");
			jEdit.setProperty("nashornfuncwrapper.title", "Nashorn Wrapper");
		}
	}

}

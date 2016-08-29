import javax.swing.UIManager;

import frame.ScriptView;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		ScriptView view = new ScriptView();
		view.setVisible(true);
	}
}

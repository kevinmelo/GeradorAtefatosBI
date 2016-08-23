import javax.swing.UIManager;

import frame.ConexaoView;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		ConexaoView conexaoView = new ConexaoView();
		conexaoView.setVisible(true);
	}
}

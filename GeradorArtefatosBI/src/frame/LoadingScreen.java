package frame;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class LoadingScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			LoadingScreen dialog = new LoadingScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoadingScreen() {
		initComponents();
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoadingScreen.class.getResource("/resources/univali.png")));
		setTitle("Carregando....");
		setBounds(100, 100, 450, 95);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblLendoDadosDo = new JLabel("Lendo dados do banco....");

		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLendoDadosDo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404,
										Short.MAX_VALUE)
								.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblLendoDadosDo)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void setMaximum(int maximum) {
		progressBar.setIndeterminate(false);
		progressBar.setMaximum(maximum);
		progressBar.setMinimum(0);
	}

	public void setProgressBarValue(int value) {
		progressBar.setValue(value);
	}
}

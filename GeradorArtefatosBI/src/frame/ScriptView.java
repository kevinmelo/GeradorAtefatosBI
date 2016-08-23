package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Script;

import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ScriptView extends JFrame {

	private JPanel contentPane;
	private JButton btnAvancar;
	private JButton btnVoltar;
	
	private int level = 0;
	private List<Script> scripts = new ArrayList<>();
	private JTextField textField;
	private JTextArea textArea;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScriptView frame = new ScriptView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScriptView() {
		setTitle("Script");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ScriptView.class.getResource("/resources/univali.png")));
		initComponents();
		createEvents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JSeparator separator = new JSeparator();

		btnAvancar = new JButton("Avan\u00E7ar");
		btnAvancar.setMinimumSize(new Dimension(90, 23));
		btnAvancar.setPreferredSize(new Dimension(90, 23));

		btnVoltar = new JButton("Voltar");
		btnVoltar.setMinimumSize(new Dimension(90, 23));
		btnVoltar.setPreferredSize(new Dimension(90, 23));
		
		JLabel lblCreateTable = new JLabel("Create Table ");
		lblCreateTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label = new JLabel("{");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		label_1 = new JLabel("}");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
									.addComponent(btnAvancar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCreateTable)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
										.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
									.addGap(10)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(78)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreateTable)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAvancar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		
		textArea = new JTextArea();
		textArea.setBorder(null);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}

	private void createEvents() {
	}
}

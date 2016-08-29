package frame;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.bounce.text.LineNumberMargin;
import org.bounce.text.xml.XMLEditorKit;
import org.bounce.text.xml.XMLFoldingMargin;
import org.bounce.text.xml.XMLStyleConstants;

import controller.Controller;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TemplateView extends JFrame {

	private JPanel contentPane;
	private JButton btnVoltar;
	private JButton btnAvanar;
	private JEditorPane editorPane;
	private JTextField filePathField;
	private JButton btnAbrirTemplate;

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
					TemplateView frame = new TemplateView();
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
	public TemplateView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TemplateView.class.getResource("/resources/univali.png")));
		setTitle("Template");
		initComponents();
		createEvents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JSeparator separator = new JSeparator();

		btnAvanar = new JButton("Avan\u00E7ar");
		btnAvanar.setPreferredSize(new Dimension(90, 23));
		btnAvanar.setMinimumSize(new Dimension(90, 23));

		btnVoltar = new JButton("Voltar");
		btnVoltar.setPreferredSize(new Dimension(90, 23));
		btnVoltar.setMinimumSize(new Dimension(90, 23));

		JScrollPane scrollPane = new JScrollPane();

		btnAbrirTemplate = new JButton("Abrir Template");

		filePathField = new JTextField();
		filePathField.setEditable(false);
		filePathField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING,
										gl_contentPane.createSequentialGroup()
												.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
												.addComponent(btnAvanar, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404,
										Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnAbrirTemplate)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(filePathField,
												GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAbrirTemplate).addComponent(filePathField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(10).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAvanar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		XMLEditorKit kit = new XMLEditorKit();
		kit.setAutoIndentation(true);
		kit.setTagCompletion(true);
		kit.setStyle(XMLStyleConstants.ATTRIBUTE_NAME, new Color(231, 76, 60), Font.PLAIN);
		kit.setStyle(XMLStyleConstants.ATTRIBUTE_VALUE, new Color(41, 128, 185), Font.PLAIN);

		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Consolas", Font.PLAIN, 14));
		editorPane.setEditorKit(kit);
		editorPane.getDocument().putProperty(PlainDocument.tabSizeAttribute, 4);

		JPanel rowHeader = new JPanel(new BorderLayout());
		try {
			rowHeader.add(new XMLFoldingMargin(editorPane), BorderLayout.EAST);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		rowHeader.add(new LineNumberMargin(editorPane), BorderLayout.WEST);

		try {
			contentPane.add(new XMLFoldingMargin(editorPane), BorderLayout.EAST);
			contentPane.add(new LineNumberMargin(editorPane), BorderLayout.WEST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		scrollPane.setRowHeaderView(rowHeader);
		scrollPane.setViewportView(editorPane);
		contentPane.setLayout(gl_contentPane);
	}

	private void createEvents() {
		btnAbrirTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(Controller.getFileFilter(2));
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(TemplateView.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					filePathField.setText(file.getAbsolutePath());
					try {
						editorPane.read(new FileReader(file), file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					filePathField.setText("");
				}
			}
		});
	}
}

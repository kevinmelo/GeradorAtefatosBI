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

import controller.FileCTR;
import objects.Table;

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
import java.util.List;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TemplateView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnBack;
	private JButton btnGenerate;
	private JEditorPane editorPane;
	private JTextField filePathField;
	private JButton btnOpen;
	private JButton btnSave;

	private String path = "";
	private File file = null;

	private List<Table> scripts;
	private TemplateView templateView;

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
					TemplateView frame = new TemplateView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param scripts
	 */
	public TemplateView(List<Table> scripts) {
		this.scripts = scripts;
		initComponents();
		createEvents();
		templateView = this;
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TemplateView.class.getResource("/resources/univali.png")));
		setTitle("Template");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JSeparator separator = new JSeparator();

		btnGenerate = new JButton("Gerar Schema");
		btnGenerate.setPreferredSize(new Dimension(90, 23));
		btnGenerate.setMinimumSize(new Dimension(90, 23));

		btnBack = new JButton("Voltar");
		btnBack.setPreferredSize(new Dimension(90, 23));
		btnBack.setMinimumSize(new Dimension(90, 23));

		JScrollPane scrollPane = new JScrollPane();

		btnOpen = new JButton("Abrir Template");

		filePathField = new JTextField();
		filePathField.setEditable(false);
		filePathField.setColumns(10);

		btnSave = new JButton("Salvar");
		btnSave.setPreferredSize(new Dimension(90, 23));
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setMinimumSize(new Dimension(90, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
									.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnOpen)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(filePathField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(162)
							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(162))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOpen)
						.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

		XMLEditorKit kit = new XMLEditorKit();
		kit.setAutoIndentation(true);
		kit.setTagCompletion(true);
		kit.setStyle(XMLStyleConstants.ATTRIBUTE_NAME, new Color(231, 76, 60), Font.PLAIN);
		kit.setStyle(XMLStyleConstants.ATTRIBUTE_VALUE, new Color(41, 128, 185), Font.PLAIN);

		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Consolas", Font.PLAIN, 14));
		editorPane.setEditorKit(kit);
		editorPane.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);

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
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFileChooser fc = new JFileChooser(path);
				fc.addChoosableFileFilter(FileCTR.getFileFilter(2));
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(TemplateView.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					path = file.getAbsolutePath();
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

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptView scriptView = new ScriptView(scripts);
				scriptView.setVisible(true);
				templateView.dispose();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file == null) {
					final JFileChooser fc = new JFileChooser(path);
					fc.addChoosableFileFilter(FileCTR.getFileFilter(2));
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(TemplateView.this);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						FileCTR.writeFile(file, editorPane.getText().getBytes(), TemplateView.this);
					}
				} else {
					FileCTR.writeFile(file, editorPane.getText().getBytes(), TemplateView.this);
				}
			}
		});

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileCTR.createSchema(file, scripts);
			}
		});
	}
}

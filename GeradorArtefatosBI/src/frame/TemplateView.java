package frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

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
import javax.swing.ImageIcon;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

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
	private JToolBar toolBar;

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

		filePathField = new JTextField();
		filePathField.setFocusable(false);
		filePathField.setEditable(false);
		filePathField.setColumns(10);

		toolBar = new JToolBar();
		toolBar.setPreferredSize(new Dimension(20, 20));
		toolBar.setFloatable(false);

		ImageIcon icon = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(ScriptView.class.getResource("/resources/save.png")));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		
		btnSave = new JButton(icon);
		btnSave.setFocusable(false);
		toolBar.add(btnSave);
		
		icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ScriptView.class.getResource("/resources/open.png")));
		img = icon.getImage();
		newimg = img.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		
		btnOpen = new JButton(icon);
		btnOpen.setFocusable(false);
		toolBar.add(btnOpen);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(filePathField, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
							.addComponent(btnGenerate, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
		editorPane.getDocument().putProperty(PlainDocument.tabSizeAttribute, 2);
		editorPane.setEditorKit(kit);

		JPanel rowHeader = new JPanel(new BorderLayout());
		try {
			XMLFoldingMargin foldingMargin = new XMLFoldingMargin(editorPane);
			foldingMargin.setFocusable(false);
			rowHeader.add(foldingMargin, BorderLayout.EAST);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LineNumberMargin lineNumberMargin = new LineNumberMargin(editorPane);
		lineNumberMargin.setFocusable(false);
		rowHeader.add(lineNumberMargin, BorderLayout.WEST);

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

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		editorPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					save();
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

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileCTR.createSchema(file, scripts);
			}
		});
	}

	private void save() {
		if (file == null) {
			final JFileChooser fc = new JFileChooser(path);
			fc.addChoosableFileFilter(FileCTR.getFileFilter(2));
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showSaveDialog(TemplateView.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				FileCTR.writeFile(file, editorPane.getText().getBytes(), TemplateView.this);
			}
		} else {
			FileCTR.writeFile(file, editorPane.getText().getBytes(), TemplateView.this);
		}
	}
}

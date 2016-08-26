package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import objects.ConexaoDB;
import objects.Script;

import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class ScriptView extends JFrame {

	private JPanel contentPane;
	private JButton btnProcurar;
	private JTextField filePathField;
	private DefaultListModel<Script> tableModel = new DefaultListModel<>();
	private DefaultListModel<String> columnModel = new DefaultListModel<>();
	private JButton btnNovaConexo;
	private JButton btnNewButton;
	private JSeparator separator;

	private List<Script> scripts = new ArrayList<>();
	private List<ConexaoDB> bancoDadosList = new ArrayList<>();
	private JList<Script> tabelaLista;
	private JList<String> colunaLista;
	private JComboBox<ConexaoDB> comboBox;

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
	@SuppressWarnings("unchecked")
	public ScriptView() {
		bancoDadosList = (List<ConexaoDB>) Controller.lerArquivo(1);
		initComponents();
		createEvents();
	}

	private void initComponents() {
		setTitle("Script");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ScriptView.class.getResource("/resources/univali.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnProcurar = new JButton("Procurar Arquivo SQL");
		btnProcurar.setMinimumSize(new Dimension(90, 23));

		filePathField = new JTextField();
		filePathField.setEditable(false);
		filePathField.setColumns(10);

		JSeparator separator_1 = new JSeparator();

		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new TitledBorder(null, "Tabelas", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Colunas", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		btnNovaConexo = new JButton("Nova Conex\u00E3o");

		comboBox = new JComboBox<ConexaoDB>();
		atualizaComboBox();

		btnNewButton = new JButton("Avan\u00E7ar");
		btnNewButton.setPreferredSize(new Dimension(90, 23));
		btnNewButton.setMinimumSize(new Dimension(90, 23));

		separator = new JSeparator();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnProcurar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(filePathField, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(panel,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnNovaConexo)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox, 0, 345, Short.MAX_VALUE))
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProcurar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(filePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnNovaConexo)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		JScrollPane colunaScrollPane = new JScrollPane();
		colunaLista = new JList<String>();
		colunaLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colunaLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		colunaScrollPane.setViewportView(colunaLista);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(colunaScrollPane, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(colunaScrollPane, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);

		JScrollPane tabelaScrollPane = new JScrollPane();
		tabelaLista = new JList<Script>();
		tabelaLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabelaScrollPane.setViewportView(tabelaLista);

		GroupLayout gl_tablePanel = new GroupLayout(tablePanel);
		gl_tablePanel.setHorizontalGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup().addContainerGap()
						.addComponent(tabelaScrollPane, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE).addContainerGap()));
		gl_tablePanel.setVerticalGroup(
				gl_tablePanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_tablePanel.createSequentialGroup()
						.addComponent(tabelaScrollPane, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE).addContainerGap()));
		tablePanel.setLayout(gl_tablePanel);
		contentPane.setLayout(gl_contentPane);
	}

	private void createEvents() {
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(Controller.getFileFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(ScriptView.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					filePathField.setText(file.getAbsolutePath());
					Controller.readSqlFile(file, scripts);
					tableModel = new DefaultListModel<>();
					atualizaLista();
				} else {
					filePathField.setText("");
				}
			}
		});

		tabelaLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!tableModel.isEmpty()) {
					columnModel = new DefaultListModel<>();
					for (String c : tabelaLista.getSelectedValue().getColunas()) {
						columnModel.addElement(c);
					}
					colunaLista.setModel(columnModel);
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() != -1) {
					ConexaoDB bd = (ConexaoDB) comboBox.getSelectedItem();
					Controller.readDataBase(bd, scripts);
					atualizaLista();
				}
			}
		});
	}

	private void atualizaLista() {
		for (Script s : scripts) {
			tableModel.addElement(s);
			columnModel = new DefaultListModel<>();
		}
		tabelaLista.setModel(tableModel);
	}

	private void atualizaComboBox() {
		comboBox.removeAllItems();
		for (ConexaoDB bd : bancoDadosList) {
			comboBox.addItem(bd);
		}
		comboBox.setSelectedIndex(-1);
	}
}

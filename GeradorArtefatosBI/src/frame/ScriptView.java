package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.DataBaseCTR;
import controller.FileCTR;
import objects.Column;
import objects.ConexaoDB;
import objects.ListRender;
import objects.Table;

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
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ScriptView extends JFrame {

	private JPanel contentPane;
	private JButton btnProcurar;
	private JTextField filePathField;
	private DefaultListModel<Table> tableModel = new DefaultListModel<>();
	private JList<Table> tableList;
	private DefaultListModel<Column> columnModel = new DefaultListModel<>();
	private JList<Column> columnList = new JList<Column>();;
	private JButton btnNovaConexo;
	private JButton btnNext;
	private JSeparator separator;

	private JPopupMenu tablePopupMenu = new JPopupMenu();
	private JRadioButtonMenuItem cubeMenuItem = new JRadioButtonMenuItem("Cubo");
	private JRadioButtonMenuItem aggregationMenuItem = new JRadioButtonMenuItem("Aggregação");
	private JMenuItem removeTableMenuItem = new JMenuItem("Remover");

	private JPopupMenu columnPopupMenu = new JPopupMenu();
	private JRadioButtonMenuItem measureMenuItem = new JRadioButtonMenuItem("Medida");
	private JRadioButtonMenuItem primaryKeyMenuItem = new JRadioButtonMenuItem("Chave Primária");
	private JMenuItem removeColumnMenuItem = new JMenuItem("Remover");

	private int selectedTableIndex = -1;
	private int selectedColumnIndex = -1;
	private boolean isUpdating = false;

	private ScriptView scriptView;
	private List<Table> scripts = new ArrayList<>();
	private List<ConexaoDB> bancoDadosList = new ArrayList<>();
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
	public ScriptView(List<Table> scripts) {
		this.scripts = scripts;
		initComponents();
		createEvents();
		updateTableList();
		scriptView = this;
	}

	public ScriptView() {
		initComponents();
		createEvents();
		scriptView = this;
	}

	private void initComponents() {
		setTitle("Script");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ScriptView.class.getResource("/resources/univali.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 468);
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
		updateComboBox();

		btnNext = new JButton("Avan\u00E7ar");
		btnNext.setPreferredSize(new Dimension(90, 23));
		btnNext.setMinimumSize(new Dimension(90, 23));

		separator = new JSeparator();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnProcurar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(filePathField, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnNovaConexo)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBox, 0, 438, Short.MAX_VALUE))
								.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
								.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(tablePanel,
								GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		columnPopupMenu.add(primaryKeyMenuItem);
		columnPopupMenu.add(measureMenuItem);
		columnPopupMenu.add(removeColumnMenuItem);

		JScrollPane colunaScrollPane = new JScrollPane();
		columnList.setCellRenderer(new ListRender());
		columnList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		columnList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		colunaScrollPane.setViewportView(columnList);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(colunaScrollPane, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(colunaScrollPane, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addContainerGap()));
		panel.setLayout(gl_panel);

		tablePopupMenu.add(cubeMenuItem);
		tablePopupMenu.add(aggregationMenuItem);
		tablePopupMenu.add(removeTableMenuItem);

		JScrollPane tabelaScrollPane = new JScrollPane();
		tableList = new JList<Table>();
		tableList.setCellRenderer(new ListRender());
		tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabelaScrollPane.setViewportView(tableList);

		GroupLayout gl_tablePanel = new GroupLayout(tablePanel);
		gl_tablePanel.setHorizontalGroup(gl_tablePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tablePanel.createSequentialGroup().addContainerGap()
						.addComponent(tabelaScrollPane, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addContainerGap()));
		gl_tablePanel.setVerticalGroup(gl_tablePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_tablePanel.createSequentialGroup()
						.addComponent(tabelaScrollPane, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
						.addContainerGap()));
		tablePanel.setLayout(gl_tablePanel);
		contentPane.setLayout(gl_contentPane);
	}

	private void createEvents() {
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(FileCTR.getFileFilter(1));
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(ScriptView.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					filePathField.setText(file.getAbsolutePath());
					FileCTR.readSqlFile(file, scripts);
					tableModel = new DefaultListModel<>();
					updateTableList();
				} else {
					filePathField.setText("");
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() != -1 && !isUpdating) {
					ConexaoDB bd = (ConexaoDB) comboBox.getSelectedItem();
					new Thread(() -> {
						DataBaseCTR.readDataBase(bd, scripts);
						updateTableList();
					}).start();
				}
			}
		});

		btnNovaConexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexaoView conexaoView = new ConexaoView();
				conexaoView.setModal(true);
				conexaoView.setVisible(true);
				updateComboBox();
			}
		});

		tableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!tableModel.isEmpty()) {
					if (SwingUtilities.isRightMouseButton(e)) {
						tablePopupMenu.show(e.getComponent(), e.getX(), e.getY());
						tableList.setSelectedIndex(tableList.locationToIndex(e.getPoint()));
						if (tableList.getSelectedValue().isCube()) {
							cubeMenuItem.setSelected(true);
						} else {
							cubeMenuItem.setSelected(false);
						}
						if(tableList.getSelectedValue().isAggregation()) {
							aggregationMenuItem.setSelected(true);
						} else {
							aggregationMenuItem.setSelected(false);
						}
					}
					selectedTableIndex = tableList.getSelectedIndex();
					updateColumnList();
				}
			}
		});

		cubeMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableList.getSelectedValue().setCube();
			}
		});
		
		aggregationMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableList.getSelectedValue().setAggregation();
			}
		});

		removeTableMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scripts.remove(selectedTableIndex);
				updateTableList();
				columnModel = new DefaultListModel<>();
				columnList.setModel(columnModel);
			}
		});

		columnList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && !columnModel.isEmpty()) {
					columnPopupMenu.show(e.getComponent(), e.getX(), e.getY());
					columnList.setSelectedIndex(columnList.locationToIndex(e.getPoint()));
					if (columnList.getSelectedValue().isMeasure()) {
						measureMenuItem.setSelected(true);
					} else {
						measureMenuItem.setSelected(false);
					}
					if (columnList.getSelectedValue().isPrimaryKey()) {
						primaryKeyMenuItem.setSelected(true);
					} else {
						primaryKeyMenuItem.setSelected(false);
					}
				}
				selectedColumnIndex = columnList.getSelectedIndex();
			}
		});

		primaryKeyMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Table table = tableList.getSelectedValue();
				Column column = columnList.getSelectedValue();

				if (column.isPrimaryKey()) {
					table.setPrimaryKey("");
					column.setPrimaryKey(false);
				} else {
					for (Column c : table.getColumns()) {
						if (c.isPrimaryKey()) {
							if (c.getName().equals(column.getName())) {
								table.setPrimaryKey("");
								column.setPrimaryKey(false);
							} else {
								c.setPrimaryKey(false);
							}
						}
						table.setPrimaryKey(column.getName());
						column.setPrimaryKey(true);
						column.setMeasure(false);
					}
				}
				columnList.repaint();
			}
		});

		measureMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Column column = columnList.getSelectedValue();
				if (column.isMeasure()) {
					column.setMeasure(false);
				} else {
					column.setMeasure(true);
				}
				column.setPrimaryKey(false);
				columnList.repaint();
			}
		});

		removeColumnMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scripts.get(selectedTableIndex).removeColumn(selectedColumnIndex);
				updateColumnList();
			}
		});

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TemplateView template = new TemplateView(scripts);
				template.setVisible(true);
				scriptView.dispose();
			}
		});
	}

	private void updateTableList() {
		tableModel = new DefaultListModel<>();
		for (Table s : scripts) {
			tableModel.addElement(s);
			columnModel = new DefaultListModel<>();
		}
		tableList.setModel(tableModel);
	}

	private void updateColumnList() {
		columnModel = new DefaultListModel<>();
		for (Column c : scripts.get(selectedTableIndex).getColumns()) {
			columnModel.addElement(c);
		}
		columnList.setModel(columnModel);
	}

	@SuppressWarnings("unchecked")
	private void updateComboBox() {
		bancoDadosList = (List<ConexaoDB>) FileCTR.readFile(1);
		comboBox.removeAllItems();
		isUpdating = true;
		for (ConexaoDB bd : bancoDadosList) {
			comboBox.addItem(bd);
		}
		isUpdating = false;
		selectedTableIndex = -1;
		comboBox.setSelectedIndex(-1);
	}
}

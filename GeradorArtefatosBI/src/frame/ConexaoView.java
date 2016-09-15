package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DataBaseCTR;
import controller.FileCTR;
import objects.ConexaoDB;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConexaoView extends JDialog {

	private List<ConexaoDB> bancoDadosList = new ArrayList<>();
	private JPanel contentPane;
	private JTextField nomeText;
	private JTextField jdbcDriveText;
	private JTextField jdbcUrlText;
	private JTextField usuarioText;
	private JPasswordField senhaText;
	private JButton btnSalvar;
	private JButton btnConcluir;
	private JComboBox<ConexaoDB> comboBox;
	private int selectIndex = -1;
	private JButton btnDelete;
	private ConexaoView conexaoView;
	private JButton btnCancel;

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
					ConexaoView frame = new ConexaoView();
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
	public ConexaoView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConexaoView.class.getResource("/resources/univali.png")));
		bancoDadosList = (List<ConexaoDB>) FileCTR.readFile(1);
		conexaoView = this;
		initComponents();
		createEvents();
	}

	// INIT COMPONENTS --------------------------------------------
	private void initComponents() {
		setTitle("Conexao");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnConcluir = new JButton("Concluir");
		btnConcluir.setPreferredSize(new Dimension(90, 23));
		btnConcluir.setEnabled(false);

		comboBox = new JComboBox<ConexaoDB>();
		atualizaComboBox();

		JLabel lblNewLabel = new JLabel("Selecione uma conex\u00E3o:");

		JSeparator separator = new JSeparator();

		nomeText = new JTextField();
		nomeText.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);

		jdbcDriveText = new JTextField();
		jdbcDriveText.setText("org.postgresql.Driver");
		jdbcDriveText.setColumns(10);

		jdbcUrlText = new JTextField();
		jdbcUrlText.setColumns(10);

		usuarioText = new JTextField();
		usuarioText.setColumns(10);

		JSeparator separator_1 = new JSeparator();

		JLabel lblJdbcDrive = new JLabel("JDBC Drive:");
		lblJdbcDrive.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblJdbcUrl = new JLabel("JDBC URL:");
		lblJdbcUrl.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setPreferredSize(new Dimension(90, 23));

		senhaText = new JPasswordField();

		btnDelete = new JButton("Delete");
		btnDelete.setPreferredSize(new Dimension(90, 23));
		btnDelete.setEnabled(false);

		btnCancel = new JButton("Cancelar");
		btnCancel.setPreferredSize(new Dimension(90, 23));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBox, 0, 318, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
								.addComponent(btnConcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsurio, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJdbcUrl, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblJdbcDrive, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
								.addGap(10)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(senhaText, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
										.addComponent(jdbcDriveText, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
										.addComponent(jdbcUrlText, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
										.addComponent(usuarioText, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(nomeText,
														GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)))))
				.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup().addGap(150)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE).addGap(18)
						.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 90, Short.MAX_VALUE).addGap(127)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(11).addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNome)
								.addComponent(nomeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblJdbcDrive, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jdbcDriveText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblJdbcUrl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jdbcUrlText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUsurio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(usuarioText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSenha)
								.addComponent(senhaText, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnConcluir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))));
		contentPane.setLayout(gl_contentPane);
	}

	private void atualizaComboBox() {
		comboBox.removeAllItems();
		for (ConexaoDB bd : bancoDadosList) {
			comboBox.addItem(bd);
		}
		comboBox.setSelectedIndex(-1);
		selectIndex = -1;
	}

	// CREATE EVENTS ----------------------------------------------
	private void createEvents() {
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (camposValidos()) {
					ConexaoDB bd = new ConexaoDB();

					bd.setNome(nomeText.getText());
					bd.setJdbcDrive(jdbcDriveText.getText());
					bd.setJdbcUrl(jdbcUrlText.getText());
					bd.setUsuario(usuarioText.getText());
					bd.setSenha(String.valueOf(senhaText.getPassword()));

					if (DataBaseCTR.connectionIsValid(bd)) {
						if (selectIndex == -1) {
							JOptionPane.showMessageDialog(null, "Conex�o banco salvo com sucesso!");
							bancoDadosList.add(bd);
						} else {
							JOptionPane.showMessageDialog(null, "Conex�o banco atualizado com sucesso!");
							bancoDadosList.set(selectIndex, bd);
						}

						FileCTR.writeFile(bancoDadosList);
						atualizaComboBox();
					}
				}
			}

			private boolean camposValidos() {
				if (nomeText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome n�o pode ser vazio!");
					return false;
				}

				if (jdbcDriveText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "JDBC Driver n�o pode ser vazio!");
					return false;
				}

				if (jdbcUrlText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "JDBC URL n�o pode ser vazio!");
					return false;
				}

				if (usuarioText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Usu�rio n�o pode ser vazio!");
					return false;
				}

				return true;
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() != -1) {
					ConexaoDB bd = (ConexaoDB) comboBox.getSelectedItem();

					selectIndex = comboBox.getSelectedIndex();

					nomeText.setText(bd.getNome());
					jdbcDriveText.setText(bd.getJdbcDrive());
					jdbcUrlText.setText(bd.getJdbcUrl());
					usuarioText.setText(bd.getUsuario());
					senhaText.setText(bd.getSenha());

					btnDelete.setEnabled(true);
					btnConcluir.setEnabled(true);
				} else {
					nomeText.setText("");
					jdbcUrlText.setText("");
					usuarioText.setText("");
					senhaText.setText("");
					btnDelete.setEnabled(false);
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Conex�o deletada com sucesso!");

				bancoDadosList.remove(selectIndex);

				nomeText.setText("");
				jdbcUrlText.setText("");
				usuarioText.setText("");
				senhaText.setText("");

				btnDelete.setEnabled(false);
				btnConcluir.setEnabled(false);

				FileCTR.writeFile(bancoDadosList);
				atualizaComboBox();
			}
		});

		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (camposValidos()) {
					ConexaoDB bd = new ConexaoDB();

					bd.setNome(nomeText.getText());
					bd.setJdbcDrive(jdbcDriveText.getText());
					bd.setJdbcUrl(jdbcUrlText.getText());
					bd.setUsuario(usuarioText.getText());
					bd.setSenha(String.valueOf(senhaText.getPassword()));

					if (DataBaseCTR.connectionIsValid(bd)) {
						if (selectIndex == -1) {
							JOptionPane.showMessageDialog(null, "Conex�o banco salvo com sucesso!");
							bancoDadosList.add(bd);
						} else {
							JOptionPane.showMessageDialog(null, "Conex�o banco atualizado com sucesso!");
							bancoDadosList.set(selectIndex, bd);
						}

						FileCTR.writeFile(bancoDadosList);
						atualizaComboBox();
					}
				}
				conexaoView.dispose();
			}

			private boolean camposValidos() {
				if (nomeText.getText().isEmpty()) {
					return false;
				}

				if (jdbcDriveText.getText().isEmpty()) {
					return false;
				}

				if (jdbcUrlText.getText().isEmpty()) {
					return false;
				}

				if (usuarioText.getText().isEmpty()) {
					return false;
				}

				return true;
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conexaoView.dispose();
			}
		});
	}
}

package frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BancoDadosController;
import objects.BancoDados;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
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

@SuppressWarnings("serial")
public class BancoDadosView extends JFrame {

	private List<BancoDados> bancoDadosList = new ArrayList<>();
	private JPanel contentPane;
	private JTextField nomeText;
	private JTextField jdbcDriveText;
	private JTextField jdbcUrlText;
	private JTextField usuarioText;
	private JPasswordField senhaText;
	private JButton btnSalvar;
	private JButton btnNewButton;
	private JComboBox<BancoDados> comboBox;

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
					BancoDadosView frame = new BancoDadosView();
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
	public BancoDadosView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BancoDadosView.class.getResource("/resources/univali.png")));
		initComponents();
		createEvents();

	}

	// INIT COMPONENTS --------------------------------------------
	private void initComponents() {
		setTitle("Banco de Dados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnNewButton = new JButton("Avan\u00E7ar");
		btnNewButton.setMinimumSize(new Dimension(90, 23));

		comboBox = new JComboBox<BancoDados>();
		comboBox.setToolTipText("");
		atualizaComboBox();

		JLabel lblNewLabel = new JLabel("Selecione um banco:");

		JSeparator separator = new JSeparator();

		nomeText = new JTextField();
		nomeText.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");

		jdbcDriveText = new JTextField();
		jdbcDriveText.setColumns(10);

		jdbcUrlText = new JTextField();
		jdbcUrlText.setColumns(10);

		usuarioText = new JTextField();
		usuarioText.setColumns(10);

		JSeparator separator_1 = new JSeparator();

		JLabel lblJdbcDrive = new JLabel("JDBC Drive:");

		JLabel lblJdbcUrl = new JLabel("JDBC URL:");

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");

		JLabel lblSenha = new JLabel("Senha:");

		btnSalvar = new JButton("Salvar");
		btnSalvar.setMinimumSize(new Dimension(90, 23));
		
		senhaText = new JPasswordField();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 338, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSenha, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
								.addComponent(lblUsurio, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
								.addComponent(lblJdbcUrl, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
								.addComponent(lblJdbcDrive, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
								.addComponent(lblNome, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(senhaText, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(jdbcDriveText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(jdbcUrlText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(usuarioText, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(nomeText, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)))))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(197)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 90, Short.MAX_VALUE)
					.addGap(187))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNome)
						.addComponent(nomeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblJdbcDrive, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jdbcDriveText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblJdbcUrl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jdbcUrlText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUsurio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(usuarioText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(senhaText, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void atualizaComboBox() {
		for (BancoDados bd : bancoDadosList) {
			comboBox.addItem(bd);
		}
		comboBox.setSelectedIndex(-1);
	}

	// CREATE EVENTS ----------------------------------------------
	private void createEvents() {
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (camposValidos()) {
					BancoDados bd = new BancoDados();
					BancoDadosController bdc = new BancoDadosController();

					bd.setNome(nomeText.getText());
					bd.setJdbcDrive(jdbcDriveText.getText());
					bd.setJdbcUrl(jdbcUrlText.getText());
					bd.setUsuario(usuarioText.getText());
					bd.setSenha(senhaText.getText());

					if (bdc.validaConexao(bd)) {
						JOptionPane.showMessageDialog(null, "Banco salvo com sucesso!");

						bancoDadosList.add(bd);

						nomeText.setText("");
						jdbcDriveText.setText("");
						jdbcUrlText.setText("");
						usuarioText.setText("");
						senhaText.setText("");

						atualizaComboBox();
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possivel conectar com o banco!");
					}
				}
			}

			private boolean camposValidos() {
				if (nomeText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome não pode ser vazio!");
					return false;
				}

				if (jdbcDriveText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "JDBC Driver não pode ser vazio!");
					return false;
				}

				if (jdbcUrlText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "JDBC URL não pode ser vazio!");
					return false;
				}

				if (usuarioText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Usuário não pode ser vazio!");
					return false;
				}

				return true;
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BancoDados bd = (BancoDados) comboBox.getSelectedItem();

				nomeText.setText(bd.getNome());
				jdbcDriveText.setText(bd.getJdbcDrive());
				jdbcUrlText.setText(bd.getJdbcUrl());
				usuarioText.setText(bd.getUsuario());
				senhaText.setText(bd.getSenha());
			}
		});
	}
}

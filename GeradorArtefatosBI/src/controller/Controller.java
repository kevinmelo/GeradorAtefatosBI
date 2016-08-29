package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.filechooser.FileFilter;

import objects.ConexaoDB;
import objects.Table;

public class Controller {

	private static final String CAMINHO_RESOURCES = "src" + File.separator + "resources";
	private static final String ARQUIVO_BANCO_DADOS = "conex�o.data";

	public boolean validaConexao(ConexaoDB bd) {
		try {
			Class.forName(bd.getJdbcDrive());
		} catch (ClassNotFoundException e) {
			return false;
		}
		try {
			DriverManager.getConnection(bd.getJdbcUrl(), bd.getUsuario(), bd.getSenha());
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public void escreveArquivo(List<ConexaoDB> obj) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		try {
			fout = new FileOutputStream(
					"." + File.separator + CAMINHO_RESOURCES + File.separator + ARQUIVO_BANCO_DADOS);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(obj);
		} catch (Exception e) {
			System.out.println("N�o foi possivel salvar o arquivo de conex�o.");
		}
	}

	@SuppressWarnings({ "resource" })
	public static Object lerArquivo(int fileIndex) {
		FileInputStream fin;
		ObjectInputStream oin;

		switch (fileIndex) {
		case 1:
			try {
				fin = new FileInputStream(
						"." + File.separator + CAMINHO_RESOURCES + File.separator + ARQUIVO_BANCO_DADOS);
				oin = new ObjectInputStream(fin);
				return oin.readObject();
			} catch (Exception e) {
				System.out.println("N�o foi possivel ler o arquivo de conex�o.");
				break;
			}
		}
		return new ArrayList<>();
	}

	public static FileFilter getFileFilter(int type) {
		return new FileFilter() {
			@Override
			public String getDescription() {
				switch (type) {
				case 1:
					return "SQL Files";

				case 2:
					return "XML Files";

				default:
					return "Todos";
				}
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}

				String extension = Controller.getExtension(f);
				if (extension != null) {
					if (extension.equals("sql") || extension.equals("txt") || extension.equals("xml")) {
						return true;
					}
				} else {
					return false;
				}
				return false;
			}
		};
	}

	private static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public static void readSqlFile(File file, List<Table> scripts) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringTokenizer st = null;
		Table script = new Table();
		while (scanner.hasNextLine()) {
			String lines = scanner.nextLine();
			if (lines.startsWith("CREATE TABLE")) {
				StringTokenizer st1 = new StringTokenizer(lines, "(");
				if (!script.getTabela().isEmpty()) {
					scripts.add(script);
					script = new Table();
				}
				script.setTabela(st1.nextToken().substring(13));
			}
			st = new StringTokenizer(lines);

			if (!lines.equals("") && lines.indexOf("CREATE") == -1 && lines.indexOf("ALTER") == -1
					&& lines.indexOf("DROP") == -1 && !lines.equals("(") && !lines.equals(");")) {
				if (lines.startsWith(" ") || !lines.startsWith(" ")) {
					script.setColunas(st.nextToken());
				}
			}
		}
	}

	public static void readDataBase(ConexaoDB conexao, List<Table> scripts) {
		try {
			Connection con = DriverManager.getConnection(conexao.getJdbcUrl(), conexao.getUsuario(),
					conexao.getSenha());
			DatabaseMetaData meta = con.getMetaData();
			ResultSet res = meta.getTables(null, con.getSchema(), null, new String[] { "TABLE" });

			while (res.next()) {
				Table script = new Table();
				script.setTabela(res.getString("TABLE_NAME"));
				script.setSchema(res.getString("TABLE_SCHEM"));
				scripts.add(script);
			}
			res.close();

			for (Table s : scripts) {
				res = meta.getColumns(null, s.getSchema(), s.getTabela(), null);
				while (res.next()) {
					s.setColunas(res.getString("COLUMN_NAME"));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

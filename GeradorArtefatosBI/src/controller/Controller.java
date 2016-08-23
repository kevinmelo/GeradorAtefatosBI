package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objects.ConexaoDB;

public class Controller {
	
	private static final String CAMINHO_RESOURCES = "src" + File.separator + "resources";
	private static final String ARQUIVO_BANCO_DADOS = "conexão.data";

	public boolean validaConexao(ConexaoDB bd) {
		Connection con;
		try {
			Class.forName(bd.getJdbcDrive());
		} catch (ClassNotFoundException e) {
			return false;
		}
		try {
			con = DriverManager.getConnection(bd.getJdbcUrl(), bd.getUsuario(), bd.getSenha());
			// Statement stmt = con.createStatement();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public void escreveArquivo(List<ConexaoDB> obj) {
		FileOutputStream fout;
		ObjectOutputStream oout;
		try {
			fout = new FileOutputStream("." + File.separator + CAMINHO_RESOURCES + File.separator + ARQUIVO_BANCO_DADOS);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(obj);
		} catch (Exception e) {
			System.out.println("Não foi possivel salvar o arquivo de conexão.");
		}
	}

	@SuppressWarnings({ "resource" })
	public Object lerArquivo(int fileIndex) {
		FileInputStream fin;
		ObjectInputStream oin;

		switch (fileIndex) {
		case 1:
			try {
				fin = new FileInputStream("." + File.separator + CAMINHO_RESOURCES + File.separator + ARQUIVO_BANCO_DADOS);
				oin = new ObjectInputStream(fin);
				return oin.readObject();
			} catch (Exception e) {
				System.out.println("Não foi possivel ler o arquivo de conexão.");
				break;
			}
		}
		return new ArrayList<>();
	}
}

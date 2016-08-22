package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import objects.BancoDados;

public class BancoDadosController {

	public boolean validaConexao(BancoDados bd) {
		Connection con;
		try {
			Class.forName(bd.getJdbcDrive());
		} catch (ClassNotFoundException e) {
			return false;
		}
		try {
			con = DriverManager.getConnection(bd.getJdbcUrl());
//			Statement stmt = con.createStatement();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}

package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import frame.LoadingScreen;
import objects.Column;
import objects.ConexaoDB;
import objects.ForeignKey;
import objects.Table;

public class DataBaseCTR {

	private static int i = 1;

	public static boolean connectionIsValid(ConexaoDB bd) {
		try {
			Class.forName(bd.getJdbcDrive());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "JDBCDriver não encontrado!");
			return false;
		}
		try {
			DriverManager.getConnection(bd.getJdbcUrl(), bd.getUsuario(), bd.getSenha());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
		return true;
	}

	public static void readDataBase(ConexaoDB conexao, List<Table> scripts) {
		Connection con = null;
		LoadingScreen loading = new LoadingScreen();
		loading.setVisible(true);
		try {
			con = DriverManager.getConnection(conexao.getJdbcUrl(), conexao.getUsuario(), conexao.getSenha());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		if (con != null) {
			try {
				DatabaseMetaData meta = con.getMetaData();
				ResultSet res = meta.getTables(null, con.getSchema(), null, new String[] { "TABLE" });

				while (res.next()) {
					Table script = new Table();
					script.setName(res.getString("TABLE_NAME"));
					script.setSchema(res.getString("TABLE_SCHEM"));
					scripts.add(script);
				}
				res.close();

				loading.setMaximum(scripts.size());
				i = 1;

				for (Table s : scripts) {
					getTableColumns(meta, s);
					getTablePrimaryKeys(meta, s);
					getTableForeignKeys(meta, s);
					loading.setProgressBarValue(i);
					i++;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		loading.dispose();
	}

	private static void getTableColumns(DatabaseMetaData meta, Table t) {
		ResultSet res;
		try {
			res = meta.getColumns(null, t.getSchema(), t.getName(), null);
			while (res.next()) {
				Column column = new Column();
				column.setName(res.getString("COLUMN_NAME"));
				column.setType(res.getString("TYPE_NAME"));
				t.setColumn(column);
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getTablePrimaryKeys(DatabaseMetaData meta, Table t) {
		ResultSet res;
		try {
			res = meta.getPrimaryKeys(null, t.getSchema(), t.getName());
			while (res.next()) {
				t.setPrimaryKey(res.getString("COLUMN_NAME"));
				for (Column c : t.getColumns()) {
					if (c.getName().equals(t.getPrimaryKey())) {
						c.setPrimaryKey(true);
					}
				}
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getTableForeignKeys(DatabaseMetaData meta, Table t) {
		ResultSet res;
		try {
			res = meta.getImportedKeys(null, t.getSchema(), t.getName());
			while (res.next()) {
				String foreignTable = res.getString("PKTABLE_NAME");
				String foreignKey = res.getString("FKCOLUMN_NAME");
				ForeignKey fk = new ForeignKey(foreignTable, foreignKey);
				if (!t.getForeignKeys().contains(fk)) {
					t.setForeignKey(new ForeignKey(foreignTable, foreignKey));
					for (Column c : t.getColumns()) {
						if (c.getName().equals(foreignKey)) {
							c.setForeignKey(true);
							break;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

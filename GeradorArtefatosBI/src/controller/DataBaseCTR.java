package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import frame.LoadingScreen;
import objects.Column;
import objects.ConexaoDB;
import objects.Table;

public class DataBaseCTR {

	private static int i = 1;

	public static void readDataBase(ConexaoDB conexao, List<Table> scripts) {
		try {
			Connection con = DriverManager.getConnection(conexao.getJdbcUrl(), conexao.getUsuario(),
					conexao.getSenha());
			DatabaseMetaData meta = con.getMetaData();
			ResultSet res = meta.getTables(null, con.getSchema(), null, new String[] { "TABLE" });
			LoadingScreen loading;

			while (res.next()) {
				Table script = new Table();
				script.setName(res.getString("TABLE_NAME"));
				script.setSchema(res.getString("TABLE_SCHEM"));
				scripts.add(script);
			}
			res.close();

			loading = new LoadingScreen(scripts.size());
			loading.setVisible(true);
			i = 1;

			for (Table s : scripts) {
				getTableColumns(meta, s);
				getTablePrimaryKeys(meta, s);
				getTableForeignKeys(meta, s);
				loading.setProgressBarValue(i);
				i++;
			}
			loading.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
				t.setForeignKey(foreignTable, foreignKey);
				for (Column c : t.getColumns()) {
					if (c.getName().equals(foreignKey)) {
						c.setForeignKey(true);
						break;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

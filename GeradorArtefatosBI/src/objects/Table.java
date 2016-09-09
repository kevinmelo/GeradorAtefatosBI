package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public class Table implements Serializable {

	private String name = "";
	private String schema = "";
	private String primaryKey = "";
	private boolean isCube = false;
	private List<Column> columns = new ArrayList<>();
	private HashMap<String, String> foreignKey = new HashMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isCube() {
		return isCube;
	}

	public void setCube() {
		this.isCube = (isCube ? false : true);
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Column getColumn(int index) {
		return columns.get(index);
	}

	public void setColumn(Column column) {
		this.columns.add(column);
	}

	public void removeColumn(int index) {
		columns.remove(index);
	}

	public HashMap<String, String> getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(HashMap<String, String> foreignKey) {
		this.foreignKey = foreignKey;
	}

	public void setForeignKey(String foreignTable, String foreignKey) {
		this.foreignKey.put(foreignTable, foreignKey);
	}

	@Override
	public String toString() {
		return name;
	}
}

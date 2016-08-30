package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Table implements Serializable {

	private String tabela = "";
	private String schema = "";
	private boolean isCube = false;
	private List<Column> columns = new ArrayList<>();

	public boolean isCube() {
		return isCube;
	}

	public void setCube() {
		this.isCube = (isCube ? false : true);
	}

	public List<Column> getColumns() {
		return columns;
	}

	public Column getColumn(int index) {
		return columns.get(index);
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void setColumn(Column column) {
		this.columns.add(column);
	}

	public void removeColumn(int index) {
		columns.remove(index);
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	@Override
	public String toString() {
		return tabela;
	}
}

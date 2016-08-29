package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Table implements Serializable {

	private String tabela = "";
	private String schema = "";
	private boolean isCube = false;
	private List<String> columns = new ArrayList<>();

	public boolean isCube() {
		return isCube;
	}

	public void setCube() {
		this.isCube = (isCube ? false : true);
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
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

	public List<String> getColunas() {
		return columns;
	}

	public void setColunas(String coluna) {
		this.columns.add(coluna);
	}

	public void setColunas(List<String> colunas) {
		this.columns = colunas;
	}

	public String getColuna(int index) {
		return columns.get(index);
	}

	@Override
	public String toString() {
		return tabela;
	}
}

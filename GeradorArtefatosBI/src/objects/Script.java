package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Script implements Serializable {

	private String tabela = "";
	private String schema = "";
	private List<String> colunas = new ArrayList<>();

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
		return colunas;
	}

	public void setColunas(String coluna) {
		this.colunas.add(coluna);
	}

	public void setColunas(List<String> colunas) {
		this.colunas = colunas;
	}

	public String getColuna(int index) {
		return colunas.get(index);
	}

	@Override
	public String toString() {
		return tabela;
	}

}

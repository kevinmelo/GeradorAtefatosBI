package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Script implements Serializable {

	private String tabela = "";
	private List<String> colunas = new ArrayList<>();

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
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

}

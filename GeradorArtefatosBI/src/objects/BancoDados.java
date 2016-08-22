package objects;
import java.io.Serializable;

@SuppressWarnings("serial")
public class BancoDados implements Serializable {

	private String nome = "";
	private String jdbcDrive = "";
	private String jdbcUrl = "";
	private String usuario = "";
	private String senha = "";

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getJdbcDrive() {
		return jdbcDrive;
	}

	public void setJdbcDrive(String jdbcDrive) {
		this.jdbcDrive = jdbcDrive;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return nome.toString();
	}

}

/**
 * @author Paulo Pocinho
 * @since 09-02-2017
 */

public class Formando {

	private String nome;
	private int idade;
	private String genero;
	private String id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Formando [nome=" + nome + ", idade=" + idade + ", genero=" + genero + ", id=" + id + "]";
	}

	public Formando(String nome, int idade, String genero, String id) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.id = id;
	}

}

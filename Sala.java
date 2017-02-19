/**
 * @author Paulo Pocinho
 * @since 09-02-2017
 */

public class Sala {

	private int capacidade;
	private int inscritos;
	private Formando[] lista;
	private int numero;
	private String turma;

	private int encontrar(String idFormando) {
		int resultado = -1;
		for (int i = 0; i < inscritos; ++i) {
			if (lista[i].getId().contentEquals(idFormando)) {
				resultado = i;
				break;
			}
		}
		return resultado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numSala) {
		this.numero = numSala;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public int getInscritos() {
		return inscritos;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getLista() {
		String listaDeFormandos = "";
		for (int i = 0; i < inscritos; ++i) {
			listaDeFormandos = listaDeFormandos + lista[i].getNome() + "\n";
		}
		return listaDeFormandos;
	}

	public void adicionarFormando(Formando formando) {
		if (inscritos < capacidade) {
			String id = formando.getId();
			int f = encontrar(id);
			if (f < 0) {
				lista[inscritos] = formando;
				inscritos++;
			} else {
				throw new IllegalArgumentException("Formando com id " + id + " já existe na sala " + numero + ".");
			}
		} else {
			throw new IllegalArgumentException("Sala cheia.");
		}
	}

	public void removerFormando(String id) {
		int i = encontrar(id);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + " na sala " + numero + ".");
		} else {
			inscritos--;
			lista[i] = lista[inscritos];
			lista[inscritos] = null;
		}
	}

	public void alterarFormando(String idAntigo, String nome, int idade, String genero, String idNovo) {
		int i = encontrar(idAntigo);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + idAntigo + " na sala " + this.getNumero() + ".");
		} else {
			lista[i].setNome(nome);
			lista[i].setIdade(idade);
			lista[i].setGenero(genero);
			lista[i].setId(idNovo);
		}
	}

	public Sala(int numero, int capacidade, String turma) {
		this.capacidade = capacidade;
		this.lista = new Formando[capacidade];
		this.inscritos = 0;
		this.numero = numero;
		this.turma = turma;
	}

	@Override
	public String toString() {
		return "Sala [numero=" + numero + ", capacidade=" + capacidade + ", inscritos=" + inscritos + ", turma=" + turma
				+ ", lista=" + this.getLista() + "]";
	}

}

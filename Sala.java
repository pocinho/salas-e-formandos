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

	public void adicionaFormando(Formando formando) {
		if (inscritos < capacidade) {
			lista[inscritos] = formando;
			inscritos++;
		} else {
			throw new IllegalArgumentException("Sala cheia.");
		}
	}

	public void removeFormando(String identificacao) {
		int resultado = -1;
		for (int i = 0; i < inscritos; ++i) {
			if (lista[i].getId().contentEquals(identificacao)) {
				resultado = i;
				break;
			}
		}

		if (resultado < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + identificacao + " na sala " + numero + ".");
		} else {
			inscritos--;
			lista[resultado] = lista[inscritos];
			lista[inscritos] = null;
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

/**
 * @author Paulo Pocinho
 * @since  09-02-2017
 */

public class Escola {

	private Formando[] formandos;
	private int formandosRegistados;
	private Sala[] salas;
	private int salasUtilizadas;
	private String nome;

	private int encontrar(Formando[] formandos, String id) {
		int resultado = -1;
		for (int i = 0; i < formandosRegistados; ++i) {
			if (formandos[i].getId().contentEquals(id)) {
				resultado = i;
				break;
			}
		}
		return resultado;
	}

	private int encontrar(Sala[] salas, int numero) {
		int resultado = -1;
		for (int i = 0; i < salasUtilizadas; ++i) {
			if (salas[i].getNumero() == numero) {
				resultado = i;
				break;
			}
		}
		return resultado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void adicionarSala(int numero, int capacidade, String turma) {
		if (salasUtilizadas < salas.length) {
			int s = encontrar(salas, numero);
			if (s < 0) {
				salas[salasUtilizadas] = new Sala(numero, capacidade, turma);
				salasUtilizadas++;
			} else {
				throw new IllegalArgumentException("Sala " + numero + " já existe.");
			}
		} else {
			throw new IllegalArgumentException("Não é possível adicionar mais salas.");
		}
	}

	public void removerSala(int numero) {
		int i = encontrar(salas, numero);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numero + ".");
		} else {
			salasUtilizadas--;
			salas[i] = salas[salasUtilizadas];
			salas[salasUtilizadas] = null;
		}
	}

	public void adicionarFormando(String nome, int idade, String genero, String id) {
		if (formandosRegistados < formandos.length) {
			int f = encontrar(formandos, id);
			if (f < 0) {
				formandos[formandosRegistados] = new Formando(nome, idade, genero, id);
				formandosRegistados++;
			} else {
				throw new IllegalArgumentException("Formando com id " + id + " já existe.");
			}
		} else {
			throw new IllegalArgumentException("Não é possível adicionar mais formandos.");
		}
	}

	public void removerFormando(String id) {
		int i = encontrar(formandos, id);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else {
			formandosRegistados--;
			formandos[i] = formandos[formandosRegistados];
			formandos[formandosRegistados] = null;

			//TODO alterar método força bruta para actualizar o formando na sala
			//     como não temos ainda base de dados é dificil verificar consistencia
			try {
				for (int s = 0; s < salasUtilizadas; ++s) {
					salas[s].removerFormando(id);
				}
			} catch (Exception e) {
				//System.out.println("A actualizar salas.");
			}
		}
	}

	public void alocarFormando(String id, int numeroDeSala) {
		int f = encontrar(formandos, id);
		int s = encontrar(salas, numeroDeSala);
		if (f < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else if (s < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numeroDeSala + ".");
		} else {
			salas[s].adicionarFormando(formandos[f]);
		}
	}

	public void desalocarFormando(String id, int numeroDeSala) {
		int f = encontrar(formandos, id);
		int s = encontrar(salas, numeroDeSala);
		if (f < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else if (s < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numeroDeSala + ".");
		} else {
			salas[s].removerFormando(id);
		}
	}

	public void consultarSala(int numero) {
		int i = encontrar(salas, numero);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numero + ".");
		} else {
			System.out.println(salas[i].toString());
		}
	}

	public void consultarFormando(String id) {
		int i = encontrar(formandos, id);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else {
			System.out.println(formandos[i].toString());
		}
	}

	public void alterarSala(int numeroAntigo, int numeroNovo, String turma) {
		int sa = encontrar(salas, numeroAntigo);
		int sn = encontrar(salas, numeroNovo);
		if (sa < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numeroAntigo + ".");
		} else if (sn < 0) {
			salas[sa].setNumero(numeroNovo);
			salas[sa].setTurma(turma);
		} else {
			throw new IllegalArgumentException("Sala " + numeroNovo + " já existe.");
		}
	}

	public void alterarFormando(String idAntigo, String nome, int idade, String genero, String idNovo) {
		int i = encontrar(formandos, idAntigo);
		if (i < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + idAntigo + ".");
		} else {
			formandos[i].setId(idNovo);
			formandos[i].setNome(nome);
			formandos[i].setIdade(idade);
			formandos[i].setGenero(genero);

			//TODO alterar método força bruta para actualizar o formando na sala
			//     como não temos ainda base de dados é dificil verificar consistencia
			try {
				for (int s = 0; s < salasUtilizadas; ++s) {
					salas[s].alterarFormando(idAntigo, nome, idade, genero, idNovo);
				}
			} catch (Exception e) {
				//System.out.println("A actualizar salas.");
			}
		}
	}

	public Escola(int salas, int formandos, String nome) {
		this.salas = new Sala[salas];
		this.formandos = new Formando[formandos];
		this.salasUtilizadas = 0;
		this.formandosRegistados = 0;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Escola [nome=" + nome + ", numero máximo de formandos=" + formandos.length + ", formandos registados=" + formandosRegistados
				+ ", total salas=" + salas.length + ", salas utilizadas=" + salasUtilizadas + ", ]";
	}


}

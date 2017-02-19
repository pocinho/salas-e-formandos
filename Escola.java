/**
 * @author Paulo Pocinho
 * @since  09-02-2017
 */

// Notas:
// Todos os formandos não alocados ficam na sala 0 no Registo de formandos.
// Por isso neste programa, não convém criar sala com numero zero!

public class Escola {

	private Registo[] formandos;
	private int formandosRegistados;
	private Sala[] salas;
	private int salasUtilizadas;
	private String nome;

	private int encontrar(Registo[] formandos, String id) {
		int resultado = -1;
		for (int i = 0; i < formandosRegistados; ++i) {
			if (formandos[i].getFormando().getId().contentEquals(id)) {
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
		if (numero == 0) {
			throw new IllegalArgumentException("Não é possível criar a sala zero. Reservada para a entrada.");
		} else {
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
	}

	public void removerSala(int numero) {
		int s = encontrar(salas, numero);
		if (s < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numero + ".");
		} else {
			// Actualizar registo
			for (int i = 0; i < formandosRegistados; ++i) {
				if (formandos[i].getSala() == numero) {
					formandos[i].setSala(0);
				}
			}
			// Actualizar salas
			salasUtilizadas--;
			salas[s] = salas[salasUtilizadas];
			salas[salasUtilizadas] = null;
		}
	}

	public void adicionarFormando(String nome, int idade, String genero, String id) {
		if (formandosRegistados < formandos.length) {
			int f = encontrar(formandos, id);
			if (f < 0) {
				formandos[formandosRegistados] = new Registo(nome, idade, genero, id);
				formandosRegistados++;
			} else {
				throw new IllegalArgumentException("Formando com id " + id + " já existe.");
			}
		} else {
			throw new IllegalArgumentException("Não é possível adicionar mais formandos.");
		}
	}

	public void removerFormando(String id) {
		int f = encontrar(formandos, id);
		if (f < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else {
			// Actualizar salas utilizando o registo
			int n = formandos[f].getSala();
			if (n != 0) {
				int s = encontrar(salas, n);
				if (s >= 0) {
					salas[s].removerFormando(id);
				}
			}
			// Actualizar lista de formandos
			formandosRegistados--;
			formandos[f] = formandos[formandosRegistados];
			formandos[formandosRegistados] = null;
		}
	}

	public void alocarFormando(String id, int numeroDeSala) {
		int f = encontrar(formandos, id);
		int num = encontrar(salas, numeroDeSala);
		if (f < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else if (num < 0) {
			throw new IllegalArgumentException("Não é possível encontrar a sala " + numeroDeSala + ".");
		} else {
			// Actualizar registo
			// nota:
			// Se a sala estiver cheia, o formando não é alocado e o registo ficaria incorrecto.
			int inscritos = salas[num].getInscritos();
			int capacidade = salas[num].getCapacidade();
			if (inscritos < capacidade) {
				// Verificar se o formando já estava alocado:
				int s = formandos[f].getSala();
				if (s != 0) {
					// Formando vai ser alocado noutra sala:
					int i = encontrar(salas, s);
					salas[i].removerFormando(id);
				}
				formandos[f].setSala(numeroDeSala);
				salas[num].adicionarFormando(formandos[f].getFormando());
			} else {
				throw new IllegalArgumentException("Sala cheia.");
			}

		}
	}

	public void desalocarFormando(String id) {
		int f = encontrar(formandos, id);
		if (f < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + id + ".");
		} else {
			int num = formandos[f].getSala();
			if (num != 0) {
				int s = encontrar(salas, num);
				if (s >= 0) {
					salas[s].removerFormando(id);
				}
			}
			formandos[f].setSala(0);
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
			System.out.println(formandos[i].getFormando().toString());
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
			// Actualizar registo
			for (int i = 0; i < formandosRegistados; ++i) {
				if (formandos[i].getSala() == numeroAntigo) {
					formandos[i].setSala(numeroNovo);
				}
			}
		} else {
			throw new IllegalArgumentException("Sala " + numeroNovo + " já existe.");
		}
	}

	public void alterarFormando(String idAntigo, String nome, int idade, String genero, String idNovo) {
		int ia = encontrar(formandos, idAntigo);
		int in = encontrar(formandos, idNovo);
		if (ia < 0) {
			throw new IllegalArgumentException("Não é possível encontrar o formando com id " + idAntigo + ".");
		} else if (in < 0) {
			// Actualizar salas utilizando o registo
			int n = formandos[ia].getSala();
			if (n != 0) {
				int s = encontrar(salas, n);
				if (s >= 0) {
					salas[s].alterarFormando(idAntigo, nome, idade, genero, idNovo);
				}
			}
			// Actualizar registo do formando
			formandos[ia].getFormando().setId(idNovo);
			formandos[ia].getFormando().setNome(nome);
			formandos[ia].getFormando().setIdade(idade);
			formandos[ia].getFormando().setGenero(genero);
		} else {
			throw new IllegalArgumentException("Formando com id " + idNovo + " já existe.");
		}
	}

	public Escola(int salas, int formandos, String nome) {
		this.salas = new Sala[salas];
		this.formandos = new Registo[formandos];
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

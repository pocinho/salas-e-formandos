/**
 * @author Paulo Pocinho
 * @since  09-02-2017
 */

import java.io.IOException;
import java.util.Scanner;

public class Entrada {

	public static void main(String[] args) {

		int op = 0;
		Scanner s = new Scanner(System.in);

		final int salasPossiveis = 50;
		final int formandosPossiveis = 200;
		final String nomeEscola = "atec";

		Escola e = new Escola(salasPossiveis, formandosPossiveis, nomeEscola);

		do {
			menu();
			op = s.nextInt();
			s.nextLine();

			switch (op) {
			case 1:
				criarSala(s, e);
				break;
			case 2:
				criarFormando(s, e);
				break;
			case 3:
				removerSala(s, e);
				break;
			case 4:
				removerFormando(s, e);
				break;
			case 5:
				alocarFormando(s, e);
				break;
			case 6:
				desalocarFormando(s, e);
				break;
			case 7:
				consultarSala(s, e);
				break;
			case 8:
				consultarFormando(s, e);
				break;
			case 9:
				alterarSala(s, e);
				break;
			case 10:
				alterarFormando(s, e);
				break;
			case 0:
				System.out.println("Adeus, volte sempre!");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}

		} while (op != 0);

		s.close();
	}

	public static void removerFormando(Scanner s, Escola escola) {
		System.out.println("Introduza o documento de identificação do formando:");
		try {
			escola.removerFormando(s.nextLine());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void removerSala(Scanner s, Escola escola) {
		System.out.println("Introduza o numero de sala:");
		int numeroDeSala = s.nextInt();
		s.nextLine();
		try {
			escola.removerSala(numeroDeSala);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alterarSala(Scanner s, Escola escola) {
		System.out.println("Introduza o numero de sala:");
		int numeroAntigo = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o novo numero de sala:");
		int numeroNovo = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o nome da turma:");
		String turma = s.nextLine();
		try {
			escola.alterarSala(numeroAntigo, numeroNovo, turma);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alterarFormando(Scanner s, Escola escola) {
		System.out.println("Introduza o numero do documento de identificação do formando:");
		String idAntigo = s.nextLine();
		System.out.println("Introduza o novo numero do documento de identificação:");
		String idNovo = s.nextLine();
		System.out.println("Introduza o nome:");
		String nome = s.nextLine();
		System.out.println("Introduza a idade:");
		int idade = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o genero:");
		String genero = s.nextLine();
		try {
			escola.alterarFormando(idAntigo, nome, idade, genero, idNovo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void consultarFormando(Scanner s, Escola escola) {
		System.out.println("Introduza o numero do documento de identificação do formando:");
		try {
			escola.consultarFormando(s.nextLine());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void consultarSala(Scanner s, Escola escola) {
		System.out.println("Introduza o numero de sala:");
		int numero = s.nextInt();
		s.nextLine();
		try {
			escola.consultarSala(numero);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void desalocarFormando(Scanner s, Escola escola) {
		System.out.println("Introduza o numero do documento de identificação do formando:");
		String id = s.nextLine();
		System.out.println("Introduza o numero de sala:");
		int numeroDeSala = s.nextInt();
		s.nextLine();
		try {
			escola.desalocarFormando(id, numeroDeSala);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void alocarFormando(Scanner s, Escola escola) {
		System.out.println("Introduza o numero do documento de identificação do formando:");
		String id = s.nextLine();
		System.out.println("Introduza o numero de sala:");
		int numeroDeSala = s.nextInt();
		s.nextLine();
		try {
			escola.alocarFormando(id, numeroDeSala);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void criarFormando(Scanner s, Escola escola) {
		System.out.println("Introduza o nome:");
		String nome = s.nextLine();
		System.out.println("Introduza a idade:");
		int idade = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o genero:");
		String genero = s.nextLine();
		System.out.println("Introduza o numero do documento de identificação:");
		String id = s.nextLine();
		try {
			escola.adicionarFormando(nome, idade, genero, id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void criarSala(Scanner s, Escola escola) {
		System.out.println("Introduza o numero da sala:");
		int numero = s.nextInt();
		s.nextLine();
		System.out.println("Introduza a capacidade da sala " + numero + ":");
		int capacidade = s.nextInt();
		s.nextLine();
		System.out.println("Introduza o nome da turma da sala " + numero + ":");
		String turma = s.nextLine();
		try {
			escola.adicionarSala(numero, capacidade, turma);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		pausa();
	}

	public static void pausa() {
		System.out.println("Prima ENTER para continuar.");
		try {
			System.in.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void menu() {
		System.out.println("Opções disponíveis:");
		System.out.println(" (1) Criar sala");
		System.out.println(" (2) Criar formando");
		System.out.println(" (3) Remover sala");
		System.out.println(" (4) Remover formando");
		System.out.println(" (5) Alocar formando a uma sala");
		System.out.println(" (6) Desalocar formando de uma sala");
		System.out.println(" (7) Consultar os dados de uma sala");
		System.out.println(" (8) Consultar os dados de um formando");
		System.out.println(" (9) Alterar os dados de uma sala");
		System.out.println("(10) Alterar os dados de um formando");
		System.out.println(" (0) Sair");
		System.out.println("Introduza uma opção: ");
	}
}


public class Registo {

	private Formando formando;
	private Integer sala;

	public Formando getFormando() {
		return formando;
	}

	public void setFormando(Formando formando) {
		this.formando = formando;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public Registo(Formando formando, Integer sala) {
		this.formando = formando;
		this.sala = sala;
	}

	public Registo(Formando formando) {
		this.formando = formando;
		this.sala = 0;
	}

	public Registo(String nome, int idade, String genero, String id, int sala) {
		this.formando = new Formando(nome, idade, genero, id);
		this.sala = sala;
	}

	public Registo(String nome, int idade, String genero, String id) {
		this.formando = new Formando(nome, idade, genero, id);
		this.sala = 0;
	}

	@Override
	public String toString() {
		return "Registo [formando=" + formando + ", sala=" + sala + "]";
	}
}

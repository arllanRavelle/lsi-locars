package br.com.locars.model;

public class Funcionario extends Pessoa {

	private int id;
	private Usuario usuario;
	private String carteiraTrabalho;
	private boolean selecao;

	public Funcionario(String nome, String cpf, String rg, String contato,
			Usuario usuario, String carteiraTrabalho) {

		super(nome, cpf, rg, contato);
		this.usuario = usuario;
		this.carteiraTrabalho = carteiraTrabalho;

	}

	public Funcionario() {
		super();
	}

	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isSelecao() {
		return selecao;
	}

	public void setSelecao(boolean selecao) {
		this.selecao = selecao;
	}

}

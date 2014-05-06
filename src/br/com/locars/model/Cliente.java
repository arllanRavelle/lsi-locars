package br.com.locars.model;

public class Cliente extends Pessoa {

	private int id;
	private String cnh;
	private boolean selecao;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String rg, String contato,
			String cnh) {
		super(nome, cpf, rg, contato);
		this.cnh = cnh;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public boolean isSelecao() {
		return selecao;
	}

	public void setSelecao(boolean selecao) {
		this.selecao = selecao;
	}

}

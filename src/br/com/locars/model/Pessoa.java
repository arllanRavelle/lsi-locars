package br.com.locars.model;

public class Pessoa {

	private String nome;
	private String cpf;
	private String rg;
	private String contato;

	public Pessoa() {
	}

	public Pessoa(String nome, String cpf, String rg, String contato) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.contato = contato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

}

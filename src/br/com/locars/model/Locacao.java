package br.com.locars.model;

import java.util.Calendar;

public class Locacao {

	private Cliente cliente;
	private Veiculo veiculo;
	private Calendar dataIni;
	private Calendar dataFim;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Calendar getDataIni() {
		return dataIni;
	}

	public void setDataIni(Calendar dataIni) {
		this.dataIni = dataIni;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

}

package br.com.locars.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.locars.model.Cliente;

public class ClienteTabelModel extends AbstractTableModel {

	private static final int NOME = 0;
	private static final int CPF = 1;
	private static final int RG = 2;
	private static final int CNH = 3;
	private static final int CONTATO = 4;
	private static final int SELECAO = 5;

	private List<Cliente> linhas;

	private String[] colunas = new String[] { "NOME ", "CPF", "RG", "CNH",
			"CONTATO", "" };

	public ClienteTabelModel() {
		this.linhas = new ArrayList();
	}

	public ClienteTabelModel(List<Cliente> clientes) {
		this.linhas = new ArrayList<Cliente>(clientes);
	}

	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.linhas.size();
	}

	public Class<?> getColumnClass(int columnIndex) {
		// retorna o tipo de dado, para cada coluna
		switch (columnIndex) {
		case NOME:
			return String.class;
		case CPF:
			return String.class;
		case RG:
			return String.class;
		case CNH:
			return String.class;
		case CONTATO:
			return String.class;
		case SELECAO:
			return Boolean.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// retorna o valor conforme a coluna e linha

		// pega o dados corrente da linha
		Cliente cliente = linhas.get(rowIndex);

		// retorna o valor da coluna
		switch (columnIndex) {
		case NOME:
			return cliente.getNome();
		case CPF:
			return cliente.getCpf();
		case RG:
			return cliente.getRg();
		case CNH:
			return cliente.getCnh();
		case CONTATO:
			return cliente.getContato();
		case SELECAO:
			return cliente.isSelecao();
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// metodo identifica qual coluna é editavel

		// só iremos editar a coluna BENEFICIO,
		// que será um checkbox por ser boolean
		if (columnIndex == SELECAO)
			return true;

		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = linhas.get(rowIndex);

		if (columnIndex == SELECAO) {
			cliente.setSelecao(((boolean) aValue));
		}
	}

	// Métodos abaixo são para manipulação de dados

	/**
	 * retorna o valor da linha indicada
	 * 
	 * @param rowIndex
	 * @return
	 */
	public Cliente getValue(int rowIndex) {
		return linhas.get(rowIndex);
	}

	/**
	 * retorna o indice do objeto
	 * 
	 * @param empregado
	 * @return
	 */
	public int indexOf(Cliente cliente) {
		return linhas.indexOf(cliente);
	}

	/**
	 * add um empregado á lista
	 * 
	 * @param empregado
	 */
	public void onAdd(Cliente cliente) {
		linhas.add(cliente);
		fireTableRowsInserted(indexOf(cliente), indexOf(cliente));
	}

	/**
	 * add uma lista de empregados
	 * 
	 * @param dadosIn
	 */
	public void onAddAll(List<Cliente> dadosIn) {
		linhas.addAll(dadosIn);
		fireTableDataChanged();
	}

	/**
	 * remove um registro da lista, através do indice
	 * 
	 * @param rowIndex
	 */
	public void onRemove(int rowIndex) {
		linhas.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	/**
	 * remove um registro da lista, através do objeto
	 * 
	 * @param empregado
	 */
	public void onRemove(Cliente cliente) {
		linhas.remove(cliente);
		fireTableRowsDeleted(indexOf(cliente), indexOf(cliente));
	}

	/**
	 * remove todos registros da lista
	 */
	public void onRemoveAll() {
		linhas.clear();
		fireTableDataChanged();
	}

}

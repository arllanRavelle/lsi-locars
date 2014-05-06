package br.com.locars.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.locars.model.Veiculo;

public class VeiculoTableModel extends AbstractTableModel {

	private static final int MODELO = 0;
	private static final int FABRICANTE = 1;
	private static final int COR = 2;
	private static final int ANO = 3;
	private static final int PLACA = 4;
	private static final int CHASSI = 5;
	private static final int RENAVAN = 6;
	private static final int SELECAO = 7;

	private List<Veiculo> linhas;

	private String[] colunas = new String[] { "MODELO", "FABRICANTE ", "COR",
			"ANO", "PLACA", "CHASSI", "RENAVAN", "" };

	public VeiculoTableModel() {
		this.linhas = new ArrayList();
	}

	public VeiculoTableModel(List<Veiculo> veiculos) {
		this.linhas = new ArrayList<Veiculo>(veiculos);
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
		case MODELO:
			return String.class;
		case FABRICANTE:
			return String.class;
		case COR:
			return String.class;
		case ANO:
			return String.class;
		case PLACA:
			return String.class;
		case CHASSI:
			return String.class;
		case RENAVAN:
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
		Veiculo veiculo = linhas.get(rowIndex);

		// retorna o valor da coluna
		switch (columnIndex) {
		case MODELO:
			return veiculo.getModelo();
		case FABRICANTE:
			return veiculo.getFabricante();
		case COR:
			return veiculo.getCor();
		case ANO:
			return veiculo.getAnoFab();
		case PLACA:
			return veiculo.getPlaca();
		case CHASSI:
			return veiculo.getChassi();
		case RENAVAN:
			return veiculo.getRenavan();
		case SELECAO:
			return veiculo.isSelecao();
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
		Veiculo veiculo = linhas.get(rowIndex);

		if (columnIndex == SELECAO) {
			veiculo.setSelecao(((boolean) aValue));
		}
	}

	// Métodos abaixo são para manipulação de dados

	/**
	 * retorna o valor da linha indicada
	 * 
	 * @param rowIndex
	 * @return
	 */
	public Veiculo getValue(int rowIndex) {
		return linhas.get(rowIndex);
	}

	/**
	 * retorna o indice do objeto
	 * 
	 * @param empregado
	 * @return
	 */
	public int indexOf(Veiculo veiculo) {
		return linhas.indexOf(veiculo);
	}

	/**
	 * add um empregado á lista
	 * 
	 * @param empregado
	 */
	public void onAdd(Veiculo veiculo) {
		linhas.add(veiculo);
		fireTableRowsInserted(indexOf(veiculo), indexOf(veiculo));
	}

	/**
	 * add uma lista de empregados
	 * 
	 * @param dadosIn
	 */
	public void onAddAll(List<Veiculo> dadosIn) {
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
	public void onRemove(Veiculo veiculo) {
		linhas.remove(veiculo);
		fireTableRowsDeleted(indexOf(veiculo), indexOf(veiculo));
	}

	/**
	 * remove todos registros da lista
	 */
	public void onRemoveAll() {
		linhas.clear();
		fireTableDataChanged();
	}

}

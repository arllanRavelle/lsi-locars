package br.com.locars.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.locars.dao.VeiculoDao;
import br.com.locars.model.Veiculo;

/**
 * classe JFrame "Formul�rio Java", U.I-User Interface
 * 
 * @author Roberto Silva
 */
public class JanelaVeiculo extends JDialog {

	private JTable table;
	private VeiculoTableModel tableModel;

	private JButton botaoAdd = new JButton("Adicionar Cliente", new ImageIcon(
			"D://icons/add1_32x32.png"));
	private JButton botaoRemover = new JButton("Remover Cliente",
			new ImageIcon("D://icons/delete1_32x32.png"));
	private JButton botaoEditar = new JButton("Editar Cliente", new ImageIcon(
			"D://icons/edit1_32x32.png"));

	public JanelaVeiculo(List<Veiculo> veiculos) {
		inicializar();
		tableModel.onAddAll(veiculos);
	}

	private void inicializar() {
		this.setTitle("Cadastro de Veiculos");
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(new JScrollPane(getJTable()));
		this.addBotoes();
	}

	private VeiculoTableModel getTabelModel() {
		if (tableModel == null) {
			tableModel = new VeiculoTableModel();
		}
		return this.tableModel;
	}

	private JTable getJTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(getTabelModel());
			// barra de rolagem
			table.setPreferredScrollableViewportSize(new Dimension(900, 480));
			// auto ajuste na altura da tabela
			table.setFillsViewportHeight(true);
			// selecionar somente uma linha
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dimencionarColunas(table);
		}
		return table;
	}

	private void addBotoes() {

		getBotaoAdd().addActionListener(botaoAddListener);
		getBotaoRemover().addActionListener(botaoRemoverListener);
		getBotaoEditar().addActionListener(botaoEditarListener);

		this.add(getBotaoAdd());
		this.add(getBotaoRemover());
		this.add(getBotaoEditar());

	}

	private JButton getBotaoAdd() {
		return this.botaoAdd;
	}

	private JButton getBotaoRemover() {
		return this.botaoRemover;
	}

	private JButton getBotaoEditar() {
		return this.botaoEditar;
	}

	private void dimencionarColunas(JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(180);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(5);
	}

	// evento p/adicionar um registro � tabela
	private ActionListener botaoAddListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CadastroVeiculo cd = new CadastroVeiculo(tableModel);
			cd.setVisible(true);
		}
	};

	// evento p/remover um registro da tabela
	private ActionListener botaoRemoverListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() != -1
					&& table.getSelectedRow() < tableModel.getRowCount()) {

				int resposta = new JOptionPane().showConfirmDialog(null,
						"TEM CERTEZA QUE DESEJA REMOVER ESSE CLIENTE?",
						"REMOVER CLIENTE", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, new ImageIcon(
								"D://icons/question1_32x32.png"));
				if (resposta == 0) {

					Veiculo veiculo = tableModel.getValue(table
							.getSelectedRow());
					VeiculoDao cd = new VeiculoDao();
					cd.deletarCliente(veiculo);
					tableModel.onRemove(table.getSelectedRow());

					JOptionPane.showMessageDialog(null,
							"CLIENTE EXCLU�DO COM SUCESSO!", "OK",
							JOptionPane.WARNING_MESSAGE, new ImageIcon(
									"D://icons/ok1_32x32.png"));
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Selecione um registro da tabela!");
			}
		}
	};

	// evento p/adicionar um v�rios registros � tabela
	private ActionListener botaoEditarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() != -1
					&& table.getSelectedRow() < tableModel.getRowCount()) {
				Veiculo veiculo = tableModel.getValue(table.getSelectedRow());
				CadastroVeiculo cv = new CadastroVeiculo(tableModel, veiculo);
				cv.setVisible(true);
			}
		}
	};

}

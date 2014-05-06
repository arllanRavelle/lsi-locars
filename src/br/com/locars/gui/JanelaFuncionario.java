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

import br.com.locars.dao.FuncionarioDao;
import br.com.locars.model.Funcionario;

public class JanelaFuncionario extends JDialog {

	private JTable table;
	private FuncionarioTabelModel tableModel;

	private JButton botaoAdd = new JButton("Adicionar Funcionario",
			new ImageIcon("D://icons/add1_32x32.png"));
	private JButton botaoRemover = new JButton("Remover Funcionario",
			new ImageIcon("D://icons/delete1_32x32.png"));
	private JButton botaoEditar = new JButton("Editar Funcionario",
			new ImageIcon("D://icons/edit1_32x32.png"));

	public JanelaFuncionario(List<Funcionario> funcionarios) {
		inicializar();
		tableModel.onAddAll(funcionarios);
	}

	private void inicializar() {
		this.setTitle("Cadastro de Funcionarios");
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(new JScrollPane(getJTable()));
		this.addBotoes();
	}

	private FuncionarioTabelModel getTabelModel() {
		if (tableModel == null) {
			tableModel = new FuncionarioTabelModel();
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
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(5);
	}

	// evento p/adicionar um registro á tabela
	private ActionListener botaoAddListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CadastroFuncionario cf = new CadastroFuncionario(tableModel);
			cf.setVisible(true);
		}
	};

	// evento p/remover um registro da tabela
	private ActionListener botaoRemoverListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() != -1
					&& table.getSelectedRow() < tableModel.getRowCount()) {

				int resposta = new JOptionPane().showConfirmDialog(null,
						"TEM CERTEZA QUE DESEJA REMOVER ESSE FUNCIONARIO?",
						"REMOVER FUNCIONARIO", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, new ImageIcon(
								"D://icons/question1_32x32.png"));
				if (resposta == 0) {
					Funcionario funcionario = tableModel.getValue(table
							.getSelectedRow());
					FuncionarioDao cd = new FuncionarioDao();
					cd.deletarFuncionario(funcionario);
					tableModel.onRemove(table.getSelectedRow());

					JOptionPane.showMessageDialog(null,
							"FUNCIONARIO EXCLUÍDO COM SUCESSO!", "OK",
							JOptionPane.WARNING_MESSAGE, new ImageIcon(
									"D://icons/ok1_32x32.png"));
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Selecione um registro da tabela!");
			}
		}
	};

	// evento p/adicionar um vários registros á tabela
	private ActionListener botaoEditarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() != -1
					&& table.getSelectedRow() < tableModel.getRowCount()) {
				Funcionario funcionario = tableModel.getValue(table
						.getSelectedRow());
				CadastroFuncionario cf = new CadastroFuncionario(tableModel,
						funcionario);
				cf.setVisible(true);
			}
		}
	};

}

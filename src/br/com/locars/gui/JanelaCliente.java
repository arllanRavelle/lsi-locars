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

import br.com.locars.dao.ClienteDao;
import br.com.locars.model.Cliente;

/**
 * classe JFrame "Formulário Java", U.I-User Interface
 * 
 * @author Roberto Silva
 */
public class JanelaCliente extends JDialog {

	private JTable table;
	private ClienteTabelModel tableModel;

	private JButton botaoAdd = new JButton("Adicionar Cliente", new ImageIcon(
			"D://icons/add1_32x32.png"));
	private JButton botaoRemover = new JButton("Remover Cliente",
			new ImageIcon("D://icons/delete1_32x32.png"));
	private JButton botaoEditar = new JButton("Editar Cliente", new ImageIcon(
			"D://icons/edit1_32x32.png"));

	public JanelaCliente(List<Cliente> clientes) {
		inicializar();
		tableModel.onAddAll(clientes);
	}

	private void inicializar() {
		this.setTitle("Cadastro de Clientes");
		this.setLayout(new FlowLayout());
		this.setSize(new Dimension(1000, 600));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(new JScrollPane(getJTable()));
		this.addBotoes();
	}

	private ClienteTabelModel getTabelModel() {
		if (tableModel == null) {
			tableModel = new ClienteTabelModel();
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
			CadastroCliente cd = new CadastroCliente(tableModel);
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
					Cliente cliente = tableModel.getValue(table
							.getSelectedRow());
					ClienteDao cd = new ClienteDao();
					cd.deletarCliente(cliente);
					tableModel.onRemove(table.getSelectedRow());

					JOptionPane.showMessageDialog(null,
							"CLIENTE EXCLUÍDO COM SUCESSO!", "OK",
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
				Cliente cliente = tableModel.getValue(table.getSelectedRow());
				CadastroCliente cd = new CadastroCliente(tableModel, cliente);
				cd.setVisible(true);
			}
		}
	};

}

package br.com.locars.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.locars.dao.ClienteDao;
import br.com.locars.model.Cliente;

public class CadastroCliente extends JDialog {

	private JLabel labelDadosPessoais = new JLabel(
			"------------------------ INFORMAÇÕES PESSOAIS ------------------------");
	private JLabel labelNome = new JLabel("*Nome:");
	private JLabel labelCPF = new JLabel("*CPF:");
	private JLabel labelRG = new JLabel("RG:");
	private JLabel labelContato = new JLabel("Contato:");
	private JLabel labelCNH = new JLabel("*CNH:");

	private JTextField campoNome = new JTextField();
	private JTextField campoCPF = new JTextField();
	private JTextField campoRG = new JTextField();
	private JTextField campoContato = new JTextField();
	private JTextField campoCNH = new JTextField();

	private JButton botaoSalvar = new JButton("Salvar", new ImageIcon(
			"D://icons/save2_16x16.png"));
	private JButton botaoCancelar = new JButton("Cancelar", new ImageIcon(
			"D://icons/cancel1_16x16.png"));

	private ClienteTabelModel tbModel;

	// GAMBIARRA
	private Cliente gambiarra;

	public CadastroCliente(ClienteTabelModel tableModel, Cliente cliente) {
		this.tbModel = tableModel;
		inicializar();
		gambiarra = cliente;
		setarCampos(cliente);
	}

	private void setarCampos(Cliente cliente) {
		this.campoNome.setText(cliente.getNome());
		this.campoCPF.setText(cliente.getCpf());
		this.campoRG.setText(cliente.getRg());
		this.campoCNH.setText(cliente.getCnh());
		this.campoContato.setText(cliente.getContato());
	}

	public CadastroCliente(ClienteTabelModel tableModel) {

		this.tbModel = tableModel;
		inicializar();

	}

	private void inicializar() {
		this.setTitle("Cadastro de Cliente");
		this.setSize(370, 240);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		this.addComponentes(this.getContentPane());

	}

	private void addComponentes(Container pane) {

		pane.add(labelDadosPessoais);

		pane.add(labelNome);
		pane.add(labelCPF);
		pane.add(labelRG);
		pane.add(labelContato);
		pane.add(labelCNH);

		pane.add(campoNome);
		pane.add(campoCPF);
		pane.add(campoRG);
		pane.add(campoContato);
		pane.add(campoCNH);

		pane.add(botaoSalvar);
		pane.add(botaoCancelar);

		dimencionarComponentes();

	}

	private void dimencionarComponentes() {
		labelDadosPessoais.setBounds(10, 10, 400, 20);
		labelNome.setBounds(10, 30, 80, 20);
		campoNome.setBounds(10, 50, 345, 20);
		labelCPF.setBounds(10, 70, 80, 20);
		campoCPF.setBounds(10, 90, 165, 20);
		labelRG.setBounds(190, 70, 80, 20);
		campoRG.setBounds(190, 90, 165, 20);
		labelCNH.setBounds(10, 110, 80, 20);
		campoCNH.setBounds(10, 130, 165, 20);
		labelContato.setBounds(190, 110, 80, 20);
		campoContato.setBounds(190, 130, 165, 20);

		botaoSalvar.setBounds(115, 160, 120, 30);
		botaoCancelar.setBounds(240, 160, 120, 30);

		botaoSalvar.addActionListener(botaoSalvarListener);
		botaoCancelar.addActionListener(botaoCancelarListener);
	}

	private ActionListener botaoSalvarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			Cliente cliente = new Cliente(campoNome.getText(),
					campoCPF.getText(), campoRG.getText(),
					campoContato.getText(), campoCNH.getText());

			ClienteDao clienteDao = new ClienteDao();

			// TODO USANDO A GAMBIARRA
			if (gambiarra == null) {
				clienteDao.salvarCliente(cliente);
				JOptionPane.showMessageDialog(null,
						"CLIENTE CADASTRADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onAdd(cliente);
			} else if (gambiarra.getCpf().equals(cliente.getCpf())) {
				clienteDao.editarCliente(cliente);
				JOptionPane.showMessageDialog(null,
						"CLIENTE ATUALIZADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onRemove(gambiarra);
				tbModel.onAdd(cliente);
			} else {
				clienteDao.salvarCliente(cliente);
				JOptionPane.showMessageDialog(null,
						"CLIENTE CADASTRADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onRemove(gambiarra);
				tbModel.onAdd(cliente);
				clienteDao.deletarCliente(gambiarra);
			}
			dispose();
		}
	};

	private ActionListener botaoCancelarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};

	public static void main(String[] args) {

	}

}

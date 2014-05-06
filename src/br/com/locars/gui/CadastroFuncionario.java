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

import br.com.locars.dao.FuncionarioDao;
import br.com.locars.model.Funcionario;
import br.com.locars.model.Usuario;

public class CadastroFuncionario extends JDialog {

	private JLabel labelDadosPessoais = new JLabel(
			"------------------------ INFORMAÇÕES PESSOAIS ------------------------");
	private JLabel labelNome = new JLabel("*Nome:");
	private JLabel labelCPF = new JLabel("*CPF:");
	private JLabel labelRG = new JLabel("RG:");
	private JLabel labelContato = new JLabel("Contato:");
	private JLabel labelCarteiraTrabalho = new JLabel("*Carteira de Trabalho:");
	private JLabel labelLogin = new JLabel("*Login:");
	private JLabel labelSenha = new JLabel("*Senha:");

	private JTextField campoNome = new JTextField();
	private JTextField campoCPF = new JTextField();
	private JTextField campoRG = new JTextField();
	private JTextField campoContato = new JTextField();
	private JTextField campoCarteiraTrabalho = new JTextField();
	private JTextField campoLogin = new JTextField();
	private JTextField campoSenha = new JTextField();

	private JButton botaoSalvar = new JButton("Salvar", new ImageIcon(
			"D://icons/save2_16x16.png"));
	private JButton botaoCancelar = new JButton("Cancelar", new ImageIcon(
			"D://icons/cancel1_16x16.png"));

	private FuncionarioTabelModel tbModel;

	// GAMBIARRA
	private Funcionario gambiarra;

	public CadastroFuncionario(FuncionarioTabelModel tableModel,
			Funcionario funcionario) {
		this.tbModel = tableModel;
		inicializar();
		gambiarra = funcionario;
		setarCampos(funcionario);
	}

	private void setarCampos(Funcionario funcionario) {
		this.campoNome.setText(funcionario.getNome());
		this.campoCPF.setText(funcionario.getCpf());
		this.campoRG.setText(funcionario.getRg());
		this.campoCarteiraTrabalho.setText(funcionario.getCarteiraTrabalho());
		this.campoContato.setText(funcionario.getContato());
		this.campoLogin.setText(funcionario.getUsuario().getLogin());
		this.campoSenha.setText(funcionario.getUsuario().getSenha());
	}

	public CadastroFuncionario(FuncionarioTabelModel tableModel) {

		this.tbModel = tableModel;
		inicializar();

	}

	private void inicializar() {
		this.setTitle("Cadastro de Funcionario");
		this.setSize(400, 280);
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
		pane.add(labelCarteiraTrabalho);
		pane.add(labelLogin);
		pane.add(labelSenha);

		pane.add(campoNome);
		pane.add(campoCPF);
		pane.add(campoRG);
		pane.add(campoContato);
		pane.add(campoCarteiraTrabalho);
		pane.add(campoLogin);
		pane.add(campoSenha);

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
		labelCarteiraTrabalho.setBounds(10, 110, 130, 20);
		campoCarteiraTrabalho.setBounds(10, 130, 165, 20);
		labelContato.setBounds(190, 110, 80, 20);
		campoContato.setBounds(190, 130, 165, 20);
		labelLogin.setBounds(10, 150, 80, 20);
		campoLogin.setBounds(10, 170, 165, 20);
		labelSenha.setBounds(190, 150, 80, 20);
		campoSenha.setBounds(190, 170, 165, 20);

		botaoSalvar.setBounds(115, 200, 120, 30);
		botaoCancelar.setBounds(240, 200, 120, 30);

		botaoSalvar.addActionListener(botaoSalvarListener);
		botaoCancelar.addActionListener(botaoCancelarListener);
	}

	private ActionListener botaoSalvarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			Usuario novoUsuario = new Usuario();
			novoUsuario.setLogin(campoLogin.getText());
			novoUsuario.setLogin(campoSenha.getText());
			Funcionario funcionario = new Funcionario(campoNome.getText(),
					campoCPF.getText(), campoRG.getText(),
					campoContato.getText(), novoUsuario,
					campoCarteiraTrabalho.getText());

			FuncionarioDao funcionarioDao = new FuncionarioDao();

			// TODO USANDO A GAMBIARRA
			if (gambiarra == null) {
				funcionarioDao.salvarFuncionario(funcionario);
				JOptionPane.showMessageDialog(null,
						"CLIENTE CADASTRADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onAdd(funcionario);
			} else if (gambiarra.getCpf().equals(funcionario.getCpf())) {
				funcionarioDao.editarFuncionario(funcionario);
				JOptionPane.showMessageDialog(null,
						"CLIENTE ATUALIZADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onRemove(gambiarra);
				tbModel.onAdd(funcionario);
			} else {
				funcionarioDao.salvarFuncionario(funcionario);
				JOptionPane.showMessageDialog(null,
						"CLIENTE CADASTRADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onRemove(gambiarra);
				tbModel.onAdd(funcionario);
				funcionarioDao.deletarFuncionario(gambiarra);
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
		CadastroFuncionario c = new CadastroFuncionario(
				new FuncionarioTabelModel());
		c.setVisible(true);
	}

}

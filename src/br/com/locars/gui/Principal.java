package br.com.locars.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.com.locars.dao.ClienteDao;
import br.com.locars.dao.FuncionarioDao;
import br.com.locars.dao.VeiculoDao;
import br.com.locars.model.Cliente;
import br.com.locars.model.Funcionario;
import br.com.locars.model.Veiculo;

public class Principal extends JFrame {

	private FuncionarioDao fd;
	private ClienteDao cd;
	private VeiculoDao vd;

	private JMenuBar menuBar = new JMenuBar();

	private JMenu menuArquivo = new JMenu("Arquivo");

	private JMenuItem menuCadastrarFuncionario = new JMenuItem(
			"Cadastrar Funcionario", new ImageIcon(
					"D://icons/clientContact1_16x16.png"));
	private JMenuItem menuCadastrarCliente = new JMenuItem("Cadastrar Cliente",
			new ImageIcon("D://icons/clientContact2_16x16.png"));
	private JMenuItem menuCadastrarVeiculo = new JMenuItem("Cadastrar Veiculo",
			new ImageIcon("D://icons/car1_16x16.png"));
	private JMenuItem menuSair = new JMenuItem("Sair");

	public Principal() {
		setTitle("SISTEMA PARA LOCADORA DE CARROS");

		fd = new FuncionarioDao();
		cd = new ClienteDao();
		vd = new VeiculoDao();

		// adicona os JMenuItem no JMenu
		menuArquivo.add(menuCadastrarFuncionario);
		menuArquivo.addSeparator();
		menuArquivo.add(menuCadastrarCliente);
		menuArquivo.addSeparator();
		menuArquivo.add(menuCadastrarVeiculo);
		menuArquivo.addSeparator();
		menuArquivo.add(menuSair);

		// menuCadastrar.setIcon(new ImageIcon("C://Icons/cadastrar (2).png"));

		// adiciona o JMenu no JMenuBar
		menuBar.add(menuArquivo);

		menuCadastrarFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				List<Funcionario> funcionarios = null;
				try {
					funcionarios = fd.getLista();
				} catch (Exception e1) {
					System.out.println("PROBLEMA NA CONEXÃO COM O BANCO");
				}
				JanelaFuncionario janelafuncionario = new JanelaFuncionario(
						funcionarios);
				janelafuncionario.setVisible(true);
			}
		});

		menuCadastrarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				List<Cliente> clientes = null;
				try {
					clientes = cd.getLista();
				} catch (Exception e1) {
					System.out.println("PROBLEMA NA CONEXÃO COM O BANCO");
				}
				JanelaCliente janelaCliente = new JanelaCliente(clientes);
				janelaCliente.setVisible(true);
			}
		});

		menuCadastrarVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				List<Veiculo> veiculos = null;
				try {
					veiculos = vd.getLista();
				} catch (Exception e1) {
					System.out.println("PROBLEMA NA CONEXÃO COM O BANCO");
				}
				JanelaVeiculo janelaVeiculo = new JanelaVeiculo(veiculos);
				janelaVeiculo.setVisible(true);
			}
		});

		menuSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int resposta = JOptionPane.showConfirmDialog(null,
						"TEM CERTEZA QUE DESEJA SAIR DO SISTEMA?", "?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
						new ImageIcon("D://icons/question1_32x32.png"));
				if (resposta == 0) {
					dispose();
				}
			}
		});

		JDesktopPane desktop = new JDesktopPane() { // Serve para alterar a
													// imagem do plano de fundo
													// da janela principal

			Image im = (new ImageIcon("D://icons/carro.jpg")).getImage();

			public void paintComponent(Graphics g) {
				g.drawImage(im, 0, 0, this);

			}

		};// fim do JDesktopPane

		((JComponent) getContentPane()).setOpaque(true);
		getContentPane().add(desktop);// adiciono o JDesktopPane ao JPanel

		setJMenuBar(menuBar); // atribui um JMenuBar para o frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(765, 650);
		setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		Principal p = new Principal();
		p.setVisible(true);
	}

}

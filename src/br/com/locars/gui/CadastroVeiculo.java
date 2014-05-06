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

import br.com.locars.dao.VeiculoDao;
import br.com.locars.model.Veiculo;

public class CadastroVeiculo extends JDialog {

	private JLabel labelDadosVeiculo = new JLabel(
			"------------------------ INFORMAÇÕES DO VEICULO ------------------------");
	private JLabel labelModelo = new JLabel("Modelo:");
	private JLabel labelFabricante = new JLabel("Fabricante:");
	private JLabel labelCor = new JLabel("Cor:");
	private JLabel labelPlaca = new JLabel("Placa:");
	private JLabel labelChassi = new JLabel("Chassi:");
	private JLabel labelRenavan = new JLabel("Renavan:");

	private JTextField campoModelo = new JTextField();
	private JTextField campoFabricante = new JTextField();
	private JTextField campoCor = new JTextField();
	private JTextField campoPlaca = new JTextField();
	private JTextField campoChassi = new JTextField();
	private JTextField campoRenavan = new JTextField();

	private JButton botaoSalvar = new JButton("Salvar", new ImageIcon(
			"D://icons/save2_16x16.png"));
	private JButton botaoCancelar = new JButton("Cancelar", new ImageIcon(
			"D://icons/cancel1_16x16.png"));

	private VeiculoTableModel tbModel;

	// GAMBIARRA
	private Veiculo gambiarra;

	public CadastroVeiculo(VeiculoTableModel tableModel, Veiculo veiculo) {
		this.tbModel = tableModel;
		inicializar();
		gambiarra = veiculo;
		setarCampos(veiculo);
	}

	public CadastroVeiculo() {
		inicializar();
	}

	public CadastroVeiculo(VeiculoTableModel tableModel) {

		this.tbModel = tableModel;
		inicializar();

	}

	private void inicializar() {
		this.setTitle("Cadastro de Veiculo");
		this.setSize(370, 270);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		this.setLayout(null);
		this.addComponentes(this.getContentPane());

	}

	private void addComponentes(Container pane) {

		pane.add(labelDadosVeiculo);

		pane.add(labelModelo);
		pane.add(labelFabricante);
		pane.add(labelCor);
		pane.add(labelPlaca);
		pane.add(labelChassi);
		pane.add(labelRenavan);

		pane.add(campoModelo);
		pane.add(campoFabricante);
		pane.add(campoCor);
		pane.add(campoPlaca);
		pane.add(campoChassi);
		pane.add(campoRenavan);

		pane.add(botaoSalvar);
		pane.add(botaoCancelar);

		botaoSalvar.addActionListener(botaoSalvarListener);
		botaoCancelar.addActionListener(botaoCancelarListener);

		dimencionarComponentes();

	}

	private void setarCampos(Veiculo veiculo) {
		this.campoModelo.setText(veiculo.getModelo());
		this.campoFabricante.setText(veiculo.getFabricante());
		this.campoCor.setText(veiculo.getCor());
		this.campoPlaca.setText(veiculo.getPlaca());
		this.campoChassi.setText(veiculo.getChassi());
		this.campoRenavan.setText(veiculo.getRenavan());
	}

	private void dimencionarComponentes() {

		labelDadosVeiculo.setBounds(10, 10, 400, 20);
		labelModelo.setBounds(10, 30, 80, 20);
		campoModelo.setBounds(10, 50, 345, 20);
		labelFabricante.setBounds(10, 70, 80, 20);
		campoFabricante.setBounds(10, 90, 165, 20);
		labelCor.setBounds(190, 70, 80, 20);
		campoCor.setBounds(190, 90, 165, 20);
		labelPlaca.setBounds(10, 110, 80, 20);
		campoPlaca.setBounds(10, 130, 165, 20);
		labelChassi.setBounds(190, 110, 80, 20);
		campoChassi.setBounds(190, 130, 165, 20);
		labelRenavan.setBounds(10, 150, 80, 20);
		campoRenavan.setBounds(10, 170, 165, 20);

		botaoSalvar.setBounds(115, 200, 120, 30);
		botaoCancelar.setBounds(240, 200, 120, 30);
	}

	private ActionListener botaoSalvarListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			Veiculo veiculo = new Veiculo();
			veiculo.setModelo(campoModelo.getText());
			veiculo.setFabricante(campoFabricante.getText());
			veiculo.setCor(campoCor.getText());
			veiculo.setPlaca(campoPlaca.getText());
			veiculo.setChassi(campoChassi.getText());
			veiculo.setRenavan(campoRenavan.getText());

			VeiculoDao clienteDao = new VeiculoDao();

			// TODO USANDO A GAMBIARRA
			if (gambiarra == null) {
				clienteDao.salvarVeiculo(veiculo);
				JOptionPane.showMessageDialog(null,
						"VEICULO CADASTRADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onAdd(veiculo);
			} else if (gambiarra.getChassi().equals(veiculo.getChassi())) {
				clienteDao.editarVeiculo(veiculo);
				JOptionPane.showMessageDialog(null,
						"VEICULO ATUALIZADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onRemove(gambiarra);
				tbModel.onAdd(veiculo);
			} else {
				clienteDao.salvarVeiculo(veiculo);
				JOptionPane.showMessageDialog(null,
						"VEICULO CADASTRADO COM SUCESSO!", "OK",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								"D://icons/ok1_32x32.png"));
				tbModel.onRemove(gambiarra);
				tbModel.onAdd(veiculo);
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

		CadastroVeiculo cv = new CadastroVeiculo();
		cv.setVisible(true);

	}

}

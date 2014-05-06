package br.com.locars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locars.model.Veiculo;

public class VeiculoDao {

	Connection connection;

	public VeiculoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void salvarVeiculo(Veiculo veiculo) {
		String sql = "insert into veiculos"
				+ "(fabricante,modelo,cor,anoFab,placa,chassi,renavan)"
				+ " values(?,?,?,?,?,?,?)";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, veiculo.getFabricante());
			stmt.setString(2, veiculo.getModelo());
			stmt.setString(3, veiculo.getCor());
			stmt.setString(4, veiculo.getAnoFab());
			stmt.setString(5, veiculo.getPlaca());
			stmt.setString(6, veiculo.getChassi());
			stmt.setString(7, veiculo.getRenavan());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public boolean deletarCliente(Veiculo veiculo) {

		try {

			PreparedStatement stm = connection
					.prepareStatement("DELETE FROM veiculos WHERE chassi=?");
			stm.setString(1, veiculo.getChassi());
			stm.execute();
			stm.close();
			return true;
		} catch (Exception x) {
			return false;
		}
	}

	public boolean editarVeiculo(Veiculo veiculo) {

		String sql = "update veiculos set fabricante=?, modelo=?, cor=?, "
				+ "anoFab=?, placa=?, renavan=?, where chassi=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, veiculo.getFabricante());
			stmt.setString(2, veiculo.getModelo());
			stmt.setString(3, veiculo.getCor());
			stmt.setString(4, veiculo.getAnoFab());
			stmt.setString(5, veiculo.getPlaca());
			stmt.setString(6, veiculo.getRenavan());
			stmt.setString(6, veiculo.getChassi());

			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public List<Veiculo> getLista() throws Exception {

		List<Veiculo> lista = new ArrayList<Veiculo>();

		try {

			// obter conexão
			connection = ConnectionFactory.getConnection();
			// definir o comando SQL
			String sql = "SELECT * FROM veiculos";
			// obter statement a partir da conexão
			PreparedStatement st = connection.prepareStatement(sql);

			// executar comando
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Veiculo veiculo = new Veiculo();

				veiculo.setFabricante(rs.getString("fabricante"));
				veiculo.setModelo(rs.getString("modelo"));
				veiculo.setCor(rs.getString("cor"));
				veiculo.setAnoFab(rs.getString("anoFab"));
				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setChassi(rs.getString("chassi"));
				veiculo.setRenavan(rs.getString("renavan"));

				lista.add(veiculo);
			}

			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar contato");
		} finally {

			connection.close();

		}

	}

}

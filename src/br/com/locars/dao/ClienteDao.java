package br.com.locars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locars.model.Cliente;

public class ClienteDao {

	Connection connection;

	public ClienteDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void salvarCliente(Cliente cliente) {
		String sql = "insert into clientes" + "(nome,cpf,rg,contato,cnh)"
				+ " values(?,?,?,?,?)";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getRg());
			stmt.setString(4, cliente.getContato());
			stmt.setString(5, cliente.getCnh());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deletarCliente(Cliente cliente) {

		try {

			PreparedStatement stm = connection
					.prepareStatement("DELETE FROM clientes WHERE cpf=?");
			stm.setString(1, cliente.getCpf());
			stm.execute();
			stm.close();
			return true;
		} catch (Exception x) {
			x.printStackTrace();
			return false;
		}
	}

	public boolean editarCliente(Cliente cliente) {

		String sql = "update clientes set nome=?, rg=?, contato=?, cnh=? where cpf=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRg());
			stmt.setString(3, cliente.getContato());
			stmt.setString(4, cliente.getCnh());
			stmt.setString(5, cliente.getCpf());

			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public Cliente buscarCliente(String cpf) throws Exception {

		try {

			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM clientes WHERE cpf= " + cpf;
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			Cliente cliente = new Cliente();

			cliente.setNome(rs.getString("nome"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setRg(rs.getString("rg"));
			cliente.setContato(rs.getString("contato"));
			cliente.setCnh(rs.getString("cnh"));

			return cliente;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro");
		} finally {

			connection.close();

		}

	}

	public List<Cliente> getLista() throws Exception {

		List<Cliente> lista = new ArrayList<Cliente>();

		try {

			// obter conexão
			connection = ConnectionFactory.getConnection();
			// definir o comando SQL
			String sql = "SELECT * FROM clientes";
			// obter statement a partir da conexão
			PreparedStatement st = connection.prepareStatement(sql);
			// executar comando
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Cliente cliente = new Cliente();

				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setRg(rs.getString("rg"));
				cliente.setContato(rs.getString("contato"));
				cliente.setCnh(rs.getString("cnh"));

				lista.add(cliente);
			}

			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("erro no banco");
		} finally {

			connection.close();

		}

	}

}

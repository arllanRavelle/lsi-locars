package br.com.locars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locars.model.Funcionario;
import br.com.locars.model.Usuario;

public class FuncionarioDao {

	Connection connection;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void salvarFuncionario(Funcionario funcionario) {
		String sql = "insert into funcionarios"
				+ "(nome,cpf,rg,contato,carteiraTrabalho,usuario,senha)"
				+ " values(?,?,?,?,?,?,?)";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getRg());
			stmt.setString(4, funcionario.getContato());
			stmt.setString(5, funcionario.getCarteiraTrabalho());
			stmt.setString(6, funcionario.getUsuario().getLogin());
			stmt.setString(7, funcionario.getUsuario().getSenha());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deletarFuncionario(Funcionario funcionario) {

		try {

			PreparedStatement stm = connection
					.prepareStatement("DELETE FROM funcionarios WHERE cpf=?");
			stm.setString(1, funcionario.getCpf());
			stm.execute();
			stm.close();
			return true;
		} catch (Exception x) {
			x.printStackTrace();
			return false;
		}
	}

	public boolean editarFuncionario(Funcionario funcionario) {

		String sql = "update funcionarios set nome=?, rg=?, contato=?, carteiraTrabalho=?, usuario=?, senha=? where cpf=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getRg());
			stmt.setString(3, funcionario.getContato());
			stmt.setString(4, funcionario.getCarteiraTrabalho());
			stmt.setString(5, funcionario.getUsuario().getLogin());
			stmt.setString(6, funcionario.getUsuario().getSenha());
			stmt.setString(7, funcionario.getCpf());

			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public Funcionario buscarFuncionario(String cpf) throws Exception {

		try {

			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM funcionarios WHERE cpf= " + cpf;
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			Funcionario funcionario = new Funcionario();

			funcionario.setNome(rs.getString("nome"));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setRg(rs.getString("rg"));
			funcionario.setContato(rs.getString("contato"));
			funcionario.setCarteiraTrabalho(rs.getString("carteiraTrabalho"));
			Usuario novoUsuario = new Usuario();
			novoUsuario.setLogin(rs.getString("usuario"));
			novoUsuario.setSenha(rs.getString("senha"));
			funcionario.setUsuario(novoUsuario);

			return funcionario;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro");
		} finally {

			connection.close();

		}

	}

	public List<Funcionario> getLista() throws Exception {

		List<Funcionario> lista = new ArrayList<Funcionario>();

		try {

			// obter conexão
			connection = ConnectionFactory.getConnection();
			// definir o comando SQL
			String sql = "SELECT * FROM funcionarios";
			// obter statement a partir da conexão
			PreparedStatement st = connection.prepareStatement(sql);
			// executar comando
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Funcionario funcionario = new Funcionario();

				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setRg(rs.getString("rg"));
				funcionario.setContato(rs.getString("contato"));
				funcionario.setCarteiraTrabalho(rs
						.getString("carteiraTrabalho"));
				Usuario novoUsuario = new Usuario();
				novoUsuario.setLogin(rs.getString("usuario"));
				novoUsuario.setSenha(rs.getString("senha"));
				funcionario.setUsuario(novoUsuario);

				lista.add(funcionario);
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

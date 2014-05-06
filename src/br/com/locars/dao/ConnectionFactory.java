package br.com.locars.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("ERRO");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());

			return DriverManager.getConnection("jdbc:mysql://localhost/locars",
					"root", "root");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
package br.com.project_back_end.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		
		try {
			
			return DriverManager.getConnection("jdbc:mysql://localhost/project_back_end", "root", "12365902");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}

package br.com.choice.banco.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Classe responsável por efetuar conexão com Banco de Dados
 * @author Diego Munhoz
 * @since 24/02/2014
 */

public class ConnectionFactory {
	
	public static Connection getConnection(){
		final String driver = "com.mysql.jdbc.Driver";
		final String local = "jdbc:mysql://localhost/turmas";
		final String login = "root";
		final String senha = "root";

		Connection conexao = null;
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(local, login, senha);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado.");
		}catch (SQLException e) {
			System.out.println("Erro durante a conexão.");
		}
		return conexao;		
	}

}
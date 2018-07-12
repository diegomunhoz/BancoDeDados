package br.com.choice.banco.teste;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * Classe respons�vel por demonstrar conex�o com MYSql
 * @author Diego Munhoz
 * @since 24/02/2014
 */

public class TestaBanco {

	public static void main(String[] args) {
		
		String local = "jdbc:mysql://localhost/turmas";
		String login = "root";
		String senha = "root";
		
		Connection bd = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver carregado com sucesso");
			bd = (Connection) DriverManager.getConnection(local, login, senha);
			System.out.println("Conex�o estabelecida com sucesso");
			bd.close();
			System.out.println("Conex�o encerrada com sucesso");
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER DO BANCO N�O ENCONTRADO");
		} catch (SQLException e) {
			System.out.println("ERRO CONECTANDO AO BANCO");
		}
	}
	
}

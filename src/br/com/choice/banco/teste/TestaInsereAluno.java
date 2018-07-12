package br.com.choice.banco.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.choice.banco.dao.AlunoDAO;
import br.com.choice.banco.util.ConnectionFactory;

/**
 * Classe responsável por demonstrar conexão com MYSql e inserção do objeto
 * ALUNO
 * 
 * @author Diego Munhoz
 * @since 24/02/2014
 */

public class TestaInsereAluno {

	public static void main(String[] args) {

		Connection bd = ConnectionFactory.getConnection();

		Scanner teclado = new Scanner(System.in);
		System.out.println("Nome do aluno........: ");
		String nomeAluno = teclado.nextLine();
		System.out.println("Cidade do aluno......: ");
		String cidadeAluno = teclado.nextLine();
		System.out.println("Idade do aluno.......: ");
		int idadeAluno = teclado.nextInt();
		
		try {
			AlunoDAO dao = new AlunoDAO(bd);
			//dao.inserir(nomeAluno, idadeAluno, cidadeAluno);
			System.out.println("Aluno inserido com sucesso");
			bd.close();
		} catch (SQLException e) {
			System.out.println("Não foi possível inserir o ALUNO.");
		}

	}

}

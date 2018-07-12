package br.com.choice.banco.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.choice.banco.dao.AlunoDAO;
import br.com.choice.banco.model.Aluno;
import br.com.choice.banco.util.ConnectionFactory;

/**
 * Classe responsável por testar o método buscar_todos
 * @author Diego Munhoz
 * @since 25/02/2014
 */

public class TestaBuscaAluno {

	public static void main(String[] args) {
		
		Connection bd = ConnectionFactory.getConnection();
		AlunoDAO dao = new AlunoDAO(bd);
		
		try {
			List<Aluno> alunos = dao.buscarTodos();
			for (Aluno aluno : alunos) {
				System.out.println("NOME..: " + aluno.getNome());
				System.out.println("IDADE.: " + aluno.getIdade());
				System.out.println("CIDADE: " + aluno.getCidade());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível exibir ALUNOS.");
		}
	}

}

package br.com.choice.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.banco.model.Aluno;

/**
 * Classe responsável por armazenar os métodos de AlunoDAO
 * @author Diego Munhoz
 * @since 24/02/2014
 */

public class AlunoDAO {
	
	private Connection bd;
	
	public AlunoDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(Aluno aluno) throws SQLException{
		String sql = "delete from alunos_java  where nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, aluno.getIdade());
		comando.execute();		
	}
	
	public void alterar(Aluno aluno) throws SQLException{
		String sql = "update alunos_java set idade=?, cidade=? where nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, aluno.getIdade());
		comando.setString(2, aluno.getCidade());
		comando.setString(3, aluno.getNome());
		comando.execute();		
	}
	
	public void inserir(Aluno aluno) throws SQLException{
		String sql = "insert into alunos_java set (nome, idade, cidade) values (?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, aluno.getIdade());
		comando.setString(2, aluno.getCidade());
		comando.setString(3, aluno.getNome());
		comando.execute();
	}

	public List<Aluno> buscarTodos() throws SQLException{
		String sql = "select * from alunos_java order by nome";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		
		while (cursor.next()) {
			Aluno aluno = new Aluno();
			aluno.setNome(cursor.getString("nome"));
			aluno.setIdade(cursor.getInt("idade"));
			aluno.setCidade(cursor.getString("cidade"));
			listaAlunos.add(aluno);
		}
		return listaAlunos;
	}
	
}//fim da classe
package br.com.choice.banco.model;

/**
 * Classe responsável por armazenar métodos e atributos de ALUNO
 * @author Diego Munhoz
 * @since 25/02/2014
 */

public class Aluno {
	
	private String nome;
	private String cidade;
	private int idade;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

}// fim da classe
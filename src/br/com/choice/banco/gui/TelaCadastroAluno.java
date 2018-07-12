package br.com.choice.banco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.LimitExceededException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.choice.banco.dao.AlunoDAO;
import br.com.choice.banco.model.Aluno;
import br.com.choice.banco.util.ConnectionFactory;

/**
 * Classe responsável por armazenar cadastro de aluno
 * @author Diego Munhoz
 * @since 25/02/2014
 */

public class TelaCadastroAluno {

	private JFrame janelaCadastroAluno;
	private JPanel painelDaJanelaCadastroAluno;

	private JLabel jlbNomeAluno;
	private JLabel jlbIdadeAluno;
	private JLabel jlbCidadeAluno;
	
	private JTextField jtfNomeAluno;
	private JTextField jtfIdadeAluno;
	private JTextField jtfCidadeAluno;
	
	private JButton jbtNovoAluno;
	private JButton jbtSalvarAluno;
	private JButton jbtCancelarAluno;
	private JButton jbtSairAluno;
	
	private boolean validaAluno = false;
	
	private int idadeAlunoConvertida;

	public TelaCadastroAluno() {
		iniciaTela();
	}
	
	public void iniciaTela(){

		janelaCadastroAluno= new JFrame("CADASTRO DE ALUNO");
		painelDaJanelaCadastroAluno = (JPanel) janelaCadastroAluno.getContentPane();

		jlbNomeAluno = new JLabel("Nome:");
		jlbIdadeAluno = new JLabel("Idade:");
		jlbCidadeAluno = new JLabel("Cidade:");

		jtfNomeAluno = new JTextField();
		jtfIdadeAluno = new JTextField();
		jtfCidadeAluno = new JTextField();

		jbtNovoAluno = new JButton("SALVAR");
		jbtSalvarAluno = new JButton("SALVAR");
		jbtCancelarAluno = new JButton("CANCELAR");
		
		jlbNomeAluno.setBounds(20, 20, 40, 20);
		jlbIdadeAluno.setBounds(20, 50, 50, 20);
		jlbCidadeAluno.setBounds(20, 80, 60, 20);

		jtfNomeAluno.setBounds(90, 17, 360, 25);
		jtfIdadeAluno.setBounds(90, 47, 100, 25);
		jtfCidadeAluno.setBounds(90, 77, 360, 25);
		
		jbtSalvarAluno.setBounds(85, 140, 150, 30);
		jbtCancelarAluno.setBounds(245, 140, 150, 30);

		jbtSalvarAluno.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				salvarAluno();
			}
		});
		
		jbtCancelarAluno.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				limparAluno();
			}
		});

		painelDaJanelaCadastroAluno.add(jlbNomeAluno);
		painelDaJanelaCadastroAluno.add(jlbIdadeAluno);
		painelDaJanelaCadastroAluno.add(jlbCidadeAluno);
		painelDaJanelaCadastroAluno.add(jtfNomeAluno);
		painelDaJanelaCadastroAluno.add(jtfIdadeAluno);
		painelDaJanelaCadastroAluno.add(jtfCidadeAluno);
		painelDaJanelaCadastroAluno.add(jbtSalvarAluno);
		painelDaJanelaCadastroAluno.add(jbtCancelarAluno);

		painelDaJanelaCadastroAluno.setLayout(null);
		
		janelaCadastroAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaCadastroAluno.setSize(500, 270);
		janelaCadastroAluno.setLocationRelativeTo(null);
		janelaCadastroAluno.setVisible(true);
	}
	
	public void salvarAluno(){
		validarAluno();
		if (validaAluno == false) {
			Connection bd = ConnectionFactory.getConnection();
			Aluno vo = new Aluno();
			vo.setNome(jtfNomeAluno.getText());
			vo.setCidade(jtfCidadeAluno.getText());
			vo.setIdade(idadeAlunoConvertida);
			try {
				AlunoDAO dao = new AlunoDAO(bd);
				dao.inserir(vo);
				JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso");
				limparAluno();
				bd.close();
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o aluno.");
			}
		}		
	}
	
	public void validarAluno(){
		if (jtfNomeAluno.getText().equals(null) || jtfNomeAluno.getText().equals("") ) {
			validaAluno = true;
			JOptionPane.showMessageDialog(null, "Informe o nome, campo obrigatório!");
			jtfNomeAluno.grabFocus();
		}
		if (jtfIdadeAluno.getText().equals(null) || jtfIdadeAluno.getText().equals("")) {
			validaAluno = true;
			JOptionPane.showMessageDialog(null, "Informe a idade, campo obrigatório!");
			jtfIdadeAluno.grabFocus();
		}else {
			try {
				idadeAlunoConvertida = Integer.parseInt(jtfIdadeAluno.getText());
			} catch (Exception e) {
				validaAluno = true;
				JOptionPane.showMessageDialog(null, "Idade inválida, digite novamente");
				jtfIdadeAluno.grabFocus();
			}
		}
		if (jtfCidadeAluno.getText().equals(null) || jtfCidadeAluno.getText().equals("")) {
			validaAluno = true;
			JOptionPane.showMessageDialog(null, "Informe a cidade, campo obrigatório!");
			jtfCidadeAluno.grabFocus();
		}
	}

	public void limparAluno(){
		jtfNomeAluno.setText("");
		jtfIdadeAluno.setText("");
		jtfCidadeAluno.setText("");
		idadeAlunoConvertida = 0;
	}

}// fim da classe
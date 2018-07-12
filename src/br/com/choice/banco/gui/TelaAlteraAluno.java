package br.com.choice.banco.gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.choice.banco.dao.AlunoDAO;
import br.com.choice.banco.model.Aluno;
import br.com.choice.banco.util.ConnectionFactory;

/**
 * @author Diego Munhoz
 *
 */

public class TelaAlteraAluno {
	
	List<Aluno> alunos = new ArrayList<Aluno>();

	private JFrame janelaAlteraAluno;
	private JPanel painelDaJanelaAlteraAluno;

	private JLabel jlbNomeComboAluno;
	private JLabel jlbNomeAluno;
	private JLabel jlbIdadeAluno;
	private JLabel jlbCidadeAluno;
	
	private JTextField jtfNomeAluno;
	private JTextField jtfIdadeAluno;
	private JTextField jtfCidadeAluno;
	
	private JComboBox cbox;
	private String[] alunoComps = {};	
	
	private JButton jbtOkAluno;
	private JButton jbtSalvarAluno;
	private JButton jbtCancelarAluno;
	
	private boolean validaAluno = false;
	
	private int idadeAlunoConvertida;

	public TelaAlteraAluno() {
		iniciaTela();
	}
	public void iniciaTela(){

		janelaAlteraAluno= new JFrame("CADASTRO DE ALUNO");
		painelDaJanelaAlteraAluno = (JPanel) janelaAlteraAluno.getContentPane();

		jlbNomeComboAluno = new JLabel("Nome:");
		jlbNomeAluno = new JLabel("Nome:");
		jlbIdadeAluno = new JLabel("Idade:");
		jlbCidadeAluno = new JLabel("Cidade:");

		jtfNomeAluno = new JTextField();
		jtfIdadeAluno = new JTextField();
		jtfCidadeAluno = new JTextField();

		jbtOkAluno = new JButton(" O K ");
		jbtSalvarAluno = new JButton("SALVAR");
		jbtCancelarAluno = new JButton("CANCELAR");

		cbox = new JComboBox(alunoComps);

		cbox.setSelectedIndex(-1);
		cbox.setMaximumRowCount(5);

		cbox.setBounds(90, 10, 250, 30);
		jlbNomeComboAluno.setBounds(20, 20, 40, 20);
		jlbNomeAluno.setBounds(20, 50, 40, 20);
		jlbIdadeAluno.setBounds(20, 80, 50, 20);
		jlbCidadeAluno.setBounds(20, 110, 60, 20);

		jtfNomeAluno.setBounds(90, 47, 360, 25);
		jtfIdadeAluno.setBounds(90, 77, 100, 25);
		jtfCidadeAluno.setBounds(90, 107, 360, 25);
		
		jbtOkAluno.setBounds(350, 10, 100, 30);
		jbtSalvarAluno.setBounds(85, 170, 150, 30);
		jbtCancelarAluno.setBounds(245, 170, 150, 30);

		jbtOkAluno.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okAluno();
			}
		});

		jbtSalvarAluno.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				alterarAluno();
			}
		});
		
		jbtCancelarAluno.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				limparAluno();
			}
		});

		painelDaJanelaAlteraAluno.add(cbox);
		painelDaJanelaAlteraAluno.add(jlbNomeComboAluno);
		painelDaJanelaAlteraAluno.add(jlbNomeAluno);
		painelDaJanelaAlteraAluno.add(jlbIdadeAluno);
		painelDaJanelaAlteraAluno.add(jlbCidadeAluno);
		painelDaJanelaAlteraAluno.add(jtfNomeAluno);
		painelDaJanelaAlteraAluno.add(jtfIdadeAluno);
		painelDaJanelaAlteraAluno.add(jtfCidadeAluno);
		painelDaJanelaAlteraAluno.add(jbtOkAluno);
		painelDaJanelaAlteraAluno.add(jbtSalvarAluno);
		painelDaJanelaAlteraAluno.add(jbtCancelarAluno);

		painelDaJanelaAlteraAluno.setLayout(null);
		jtfNomeAluno.setEditable(false);
		
		listarAluno();
		
		janelaAlteraAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaAlteraAluno.setSize(500, 270);
		janelaAlteraAluno.setLocationRelativeTo(null);
		janelaAlteraAluno.setVisible(true);
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

	public void alterarAluno(){
		validarAluno();
		if (validaAluno == false) {
			Connection bd = ConnectionFactory.getConnection();
			Aluno vo = new Aluno();
			vo.setNome(jtfNomeAluno.getText());
			vo.setCidade(jtfCidadeAluno.getText());
			vo.setIdade(idadeAlunoConvertida);
			try {
				AlunoDAO dao = new AlunoDAO(bd);
				dao.alterar(vo);
				JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso");
				limparAluno();
				bd.close();
			} catch (SQLException e) {
				System.out.println("Erro ao inserir o aluno.");
			}
		}		
	}

	public void okAluno(){
		jtfNomeAluno.setText(alunos.get(cbox.getSelectedIndex()).getNome());
		jtfIdadeAluno.setText("" + alunos.get(cbox.getSelectedIndex()).getIdade());
		jtfCidadeAluno.setText(alunos.get(cbox.getSelectedIndex()).getCidade());
	}
	
	public void listarAluno() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			AlunoDAO dao = new AlunoDAO(bd);
			alunos = dao.buscarTodos();
			for (Aluno aluno : alunos) {
				cbox.addItem(aluno.getNome());
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir ALUNOS.");
		}
	}

	public void limparAluno(){
		jtfNomeAluno.setText("");
		jtfIdadeAluno.setText("");
		jtfCidadeAluno.setText("");
		idadeAlunoConvertida = 0;
	}


}// fim da classe
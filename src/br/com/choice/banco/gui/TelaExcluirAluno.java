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

public class TelaExcluirAluno {
	
	List<Aluno> alunos = new ArrayList<Aluno>();

	private JFrame janelaExcluirAluno;
	private JPanel painelDajanelaExcluirAluno;

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

	public TelaExcluirAluno() {
		iniciaTela();
	}
	public void iniciaTela(){

		janelaExcluirAluno= new JFrame("CADASTRO DE ALUNO");
		painelDajanelaExcluirAluno = (JPanel) janelaExcluirAluno.getContentPane();

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
				excluirAluno();
			}
		});
		
		jbtCancelarAluno.addActionListener(new java.awt.event.ActionListener(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				limparAluno();
			}
		});

		painelDajanelaExcluirAluno.add(cbox);
		painelDajanelaExcluirAluno.add(jlbNomeComboAluno);
		painelDajanelaExcluirAluno.add(jlbNomeAluno);
		painelDajanelaExcluirAluno.add(jlbIdadeAluno);
		painelDajanelaExcluirAluno.add(jlbCidadeAluno);
		painelDajanelaExcluirAluno.add(jtfNomeAluno);
		painelDajanelaExcluirAluno.add(jtfIdadeAluno);
		painelDajanelaExcluirAluno.add(jtfCidadeAluno);
		painelDajanelaExcluirAluno.add(jbtOkAluno);
		painelDajanelaExcluirAluno.add(jbtSalvarAluno);
		painelDajanelaExcluirAluno.add(jbtCancelarAluno);

		painelDajanelaExcluirAluno.setLayout(null);
		jtfNomeAluno.setEditable(false);
		jtfIdadeAluno.setEditable(false);
		jtfCidadeAluno.setEditable(false);
		
		listarAluno();
		
		janelaExcluirAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaExcluirAluno.setSize(500, 270);
		janelaExcluirAluno.setLocationRelativeTo(null);
		janelaExcluirAluno.setVisible(true);
	}

	public void excluirAluno(){
		if (cbox.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "É necessário selecionar um aluno!");
		}else {
			Connection bd = ConnectionFactory.getConnection();
			Aluno vo = new Aluno();
			vo.setNome(jtfNomeAluno.getText());
			vo.setCidade(jtfCidadeAluno.getText());
			vo.setIdade(idadeAlunoConvertida);
			try {
				AlunoDAO dao = new AlunoDAO(bd);
				dao.excluir(vo);
				JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso");
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
package br.com.choice.banco.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import br.com.choice.banco.dao.AlunoDAO;
import br.com.choice.banco.model.Aluno;
import br.com.choice.banco.util.ConnectionFactory;

/**
 * Classe responsável por exibir os dados de ALUNO
 * 
 * @author Diego Munhoz
 * @since 25/02/2014
 */

public class TelaExibirAluno {

	private JFrame janelaExibeAluno;
	private JPanel painelDaJanelaExibeAluno;
	private JTable tabelaAluno;
	private JButton btnSairAluno;

	private JScrollPane painelDeScrollAluno;

	private String[] colunas = new String[] { "Nome", "Idade", "Cidade" };
	private String[][] dados = new String[][] { {} };

	public TelaExibirAluno() {
		iniciaTela();
	}

	public void iniciaTela() {

		janelaExibeAluno = new JFrame("CONSULTA DE ALUNO");
		btnSairAluno = new JButton("SAIR");

		painelDaJanelaExibeAluno = (JPanel) janelaExibeAluno.getContentPane();
		painelDaJanelaExibeAluno.setLayout(null);

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaAluno = new JTable(modelo);

		tabelaAluno.setEnabled(false);

		painelDeScrollAluno = new JScrollPane(tabelaAluno);
		painelDeScrollAluno
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		painelDeScrollAluno.setBounds(0, 0, 560, 310);
		tabelaAluno.setBounds(0, 0, 500, 310);
		btnSairAluno.setBounds(190, 320, 150, 30);

		painelDaJanelaExibeAluno.add(painelDeScrollAluno);
		painelDaJanelaExibeAluno.add(btnSairAluno);

		btnSairAluno.addActionListener(new SairFrameListener());

		listarAluno();

		janelaExibeAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaExibeAluno.setSize(575, 400);
		janelaExibeAluno.setLocationRelativeTo(null);
		janelaExibeAluno.setVisible(true);

	}

	public class SairFrameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			janelaExibeAluno.setVisible(false);
		}
	}

	public void listarAluno() {
		DefaultTableModel m = (DefaultTableModel) tabelaAluno.getModel();
		m.removeRow(0);
		Connection bd = ConnectionFactory.getConnection();
		try {
			AlunoDAO dao = new AlunoDAO(bd);
			List<Aluno> alunos = dao.buscarTodos();
			for (Aluno aluno : alunos) {
				DefaultTableModel modelo = (DefaultTableModel) tabelaAluno
						.getModel();
				modelo.addRow(new String[] { aluno.getNome(),
						"" + aluno.getIdade(), aluno.getCidade() });
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir ALUNOS.");

		}
	}

}// fim da classe
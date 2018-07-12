package br.com.choice.banco.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/**
 * Classe responsável por criar menu para cadastro
 * 
 * @author Diego Munhoz
 * @since 24/02/2014
 */

public class MenuBanco {

	private JFrame janela;

	private JMenuBar barraMenu;

	private JMenu menuCadastro;
	private JMenu menuAlterar;
	private JMenu menuExcluir;
	private JMenu menuConsulta;
	private JMenu menuSair;

	private JMenuItem itemMenuCadastroAluno;
	private JMenuItem itemMenuAlterarAluno;
	private JMenuItem itemMenuExcluirAluno;
	private JMenuItem itemMenuConsultaAluno;
	private JMenuItem itemMenuSair;

	public void iniciaTela() {
		janela = new JFrame("MENU PRINCIPAL");

		barraMenu = new JMenuBar();

		menuCadastro = new JMenu("Cadastrar");
		menuAlterar = new JMenu("Alterar");
		menuExcluir = new JMenu("Excluir");
		menuConsulta = new JMenu("Consultar");
		menuSair = new JMenu("Sair");
		itemMenuCadastroAluno = new JMenuItem("Cadastrar Aluno");
		itemMenuExcluirAluno = new JMenuItem("Excluir Aluno");
		itemMenuAlterarAluno = new JMenuItem("Alterar Aluno");
		itemMenuConsultaAluno = new JMenuItem("Consultar Aluno");
		itemMenuSair = new JMenuItem("Sair");

		ImageIcon icone = new ImageIcon(
				"src/br/com/choice/banco/icones/cadastrar15x15.png");
		menuCadastro.setIcon(icone);

		icone = new ImageIcon("src/br/com/choice/banco/icones/salvar15x15.png");
		itemMenuCadastroAluno.setIcon(icone);

		itemMenuCadastroAluno
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cadastroAlunoActionListener(evt);
					}
				});

		itemMenuAlterarAluno
		.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				alteraAlunoActionListener(evt);
			}
		});

		itemMenuExcluirAluno
		.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				excluiAlunoActionListener(evt);
			}
		});

		itemMenuConsultaAluno
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						consultaAlunoActionListener(evt);
					}
				});

		itemMenuSair.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});

		menuCadastro.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuAlterar.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuExcluir.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuConsulta.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuSair.setFont(new Font("Arial Narrow", Font.BOLD, 15));

		barraMenu.add(menuCadastro);
		barraMenu.add(menuAlterar);
		barraMenu.add(menuExcluir);
		barraMenu.add(menuConsulta);
		barraMenu.add(menuSair);
		menuCadastro.add(itemMenuCadastroAluno);
		menuAlterar.add(itemMenuAlterarAluno);
		menuExcluir.add(itemMenuExcluirAluno);
		menuConsulta.add(itemMenuConsultaAluno);
		menuSair.add(itemMenuSair);

		janela.setJMenuBar(barraMenu);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
		janela.setVisible(true);

	}

	private static void cadastroAlunoActionListener(ActionEvent evt) {
		new TelaCadastroAluno();
	}

	private static void alteraAlunoActionListener(ActionEvent evt) {
		new TelaAlteraAluno();
	}

	private static void excluiAlunoActionListener(ActionEvent evt) {
		new TelaExcluirAluno();
	}

	private static void consultaAlunoActionListener(ActionEvent evt) {
		new TelaExibirAluno();
	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MenuBanco().iniciaTela();
	}

}// fim da classe
package com.jp.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jp.senac.model.Aluno;

public class AlunoJDBCdao {

	public Connection getConexao() throws ClassNotFoundException {

		// Driver

		String driver = "com.mysql.cj.jdbc.Driver";

		// Banco de dados

		String url = "jdbc:mysql://localhost:3306/cadastroalunos?useTimezone=true&serverTimezone=UTC";

		// usuario
		String user = "root";

		// senha

		String password = "root";

		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado ao banco de dados");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public List<Aluno> listaAlunos() throws ClassNotFoundException{
			List<Aluno> alunos = new ArrayList<>();
			String query = "SELECT * FROM alunos";
			
		
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				
				System.out.println("Teste");
				
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);				
				String matricula = rs.getString(6);
				alunos.add(new Aluno(nome,idade,semestre,genero,id,matricula));
				
				System.out.println(alunos);
			
				
			}
			
		
		}catch (SQLException e) {
			
		}
		return alunos;
	
		}

//	//abaixo exemplo de criação das classes
//	public Aluno pesquisarPorId(Intenger id) {
//	
//		String query = "";
//		
//	}
//
//	public Aluno excluirAluno(Aluno aluno) {
//		String delete = "Delete from aluno WHERE (id = ?)";
//	try {
//		Connection con = getConexao();
//		PreparedStatement pst = con.prepareStatement(delete);
//		pst.setInt(1, Id);
//		pst.executeUpdate();
//		pst.close();
//		con.close();
//	} catch (ClassNotFoundException e){
//		e.printStackTrace();
//	}
//		
//	
//	
//	}
//	
//	
//	public Aluno cadastrarAluno (Aluno aluno) {
//	
//		String query = "";
//		
//		return null;
//	
//	}
//	
//	public Aluno AlterarAluno (Aluno aluno) {
//		
//		String query = "update";
//		
//		return null;
//	}
}

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
	
		
	
	public Aluno PesquisarPorId (int id)  {
		
		String query = "select * from alunos where id=?";
		
		Aluno aluno = null;
		
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);				
				String matricula = rs.getString(6);
				aluno = new Aluno(nome, idade, semestre, genero, id, matricula);
			}
			rs.close();
			pst.close();
			con.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return aluno;
		
	}


	
	public void AlterarAluno (Aluno aluno)  {
		
		String query = "update alunos set nome = ?, idade = ?,semestre = ?, genero = ? where id=?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setInt(5, aluno.getId());
			pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void excluirAluno(int id) throws SQLException {
		String delete = "delete from alunos WHERE id =?";
	try {
		Connection con = getConexao();
		PreparedStatement pst = con.prepareStatement(delete);
		pst.setInt(1, id);
		pst.executeUpdate();
		pst.close();
		con.close();
	} catch (ClassNotFoundException e){
		e.printStackTrace();
	}
		
	}
}

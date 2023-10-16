package com.jp.senac.controller;

import java.io.IOException;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ConfirmarAlteracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id = Integer.parseInt(request.getParameter("id"));
		String matricula = request.getParameter("matricula");
		
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		
		AlunoJDBCdao dao = new AlunoJDBCdao();
		
		Aluno aluno = new Aluno(nome, idade, semestre, genero, id, matricula);
		
		dao.AlterarAluno(aluno);
		
		request.getRequestDispatcher("ListarServlet").forward(request, response);
		
	}

}

package com.jp.senac.controller;

import java.io.IOException;
import java.util.List;

import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AlterarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		
		//Recuperar a minha sessão
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		
		//Aluno aluno = dao.FindById
		//Recuperando o aluno que tem o nome informado 
		
		Aluno aluno = null;
		for(Aluno a : listaAlunos) {
			if(a.getNome().toString().equals(nome)) {
				aluno = a;
			}
		}
				
		request.setAttribute("aluno", aluno);
		request.getRequestDispatcher("alterarAluno.jsp").forward(request, response);
		
		
	
	}

	
	

}

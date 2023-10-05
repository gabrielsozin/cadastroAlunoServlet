package com.jp.senac.controller;

import java.io.IOException;
import java.util.List;

import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class DetalharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		
		//adicionar a lista
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		
		//recuperando o aluno da lista que tem o nome selecionado
		
		Aluno aluno = null;
		for(Aluno a : listaAlunos){
			if(a.getId()==id) {
				aluno = a ;
			}
		}
	
	request.setAttribute("aluno", aluno);
	request.getRequestDispatcher("listarAlunos.jsp").forward(request, response);
	
	
	}


	

}

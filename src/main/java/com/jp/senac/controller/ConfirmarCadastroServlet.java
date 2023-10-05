package com.jp.senac.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ConfirmarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recuperando a sessao
		HttpSession session = request.getSession();
		
		//recuperando os valores informados
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		
		//recuperando a lista da sessão, caso nao exista, criar
		
		@SuppressWarnings("unchecked")
		List<Aluno> listaAlunos = (List<Aluno>) session.getAttribute("listaAlunos");
		int id=0;
		
		int max=0;
		
		if(listaAlunos ==null) {
			listaAlunos = new ArrayList<>();//criando a lista
		}else {
			for(Aluno a: listaAlunos ) {
				if(a.getId() > max) {
					max = a.getId();
					id = max;
				}
			}
			
		}
		String matricula ="";
		LocalDate dataAtual = LocalDate.now();
		int mes = dataAtual.getMonthValue();
		//asume que o semetre 1 é de janeiro a junho e o semetre 2 é de julho a dezembro
		int semestreAtual = (mes < 7 ) ? 1 : 2;
	    String ano = String.valueOf(dataAtual.getYear()) ;
	    Random random = new Random();
		matricula = ano + String.valueOf(mes) + "0" + semestreAtual + idade;
		
		for (int i = 0; i< 4; i++) {
			matricula += random.nextInt(10);
		}
		
		
		//guardar no objeto aluno, das ctrl espaço que ele fala  a ordem correta
		Aluno aluno = new Aluno(nome, idade, semestre, genero,id+1 ,matricula );
		
		//adicionando aluno na lista(Insert)
		listaAlunos.add(aluno);
		
		session.setAttribute("listaAlunos", listaAlunos);
		request.setAttribute("aluno", aluno);
		
		//encaminhar a requisicao para o jsp
		request.getRequestDispatcher("detalharAluno.jsp").forward(request, response);
		
	}

}

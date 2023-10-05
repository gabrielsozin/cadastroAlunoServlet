<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Sistema de cadastro de Alunos</title>
</head>
<body >
<h1> Sistema de cadastro de Alunos</h1><br>

<h2>Informe o seu usúario e senha</h2>

<form action="LoginServlet" method="post">
<br>
Login: <input type="text" name="usuario" required>
<br><br>

Senha: <input type="password" name="senha" required>
<br><br>

<input type="submit" name="Login" >
<br><br>
</form>
	<%String erro = request.getParameter("error");
	if(erro != null ){
		if(erro.equals("1")){
			out.println("<p> Usuário ou senha invalida </p>");
		}
		if(erro.equals("2")){
			out.println("<p> Sessão Encerrada, faça login novamente</p>");
		}
		
	}
	
	%>

</body>
</html>
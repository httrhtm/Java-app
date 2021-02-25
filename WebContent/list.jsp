<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.*,java.util.*"%>
<%
List<QuestionsBean> qlist=(List<QuestionsBean>)request.getAttribute("qlist");
List<CorrectAnswersBean> calist=(List<CorrectAnswersBean>)request.getAttribute("calist");
String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="logout">logout</a>
			<br>
		</ul>
	</div>
	<div class="registerBtn">
		<a href="register.jsp">新規登録</a>
	</div>
	<%
	if(qlist != null){
		for(int i=0;i<qlist.size();i++){
    %>
	    <div class="inputQuestion">
			<label for="question">問題：</label>
			<a><%= qlist.get(i).getId() %></a>
			<a><%= qlist.get(i).getQuestion() %></a>
		</div>
		<% for(int j=0;j<calist.size();j++){ %>
		<!-- questions.id = question_id -->
		<%
		if(i == j){
		%>
		<div class="input-answer">
			<label for="answer">答え：</label>
			<a><%=calist.get(j).getAnswer() %></a>
		</div>
		<% } %>
	<%
		}
		}
	%>
	<%
	}
	%>
</body>
</html>
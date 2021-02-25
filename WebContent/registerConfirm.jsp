<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerComfirm</title>
</head>
<body>
	<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="/">logout</a>
		</ul>
	</div>
	<form action="RegisterServlet" method="post">
		<div class="main">
			<div class="inputQuestion">
				<label for="question">問題：</label>
				<textarea readonly name="question"><%=request.getAttribute("question") %></textarea>
			</div>
			<% String[] answer = (String[])request.getAttribute("answer");
			for(int i = 0; i < answer.length; i++) {
			%>
			<div class="input-answer">
			<label for="answer">答え：</label>
			<input type="text" name="answer" readonly value="<%=answer[i]%>">
			</div>
			<% } %>
			<div class="bottomNav">
				<ul>
					<li><a href="register.jsp">戻る</a>
					<li><input type="submit" value="登録">
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
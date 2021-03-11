<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerComfirm</title>
</head>
<body>
<%
if (session == null) {
	session = request.getSession(true);
	String message = "ログインしてください";
	request.setAttribute("message", message);
	response.sendRedirect("login.jsp");
}else {
	Object loginCheck = session.getAttribute("login_id");
	if (loginCheck == null){
		String message = "ログインしてください";
		request.setAttribute("message", message);
		response.sendRedirect("login.jsp");
	}
}
%>
	<!-- top, logoutボタン -->
	<div class="nav">
		<form action="top.jsp" method="post">
			<input type="submit" value="top">
		</form>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
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
				<label for="answer">答え：</label> <input type="text" name="answer"
					readonly value="<%=answer[i]%>">
			</div>
			<% } %>
		</div>
		<input type="submit" value="登録">
	</form>
	<form action="register.jsp" method="post">
		<input type="submit" value="戻る">
	</form>

</body>
</html>
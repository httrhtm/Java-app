<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editConfirm</title>
</head>
<body>
<!-- top, logoutボタン -->
	<div class="nav">
		<form action="top.jsp" method="post">
			<input type="submit" value="top">
		</form>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
	</div>
	<form action="UpdateServlet" method="post">
		<div class="main">
			<div class="questionNum">
				<p>問題番号:<input readonly type="text" id="questionId" name="questionId" value="<%=request.getAttribute("questionId") %>"></p>
			</div>
			<div class="question">
				<p>問題:<textarea readonly id="question" name="question"><%=request.getAttribute("question") %></textarea></p>
			</div>
			<%	String[] answer_ids = (String[])request.getAttribute("answer_id");
				String[] answer = (String[])request.getAttribute("answer");
				for(int i = 0; i < answer.length; i++) {
			%>
			<div class="answer">
				<p>
					答え:
					<input type="hidden" name="answer_id" value="<%= answer_ids[i] %>">
					<input readonly type="text" id="answer" name="answer" value="<%=answer[i]%>">
				</p>
			</div>
			<% } %>
			</div>
			<input type="submit" value="登録">
	</form>
	<form action="edit.jsp" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
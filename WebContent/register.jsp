<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
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
	<form action="RegisterConfirmServlet" method="post">
		<div class="main">
		<!-- "error_msg"がnullでない場合、エラーメッセージを表示する -->
		<%
		if(request.getAttribute("error_msg") != null) {
		%>
			<p><%= request.getAttribute("error_msg") %></p>
		<%
		}
		%>
			<div class="inputQuestion">
				<label for="question">問題：</label>
				<textarea name="question" id="question"></textarea>
			</div>
			<div class="input-answer">
				<input type="text" name="answer" id="answer"> 削除
			</div>
		</div>
		<!-- 戻る、確認、追加ボタン -->
		追加
		<input type="submit" value="確認">
	</form>
	<form action="ListServlet" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
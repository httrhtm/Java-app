<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userRegister</title>
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
	<!-- 新規登録フォーム -->
	<form action="UserRegisterConfirmServlet" method="post">

		<!-- "error_msg"がnullでない場合、エラーメッセージを表示する -->
		<%
		if (request.getAttribute("error_msg") != null) {
		%>
		<p><%=request.getAttribute("error_msg")%></p>
		<%
		}
		%>
		<p>
			ユーザー名：<input type="text" name="name" placeholder="半角英数字のみ">
		</p>
		<p>
			PW：<input type="password" name="password" placeholder="8文字以上">
		</p>
		<p>
			PW確認：<input type="password" name="password_confirm">
		</p>
		<p>
			管理者：<input type="checkbox" name="admin_check">
		</p>

		<!-- 確認 -->
		<input type="submit" value="確認">
	</form>
	<!-- 戻る -->
	<form action="UserListsServlet" method="post">
		<input type="submit" value="戻る">
	</form>


</body>
</html>
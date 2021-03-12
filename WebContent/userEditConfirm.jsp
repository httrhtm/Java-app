<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userEditConfirm</title>
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
	<!-- "message"がnullでない場合、メッセージを表示する -->
	<%
		if(request.getAttribute("message") != null) {
		%>
	<p><%= request.getAttribute("message") %></p>
	<%
		}
		%>
	<!-- 編集確認フォーム -->
	<form action="UserUpdateServlet" method="post">
		<p>
			ID：<input readonly type="text" name="id"
				value="<%=request.getAttribute("id")%>">
		</p>
		<p>
			ユーザー名：<input readonly type="text" name="name"
				value="<%=request.getAttribute("name")%>">
		</p>
		<p>
			PW：<input readonly type="password" name="password"
				value="<%=request.getAttribute("password")%>">
		</p>
		<p>
			PW確認：<input readonly type="password" name="password_confirm"
				value="<%=request.getAttribute("password_confirm")%>">
		</p>
		<p>
			管理者：<%=request.getAttribute("admin")%></p>
		<input type="hidden" name="admin_flag"
			value="<%=request.getAttribute("admin_flag")%>">

		<%
		if (request.getAttribute("message") == null) {
		%>
		<!-- 登録 -->
		<input type="submit" value="登録">
		<%
		}
		%>
	</form>
	<!-- 戻る -->
	<form action="UserEditServlet" method="post">
		<input type="submit" value="戻る"> <input type="hidden"
			name="user_id" value="<%=request.getAttribute("id")%>">
	</form>


</body>
</html>
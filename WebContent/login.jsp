<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
		<!-- "message"がnullでない場合、ログアウトメッセージを表示する -->
		<%
		if(request.getAttribute("message") != null) {
		%>
			<p><%= request.getAttribute("message") %></p>
		<%
		}
		%>
	<form action="Login" method="post">
		ID：<input type="text" name="id"><br>
		pw：<input type="password" name="password"><br>

		<!-- "error_msg"がnullでない場合、エラーメッセージを表示する -->
		<%
		if(request.getAttribute("error_msg") != null) {
		%>
			<p><%= request.getAttribute("error_msg") %></p>
		<%
		}
		%>
		<input type="submit" value="login"><br>
	</form>

</body>
</html>
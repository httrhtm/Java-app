<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testResult</title>
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
	<!-- タイトル -->
	<h1>テスト結果</h1>
	<!-- top, logoutボタン -->
	<div class="nav">
		<form action="top.jsp" method="post">
			<input type="submit" value="top">
		</form>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
	</div>
	<!-- 詳細 -->
	<div class="detail">
		<!-- ログインユーザー名 -->
		<p><%=request.getAttribute("user_name")%>さん
		</p>

		<!-- 問題数と正解数 -->
		<%
		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession p_session = request.getSession(false);
		%>

		<p><%=request.getAttribute("total")%>問中<%=request.getAttribute("point")%>問正解です。
		</p>
		<!-- 点数 -->
		<p><%=request.getAttribute("score")%>点でした。
		</p>
	</div>

	<!-- 現在時刻 -->
	<div class="current_time">
		<p><%=request.getAttribute("date")%></p>
	</div>

</body>
</html>
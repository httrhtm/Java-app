<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>testResult</title>
</head>
<body>
<!-- タイトル -->
<h1>テスト結果</h1>

<!-- top, logoutボタン -->
<div class ="nav">
	<ul>
		<li><a href="top.jsp">top</a>
		<li><a href="logout">logout</a>
		<br>
	</ul>
</div>

<!-- 詳細 -->
<div class="detail">
	<!-- ログインユーザー名 -->
	<p><%=request.getAttribute("user_name") %></p>

	<!-- 問題数と正解数 -->
	<%
	//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
	HttpSession p_session = request.getSession(false);
	%>

	<p><%=request.getAttribute("total") %>問中<%=request.getAttribute("point") %>問正解です。</p>
	<!-- 点数 -->
	<p></p>
</div>

<!-- 現在時刻 -->
<div class="current_time">
	<p><%=request.getAttribute("date") %></p>
</div>

</body>
</html>
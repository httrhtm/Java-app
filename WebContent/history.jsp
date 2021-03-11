<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>History</title>
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
	<h1>履歴</h1>
	<!-- top, logoutボタン -->
	<div class="nav">
		<form action="top.jsp" method="post">
			<input type="submit" value="top">
		</form>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
	</div>
	<div class="table">
		<table border="1">
			<tr>
				<th>名前</th>
				<th>得点</th>
				<th>採点時間</th>
			</tr>
			<%
			//hlistをサーブレットから取得
			List<HistoriesBean> hlist = (List<HistoriesBean>) request.getAttribute("hlist");
			//hlistの長さ分、以下の処理を繰り返す
			for (int k = 0; k < hlist.size(); k++) {

				//現在ログイン中のidをsessionから取得
				int user_id = (Integer) session.getAttribute("login_id");
				//ログイン中のuser_idとhistories.user_idが一致した場合
				if (user_id == hlist.get(k).getUserId()) {
					//名前を取得
					String name = (String) session.getAttribute("login_name");
			%>
			<tr>
				<td><%=name%></td>
				<td><%=hlist.get(k).getPoint()%></td>
				<td><%=hlist.get(k).getCreatedAt()%></td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*"%>
<%@ page import="bean.UsersBean"%>
<%@ page import="dao.UsersDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>
</head>
<body>
	<div class="nav">
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
	</div>
	<div class="main">
		<%
		//sessionからlogin_idを取得
		int login_id = (int) session.getAttribute("login_id");

		UsersBean bean = new UsersBean();
		UsersDao dao = new UsersDao();

		bean = dao.find(login_id);

		if (bean.getAdminFlag() == 1) {
		%>
		<!-- post 管理者権限ユーザーのみに表示 -->
		<form action="ListServlet" method="post">
			<input type="submit" value="問題と答えを確認・登録する ＞">
		</form>
		<%
		}
		%>
		<!-- post -->
		<form action="TestServlet" method="post">
			<input type="submit" value="テストをする ＞">
		</form>
		<!-- post -->
		<form action="HistoryServlet" method="post">
			<input type="submit" value="過去の採点結果をみる ＞">
		</form>
		<%
		if (bean.getAdminFlag() == 1) {
		%>
		<!-- post 管理者権限ユーザーのみに表示 -->
		<form action="UserListsServlet" method="post">
			<input type="submit" value="ユーザーを追加・編集する ＞">
		</form>
		<%
		}
		%>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top</title>
</head>
<body>
<div class ="nav">
	<form action="LogoutServlet" method="post">
		<input type="submit" value="logout" >
	</form>
</div>
<div class="main">
	<!-- post -->
	<form action="ListServlet" method="post">
		<input type="submit" value="問題と答えを確認・登録する ＞" >
	</form>
	<!-- post -->
	<form action="TestServlet" method="post">
		<input type="submit" value="テストをする ＞" >
	</form>
	<!-- post -->
	<form action="HistoryServlet" method="post">
		<input type="submit" value="過去の採点結果をみる ＞" >
	</form>
</div>
</body>
</html>
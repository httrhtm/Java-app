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
	<a href="/">logout</a>
</div>
<div class="main">

	<!-- get -->
	<ul>
		<li><a href="ListServlet">問題と答えを確認・登録する　＞</a>
	</ul>

	<!-- post -->
	<form action="TestServlet" method="post">
		<input type="submit" value="テストをする ＞" >
	</form>

	<ul>
		<li><a href="history.jsp">過去の採点結果をみる　＞</a>
	</ul>
</div>
</body>
</html>
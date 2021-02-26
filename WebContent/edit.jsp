<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
</head>
<body>
<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="/">logout</a>
		</ul>
	</div>
	<form action="EditConfirmServlet" method="post">
		<div class="main">
			<div class="questionNum">
				<p>問題番号:<input type="text" id="questionNum" name="questionNum" value=""></p>
			</div>
			<div class="question">
				<p>問題:<textarea id="question" name="question"></textarea></p>
			</div>
			<div class="answer">
				<p>
					答え:
					<input type="text" id="answer" name="answer" value="">
					<a href="/">削除</a>
				</p>
			</div>
			<div class="bottomNav">
				<ul>
					<li><a href="ListServlet">戻る</a>
					<li><input type="submit" value="確認">
					<li><a href="/">追加</a>
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
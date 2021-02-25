<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="/">logout</a>
		</ul>
	</div>
	<form action="RegisterConfirmServlet" method="post">
		<div class="main">
			<div class="inputQuestion">
				<label for="question">問題：</label>
				<textarea name="question" id="question"></textarea>
			</div>
			<div class="input-answer">
				<input type="text" name="answer" id="answer">
				<a href="/">削除</a>
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
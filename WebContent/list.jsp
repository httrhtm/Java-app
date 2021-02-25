<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="logout">logout</a>

		</ul>
	</div>
	<div class="registerBtn">
		<a href="register.jsp">新規登録</a>
	</div>
	<form action="List" method="post">
	<div class="main">
		<div class="questionList">
			<div class="question">
				<p>1</p>
				<label for="question">問題：</label>
				<input type="text" name="question" id="question">
				<ul>
					<li><a href="edit.jsp">編集</a>
					<li><a href="deleteConfirm.jsp">削除</a>
				</ul>
			</div>
			<div class="answer">
				<label for="answer">答え１：</label>
				<input type="text" name="answer" id="answer">
			</div>
			<div class="answer">
				<label for="answer">答え２：</label>
				<input type="text" name="answer" id="answer">
			</div>
		</div>
	</div>
</form>
</body>
</html>
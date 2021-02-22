<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
	<form action="List.action" method="post">
	<div class ="nav">
		<ul>
			<li><a href="/">top</a>
			<li><a href="/">logout</a>

		</ul>
	</div>
	<div class="registerBtn">
		<a href="/">新規登録</a>
	</div>
	<div class="main">
		<div class="questionList">
			<div class="question">
				<p>1</p>>
				<label for="question">問題：</label>
				<input type="text" name="question" id="question">
				<ul>
					<li><a href="/">編集</a>
					<li><a href="/">削除</a>
				</ul>
			</div>
			<div class="answer">
				<label for="question">答え１：</label>
				<input type="text" name="question" id="question">
			</div>
			<div class="answer">
				<label for="question">答え２：</label>
				<input type="text" name="question" id="question">
			</div>
		</div>
		<div class="questionList">
			<div class="question">
				<p>1</p>>
				<label for="question">問題：</label>
				<input type="text" name="question" id="question">
					<ul>
						<li><a href="/">編集</a>
						<li><a href="/">削除</a>
					</ul>
			</div>
			<div class="answer">
				<label for="question">答え１：</label>
				<input type="text" name="question" id="question">
			</div>
			<div class="answer">
				<label for="question">答え２：</label>
				<input type="text" name="question" id="question">
			</div>
		</div>
		<div class="questionList">
			<div class="question">
				<p>1</p>>
				<label for="question">問題：</label>
				<input type="text" name="question" id="question">
					<ul>
						<li><a href="/">編集</a>
						<li><a href="/">削除</a>
					</ul>
			</div>
			<div class="answer">
				<label for="question">答え１：</label>
				<input type="text" name="question" id="question">
			</div>
			<div class="answer">
				<label for="question">答え２：</label>
				<input type="text" name="question" id="question">
			</div>
		</div>
	</div>
</form>
</body>
</html>
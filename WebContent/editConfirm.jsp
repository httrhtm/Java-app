<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>editConfirm</title>
</head>
<body>
<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="/">logout</a>
		</ul>
	</div>
	<form action="UpdateServlet" method="post">
		<div class="main">
			<div class="questionNum">
				<p>問題番号:<input readonly type="text" id="questionNum" name="questionNum" value=""></p>
			</div>
			<div class="question">
				<p>問題:<textarea readonly id="question" name="question"></textarea></p>
			</div>
			<div class="answer">
				<p>
					答え:
					<input readonly type="text" id="answer" name="answer" value="">
				</p>
			</div>
			<div class="bottomNav">
				<ul>
					<li><a href="editConfirmServlet">戻る</a>
					<li><input type="submit" value="更新">
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
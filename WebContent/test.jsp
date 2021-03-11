<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.QuestionsBean"%>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
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
	<!-- top, logoutボタン -->
	<div class="nav">
		<form action="top.jsp" method="post">
			<input type="submit" value="top">
		</form>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
	</div>
	<!-- ランダムで表示 -->
	<!-- 問題 -->
	<form action="TestResultServlet" method="post">
		<%
		//リストデータをリクエストから取得
		List<QuestionsBean> qlist = (List<QuestionsBean>) request.getAttribute("qlist");

		//questionのデータが空でない場合
		if (qlist != null) {

			//qestionのデータの数分、繰り返し処理
			for (int i = 0; i < qlist.size(); i++) {
		%>
		<div class="inputQuestion">
			<p>
				<!-- 問題番号 -->
				<input readonly type="text" name="questionId"
					value="<%=qlist.get(i).getId()%>">
			</p>
			<p>
				<!-- 問題 -->
				<textarea readonly name="question"><%=qlist.get(i).getQuestion()%></textarea>
			</p>
		</div>

		<!-- 回答 -->
		<div class="input-answer">
			<p>
				回答：
				<!-- 答え -->
				<input type="text" name="answer" id="answer">
			</p>
		</div>
		<%
		}
		}
		%>
		<div class="bottomNav">
			<input type="submit" value="採点">
		</div>
	</form>

</body>
</html>
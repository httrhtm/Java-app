<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="bean.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
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
	<!-- "message"がnullでない場合、メッセージを表示する -->
		<%
		if(request.getAttribute("message") != null) {
		%>
			<p><%= request.getAttribute("message") %></p>
		<%
		}
		%>
	<!-- 新規登録ボタン -->
	<div class="registerBtn">
		<form action="register.jsp" method="post">
			<input type="submit" value="新規登録">
		</form>
	</div>

	<%
	//リストデータを取得 qlistへ代入
	List<QuestionsBean> qlist = (List<QuestionsBean>) request.getAttribute("qlist");

	//questionのデータが空でない場合
	if (qlist != null) {

		//qestionのデータ数分、繰り返し処理
		for (int i = 0; i < qlist.size(); i++) {
	%>

	<!-- 問題 -->
	<div class="inputQuestion">
		問題：
		<!-- 問題番号 -->
		<a><%=qlist.get(i).getId()%></a>

		<!-- 問題 -->
		<a><%=qlist.get(i).getQuestion()%></a>
		<!-- 編集ボタン -->
		<form action="EditServlet" method="post">
			<input type="hidden" name="questionId"
				value="<%=qlist.get(i).getId()%>"> <input type="submit"
				value="編集">
		</form>

		<!-- 削除ボタン -->
		<form action="DeleteConfirmServlet" method="post">
			<input type="hidden" name="questionId"
				value="<%=qlist.get(i).getId()%>"> <input type="submit"
				value="削除">
		</form>
	</div>

	<%
	//リストデータをリクエストから取得
	List<CorrectAnswersBean> calist = (List<CorrectAnswersBean>) request.getAttribute("calist");

	//qestionのデータの数分、繰り返し処理
	for (int j = 0; j < calist.size(); j++) {

		//qlistのidとcalistのquestion_idが同じ場合
		if (qlist.get(i).getId() == calist.get(j).getQuestionId()) {
	%>

	<!-- 回答 -->
	<div class="input-answer">
		<p>
			答え：
			<!-- 答え -->
			<a><%=calist.get(j).getAnswer()%></a>
		</p>
	</div>

	<%
		}
	}
		}
	}
	%>
</body>
</html>
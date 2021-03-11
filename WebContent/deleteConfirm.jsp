<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*"%>
<%@ page import="bean.QuestionsBean"%>
<%@ page import="bean.CorrectAnswersBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteConfirm</title>
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
	<form action="DeleteServlet" method="post">
		<div class="main">
			<%
			QuestionsBean qbean = (QuestionsBean) request.getAttribute("qbean");
			%>
			<div class="question">
				<p>
					問題: <input type="hidden" id="questionId" name="questionId"
						value="<%=qbean.getId()%>">
					<textarea readonly id="question" name="question"><%=qbean.getQuestion()%></textarea>
				</p>
			</div>
			<%
			List<CorrectAnswersBean> calist = (List<CorrectAnswersBean>) request.getAttribute("calist");
			for (int j = 0; j < calist.size(); j++) {
				//qlistのidとcalistのidが同じ場合
				if (qbean.getId() == calist.get(j).getQuestionId()) {
			%>
			<div class="answer">
				<p>
					答え: <input type="hidden" id="answerId" name="answerId"
						value="<%=calist.get(j).getId()%>"> <input type="hidden"
						id="questionsId" name="questionsId"
						value="<%=calist.get(j).getQuestionId()%>"> <input
						readonly type="text" id="answer" name="answer"
						value="<%=calist.get(j).getAnswer()%>">
				</p>
			</div>
			<%
			}
			}
			%>
		</div>
		<input type="submit" value="削除">
	</form>
	<form action="ListServlet" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
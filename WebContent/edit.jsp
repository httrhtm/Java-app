<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*"%>
<%@ page import="bean.QuestionsBean"%>
<%@ page import="bean.CorrectAnswersBean"%>

<%
String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
</head>
<body>
	<!-- top, logoutボタン -->
	<div class="nav">
		<form action="top.jsp" method="post">
			<input type="submit" value="top">
		</form>
		<form action="LogoutServlet" method="post">
			<input type="submit" value="logout">
		</form>
	</div>
	<form action="EditConfirmServlet" method="post">
		<div class="main">
			<%
			//questionは1つだけ取るからforでたくさん取る必要なし
			//リストデータをリクエストから取得

			QuestionsBean qbean = (QuestionsBean) request.getAttribute("qbean");
			%>
			<div class="questionNum">
				<p>
					問題番号: <input type="text" id="questionId" name="questionId"
						value="<%=qbean.getId()%>">
				</p>
			</div>
			<div class="question">
				<p>
					問題:
					<textarea id="question" name="question"><%=qbean.getQuestion()%></textarea>
				</p>
			</div>
			<%
			//リストデータをリクエストから取得

			List<CorrectAnswersBean> calist = (List<CorrectAnswersBean>) request.getAttribute("calist");
			for (int j = 0; j < calist.size(); j++) {

				//qlistのidとcalistのidが同じ場合
				if (qbean.getId() == calist.get(j).getQuestionId()) {
			%>

			<!-- 回答 -->
			<div class="input-answer">
				<p>
					答え：
					<!-- 答え -->
					<input type="hidden" name="answerId"
						value="<%=calist.get(j).getId()%>"></input> <input type="text"
						id="answer" name="answer" value="<%=calist.get(j).getAnswer()%>"></input>
				</p>
			</div>
			<%
			}
			}
			%>


			<!-- 戻る、確認、追加ボタン -->
			追加
			<input type="submit" value="確認">
	</form>
	<form action="ListServlet" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
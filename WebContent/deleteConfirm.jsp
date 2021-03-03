<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*" %>
<%@ page import="bean.QuestionsBean" %>
<%@ page import="bean.CorrectAnswersBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteConfirm</title>
</head>
<body>
<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="/">logout</a>
		</ul>
	</div>
	<form action="DeleteServlet" method="post">
		<div class="main">
<%
//questionは1つだけ取るからforでたくさん取る必要なし
//リストデータをリクエストから取得

QuestionsBean qbean= (QuestionsBean)request.getAttribute("qbean");
%>
			<div class="question">
				<p>問題:
				<input type="hidden" id="questionId" name="questionId" value="<%= qbean.getId() %>">
				<textarea readonly id="question" name="question"><%= qbean.getQuestion() %></textarea>
				</p>
			</div>
<%
//リストデータをリクエストから取得

List<CorrectAnswersBean> calist=(List<CorrectAnswersBean>)request.getAttribute("calist");
for(int j=0;j<calist.size();j++){

	//qlistのidとcalistのidが同じ場合
	if(qbean.getId() == calist.get(j).getQuestionId()){
%>
			<div class="answer">
				<p>
					答え:
					<input type="hidden" id="answerId" name="answerId" value="<%= calist.get(j).getId() %>">
					<input type="hidden" id="questionsId" name="questionsId" value="<%= calist.get(j).getQuestionId() %>">
					<input readonly type="text" id="answer" name="answer" value="<%= calist.get(j).getAnswer() %>">
				</p>
			</div>
<%
	}
}
%>
			<div class="bottomNav">
				<ul>
					<li>戻る
					<li><input type="submit" value="削除">
				</ul>
			</div>
		</div>
	</form>
</body>
</html>
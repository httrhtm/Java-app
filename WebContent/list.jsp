<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
<!-- top, logoutボタン -->
<div class ="nav">
	<ul>
		<li><a href="top.jsp">top</a>
		<li><a href="logout">logout</a>
		<br>
	</ul>
</div>

<!-- 新規登録ボタン -->
<div class="registerBtn">
	<a href="register.jsp">新規登録</a>
</div>

<%
//リストデータをリクエストから取得
List<QuestionsBean> qlist=(List<QuestionsBean>)request.getAttribute("qlist");

//questionのデータが空でない場合
if(qlist != null){

	//qestionのデータの数分、繰り返し処理
	for(int i=0;i<qlist.size();i++){

		//idをsetAttributeでリクエストパラメータに登録?
%>

<!-- 問題 -->
<div class="inputQuestion">
	<p> 問題：
		<!-- 問題番号 -->
		<a><%= qlist.get(i).getId() %></a>

		<!-- 問題 -->
		<a><%= qlist.get(i).getQuestion() %></a>
	</p>

		<!-- 編集ボタン -->
		<form action="EditServlet" method="post">
			<input type="hidden" name="questionId" value="<%= qlist.get(i).getId() %>">
			<input type="submit" value="編集">
		</form>

		<!-- 削除ボタン -->
		<a href="DeleteServlet">削除</a>
</div>

<!-- 二重ループ -->
<%
//リストデータをリクエストから取得
List<CorrectAnswersBean> calist=(List<CorrectAnswersBean>)request.getAttribute("calist");

//qestionのデータの数分、で繰り返し処理
for(int j=0;j<calist.size();j++){

	//qlistのidとcalistのidが同じ場合
	if(qlist.get(i).getId() == calist.get(j).getQuestionId()){
%>

<!-- 回答 -->
<div class="input-answer">
	<p> 答え：
		<!-- 答え -->
		<a><%=calist.get(j).getAnswer() %></a>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
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
<!-- ランダムで表示 -->
<%
//リストデータをリクエストから取得
List<QuestionsBean> qlist=(List<QuestionsBean>)request.getAttribute("qlist");

//questionのデータが空でない場合
if(qlist != null){

	//qestionのデータの数分、繰り返し処理
	for(int i=0;i<qlist.size();i++){
%>
<form action="TestResultServlet">
<!-- 問題 -->
<div class="inputQuestion">
	<p> 問題：
		<!-- 問題番号 -->
		<a><%= qlist.get(i).getId() %></a>

		<!-- 問題 -->
		<label for="question">問題：</label>
		<textarea readonly name="question"><%= qlist.get(i).getQuestion() %></textarea>
	</p>
</div>
<%
	}
}
%>

<!-- 回答 -->

<div class="input-answer">
<p> 答え：
	<!-- 答え -->
		<input type="text" name="answer" id="answer">
</p>
</div>
<div class="bottomNav">
	<input type="submit" value="採点">
</div>
</form>

</body>
</html>
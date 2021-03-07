<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.HistoriesBean" %>
<%@ page import="java.util.ArrayList" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>履歴</h1>
<div class ="nav">
		<ul>
			<li><a href="top.jsp">top</a>
			<li><a href="/">logout</a>
		</ul>
</div>
<div class="table">
	<table border="1">
    <tr>
      <th>名前</th>
      <th>得点</th>
      <th>採点時間</th>
    </tr>
<!-- 繰り返し -->
<%
//リストデータをリクエストから取得
List<HistoriesBean> hlist = (List<HistoriesBean>)request.getAttribute("hlist");

	//questionのデータが空でない場合
	if(hlist != null){

		//qestionのデータの数分、繰り返し処理
		for(int i=0;i<hlist.size();i++){
%>
    <tr>
      <td><%= hlist.get(i).getUserId() %></td>
      <td><%= hlist.get(i).getPoint() %></td>
      <td><%= hlist.get(i).getCreatedAt() %></td>
   </tr>
<%
	}
}
%>
  </table>
</div>
</body>
</html>
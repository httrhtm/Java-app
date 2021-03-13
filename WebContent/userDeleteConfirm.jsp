<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*"%>
<%@ page import="bean.UsersBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userDeleteConfirm</title>
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
	<!-- ユーザー削除確認フォーム -->
	<%
	UsersBean bean = (UsersBean)request.getAttribute("bean");
	%>
	<form action="UserDeleteServlet" method="post">
		<p>
			ID：<input readonly type="text" name="id" value="<%=bean.getId()%>">
		</p>
		<p>
			ユーザー名：<input readonly type="text" name="name" value="<%=bean.getName()%>">
		</p>
		<p>
			PW：<input readonly type="password" name="password"
				value="<%=bean.getPassword()%>">
		</p>
		<p>
			PW確認：<input readonly type="password" name="password_confirm"
				value="<%=bean.getPassword()%>">
		</p>
		<%
		//admin_checkがnullでない場合、adminに"あり"をセットする
		String admin = "";
		String admin_flag = "";
		if(bean.getAdminFlag() == 1){
			admin = "あり";
			admin_flag = "1";
		}else{
			admin = "なし";
			admin_flag = "0";
		}
		%>
		<p>
			管理者：<%= admin %>
		<input type="hidden" name="admin_flag" value="<%=request.getAttribute("admin_flag")%>">
		</p>

		<!-- 削除 -->
		<input type="submit" value="削除">
	</form>
	<!-- 戻る -->
	<form action="UserListsServlet" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
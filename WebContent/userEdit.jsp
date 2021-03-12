<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="bean.*,java.util.*"%> --%>
<%@ page import="bean.UsersBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userEdit</title>
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
	
	<!-- 編集フォーム (ID,ユーザ名は編集不可) -->
	<form action="UserEditConfirmServlet" method="post">
		<%
		UsersBean bean = (UsersBean) request.getAttribute("bean");
		%>
		<p>
			ID：<input readonly type="text" name="id" value="<%=bean.getId()%>">
		</p>
		<p>
			ユーザー名：<input readonly type="text" name="name"
				value="<%=bean.getName()%>">
		</p>
		<p>
			PW：<input type="password" name="password"
				value="<%=bean.getPassword()%>" placeholder="半角英数字8文字以上">
		</p>
		<p>
			PW確認：<input type="password" name="password_confirm"
				value="<%=bean.getPassword()%>" placeholder="半角英数字8文字以上">
		</p>

		<!-- admin_flagが1だったらchecked属性で初期値をチェック済にする -->
		<%
		if(bean.getAdminFlag() == 1){
		%>
		<p>
			管理者：<input type="checkbox" checked="checked" name="admin_check">
		</p>
		<%
		}else{
		%>
		<p>
			管理者：<input type="checkbox" name="admin_check">
		</p>
		<%
		}
		%>

		<!-- 確認 -->
		<input type="submit" value="確認">
	</form>
	<!-- 戻る -->
	<form action="UserListsServlet" method="post">
		<input type="submit" value="戻る">
	</form>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userLists</title>
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
	<!-- 新規登録ボタン -->
	<div class="userRegisterBtn">
		<form action="userRegister.jsp" method="post">
			<input type="submit" value="新規登録">
		</form>
	</div>
	<p>ID 権限 ユーザー名</p>
	<%
	//リストデータを取得 listへ代入
	List<UsersBean> list = (List<UsersBean>) request.getAttribute("list");

	//questionのデータが空でない場合
	if (list != null) {

		//listのデータ数分、繰り返し処理
		String admin="";
		for (int i = 0; i < list.size(); i++) {
			//admin_flagが1の場合は管理者
			if (list.get(i).getAdminFlag() == 1) {
				 admin= "管理者";
			//admin_flagがそれ以外の場合は一般
			}else{
				admin= "一般";
			}
	%>
	<!-- ユーザ一覧 -->
	<!-- ID -->
	<input readonly name="user_id" value="<%=list.get(i).getId()%>">

	<!-- 権限 -->
	<!-- 0：一般 、1：管理者-->
	<input readonly name="admin" value="<%=admin%>">

	<!-- ユーザー名 -->
	<input readonly name="user_name" value="<%=list.get(i).getName()%>">

	<!-- 編集 -->
	<form action="UserEditServlet" method="post">
		<input type="hidden" name="user_id" value="<%=list.get(i).getId()%>">
		<input type="submit" value="編集">
	</form>

	<!-- 削除ボタン -->
	<form action="UserDeleteConfirmServlet" method="post">
		<input type="hidden" name="questionId"
			value="<%=list.get(i).getId()%>"> <input type="submit"
			value="削除">
	</form>
	<%
			}
		}
	%>
</body>
</html>
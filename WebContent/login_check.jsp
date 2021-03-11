<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login_check</title>
</head>
<body>
<%
if (session == null) {
	session = request.getSession(true);
	response.sendRedirect("login.jsp");
}else {
	Object loginCheck = session.getAttribute("login_id");
	if (loginCheck == null){
		response.sendRedirect("login.jsp");
	}
}
%>

</body>
</html>
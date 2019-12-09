<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/logout.jsp</title>
</head>
<body>
<%
	// 세션 무효화 (기존 세션ID 삭제)
	session.invalidate();
%>
	<h3>로그아웃</h3>
	<a href="../index.jsp">Home으로</a>

</body>
</html>
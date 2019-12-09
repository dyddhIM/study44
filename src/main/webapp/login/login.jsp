<%@page import="com.study.common.util.CookieBox"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/login.jsp</title>
</head>
<body>
<%
	String msg = request.getParameter("msg");
%>
<h3>로그인</h3>
<% if(msg != null && !msg.isEmpty()){   %>
<div class="message">
	<span class="warning"><%=msg%></span>
</div>
<% } %>
<!-- <form action="loginCheck.jsp" > -->

<!-- 포워드 하신분은 login.jsp 의 action 방향을 절대경로로 변경 필요   -->
<form action="${pageContext.request.contextPath}/login/loginCheck.jsp" >
<%  // 만약 쿠키중에 s_id 가 존재하면 그 값을 value에 나오게 하시면 됩니다. 
   //  <input type="text" name="id" value="malja" > 
	//  <input type="checkbox" name="id_remember" value="Y" checked="checked" >
	CookieBox box = new CookieBox(request);
	String s_id = box.getValue("s_id");
	String id = "";
	String chk = "";
	if(s_id != null){
		id = s_id;
		chk = "checked=\"checked\"";
	}
	
%>
	<label>
		아이디 : <input type="text" name="id" value="<%=id%>">
	</label>
	<br>
	<label>
		비번 : <input type="password" name="pw">
	</label>
	<br>
	<label>
		<input type="checkbox" name="id_remember" value="Y" <%=chk%> > id 기억하기 
	</label>
	<br>
	<button type="submit">로그인</button>
</form>

</body>
</html>



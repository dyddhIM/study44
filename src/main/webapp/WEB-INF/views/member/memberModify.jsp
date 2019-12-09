<%@page import="com.study.member.dao.MemberDaoOracle"%>
<%@page import="com.study.member.dao.IMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<title>회원가입 결과</title>
  </head>
<body>
<div class="container">

<jsp:useBean id="member" class="com.study.member.vo.MemberVO"  />
<jsp:setProperty property="*" name="member" />

<c:catch var="ex">
<%
	IMemberDao memberDao = new MemberDaoOracle();
	int cnt = memberDao.updateMember(member);
	request.setAttribute("cnt", cnt);
%>
	<c:if test="${cnt > 0}">	
		<h3>가입성공</h3>
		<div>
			<a class="btn btn-sm btn-default" href="memberList.jsp" >회원목록</a>
			<a class="btn btn-sm btn-info" href="memberView.jsp?mem_id=<%=request.getParameter("mem_id") %>" >상세보기</a>
			<a class="btn btn-sm btn-info" href="memberView.jsp?mem_id=${param.mem_id}" >상세보기</a>
			<a class="btn btn-sm btn-info" href="memberView.jsp?mem_id=${member.mem_id}" >상세보기</a>
		</div>
	</c:if>
	<c:if test="${cnt == 0}">
		<h3>가입실패</h3>
		<div>
			<a class="btn btn-sm btn-default" href="memberList.jsp" >회원목록</a>
			<!-- // 뒤로가기  -->
			<a class="btn btn-sm btn-info"  href="#" onclick="history.go(-1)" >뒤로보기</a>
		</div> 	
	</c:if>
</c:catch> 

<c:if test="${not empty ex}">
	<h3>가입실패</h3>
	<div>
		 예외 정보 : ${ex.message} 
	</div>
	<div>
		<a class="btn btn-sm btn-default" href="memberList.jsp" >회원목록</a>
		<!-- // 뒤로가기  -->
		<a class="btn btn-sm btn-info" href="#" onclick="history.go(-1)" >뒤로보기</a>
	</div> 	
</c:if>
</div>
</body>
</html>


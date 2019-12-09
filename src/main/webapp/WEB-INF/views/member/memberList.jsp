<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.study.member.dao.MemberDaoOracle"%>
<%@page import="com.study.member.dao.IMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<title>회원목록</title>
  </head>
<body>
<div class="container">

<div class="row">
	<h3>회원 목록</h3>
</div>
<div class="row">
	<a href="memberForm.jsp" class="btn btn-primary btn-sm" >
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		회원가입
	</a>
</div>
<%
	IMemberDao memberDao = new MemberDaoOracle();
	List<MemberVO> list =  memberDao.selectMemberList();
	request.setAttribute("memberList", list);
%>
<table class="table table-striped">
	<colgroup>
		<col width="10%" />
		<col width="15%" />
		<col />
		<col width="20%" />
		<col width="10%" />
	</colgroup>
	<tr>
		<th>ID</th>
		<th>회원명</th>
		<th>주소</th>
		<th>메일</th>
		<th>마일리지</th>
	</tr>
	<c:forEach var="member" items="${memberList}" >
	<tr>
		<td><a href="memberView.jsp?mem_id=${member.mem_id}">
			  ${member.mem_id}
			</a>
		</td>
		<td>${member.mem_name}</td>
		<td>${member.mem_add1} ${member.mem_add2}</td>
		<td>${member.mem_mail}</td>
		<td>${member.mem_mileage}</td>
	</tr>
	</c:forEach>
</table>

</div>
</body>
</html>






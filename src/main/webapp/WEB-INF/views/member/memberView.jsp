<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.member.dao.MemberDaoOracle"%>
<%@page import="com.study.member.dao.IMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<title>회원상세정보</title>
  </head>
<body>
<div class="container">
<%
IMemberDao memberDao = new MemberDaoOracle();

String id = request.getParameter("mem_id");
MemberVO vo = memberDao.selectMember(id);
request.setAttribute("member", vo);
%>
<h3>회원 정보 상세</h3>
<table class="table">
	<colgroup>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col />
	</colgroup>
	<c:if test="${member != null}">	
	<tr>
		<th>ID</th>
		<td>${member.mem_id}</td>
		<th>회원명</th>
		<td>${member.mem_name}</td>
	</tr>
	<tr>
		<th>주민번호</th>
		<td colspan="3">${member.mem_regno1}-${member.mem_regno2}</td>
	</tr>
	<tr>
		<th>생일</th>
		<td colspan="3">${member.mem_bir}</td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td colspan="3">${member.mem_zip}</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${member.mem_add1} ${member.mem_add2}</td>
	</tr>
	<tr>
		<th>핸드폰</th>
		<td>${member.mem_hp}</td>
		<th>메일</th>
		<td>${member.mem_mail}</td>
	</tr>	
	<tr>
		<th>직업</th>
		<td>${member.mem_job_nm}</td>
		<th>취미</th>
		<td>${member.mem_like_nm}</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td colspan="3">${member.mem_mileage}</td>
	</tr>	
	</c:if>
	
	<c:if test="${empty member}">
		<tr>
			<td colspan="4">해당 회원이 존재하지 않습니다.</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="4">
			<a href="memberEdit.jsp?mem_id=${member.mem_id}" class="btn btn-sm btn-default">회원 정보 수정</a>
			<a href="memberList.jsp" class="btn btn-sm btn-info">회원 목록 </a>
		</td>
	</tr>	
</table>

</div>
</body>
</html>
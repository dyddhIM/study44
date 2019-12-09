<%@page import="com.study.code.vo.CommonCodeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.study.code.dao.CommonCodeDaoOracle"%>
<%@page import="com.study.code.dao.ICommonCodeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<title>회원가입</title>
  </head>
<body>
<div class="container">
<h3>회원 가입</h3>
<%
	ICommonCodeDao codeDao = new CommonCodeDaoOracle();
	List<CommonCodeVO> jobList = codeDao.selectCodeByType("JB00");
	List<CommonCodeVO> hobbyList = codeDao.selectCodeByType("HB00");
	
	request.setAttribute("jobList", jobList);
	request.setAttribute("hobbyList", hobbyList);
%>

<form action="memberRegist.jsp" method="post">
<table class="table">
	<colgroup>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col />
	</colgroup>
	<tr>
		<th>ID</th>
		<td><input type="text" name="mem_id" value=""></td>
		<th>회원명</th>
		<td><input type="text" name="mem_name" value=""></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td colspan="3"><input type="password" name="mem_pass" value=""></td>
	</tr>
	<tr>
		<th>주민번호</th>
		<td colspan="3">
			<input type="text" name="mem_regno1" value="">
			-
			<input type="password" name="mem_regno2" value="">
		</td>
	</tr>
	<tr>
		<th>생일</th>
		<td colspan="3"><input type="date" name="mem_bir" value=""></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td colspan="3"><input type="text" name="mem_zip" value=""></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="mem_add1" value=""> <input type="text" name="mem_add2" value=""></td>
	</tr>
	<tr>
		<th>핸드폰</th>
		<td><input type="text" name="mem_hp" value=""></td>
		<th>메일</th>
		<td><input type="email" name="mem_mail" value=""></td>
	</tr>	
	<tr>
		<th>직업</th>
		<td>
			<select name="mem_job">
				<option value="" >직업을 선택하세요</option>
				<c:forEach var="code" items="${jobList}">
					<option value="${code.com_cd}">${code.com_nm}</option>
				</c:forEach>	
			</select>
		</td>
		<th>취미</th>
		<td>
			<select name="mem_like">
				<option value="" >취미를 선택하세요</option>
				<c:forEach var="code" items="${hobbyList}">
					<option value="${code.com_cd}">${code.com_nm}</option>
				</c:forEach>			
			</select>
		</td>
	</tr>	
	<tr>
		<td colspan="4">
			<a href="memberList.jsp" class="btn btn-sm btn-default">회원 목록</a>
			<button type="submit"    class="btn btn-sm btn-success">가입</button>
		</td>
	</tr>	
</table>
</form>
</div>
</body>
</html>




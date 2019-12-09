<%@page import="com.study.member.dao.MemberDaoOracle"%>
<%@page import="com.study.member.dao.IMemberDao"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="com.study.code.vo.CommonCodeVO"%>
<%@page import="java.util.List"%>
<%@page import="com.study.code.dao.CommonCodeDaoOracle"%>
<%@page import="com.study.code.dao.ICommonCodeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp" %>
  	<title>회원가입</title>
  </head>
<body>
<div class="container">

<h3>회원 정보 수정</h3>
<%
IMemberDao memberDao = new MemberDaoOracle();

String id = request.getParameter("mem_id");
MemberVO vo = memberDao.selectMember(id);
request.setAttribute("member", vo);

ICommonCodeDao codeDao = new CommonCodeDaoOracle();
List<CommonCodeVO> jobList = codeDao.selectCodeByType("JB00");
List<CommonCodeVO> hobbyList = codeDao.selectCodeByType("HB00");

request.setAttribute("jobList", jobList);
request.setAttribute("hobbyList", hobbyList);
%>

<form action="memberModify.jsp" method="post">
<table class="table " >
	<colgroup>
		<col width="15%" />
		<col width="35%" />
		<col width="15%" />
		<col />
	</colgroup>
	<tr>
		<th>ID</th>
		<td><input type="hidden" name="mem_id" value="${member.mem_id}">
		     ${member.mem_id}
		</td>
		<th>회원명</th>
		<td><input type="text" name="mem_name" value="${member.mem_name}" ></td>
	</tr>
	<tr>
		<th>주민번호</th>
		<td colspan="3">
			<input type="text" name="mem_regno1" value="${member.mem_regno1}">
			-
			<input type="password" name="mem_regno2" value="${member.mem_regno2}">
		</td>
	</tr>
	<tr class="form-group-sm">
		<th>생일</th>
		<td colspan="3"><input type="date" class="form-control col-md-5"  name="mem_bir" value="${member.mem_bir}"></td>
	</tr>
	<tr class="form-group-sm">
		<th>우편번호</th>
		<td colspan="3"><input type="text" class="form-control" name="mem_zip" value="${member.mem_zip}"></td>
	</tr>
	<tr class="form-group-sm">
		<th>주소</th>
		<td><input type="text" name="mem_add1" class="form-control" value="${member.mem_add1}"> <input type="text" name="mem_add2" value="${member.mem_add2}"></td>
	</tr>
	<tr  class="form-group-sm">
		<th>핸드폰</th>
		<td><input type="text" class="form-control" name="mem_hp" value="${member.mem_hp}"></td>
		<th>메일</th>
		<td><input type="email" class="form-control" name="mem_mail" value="${member.mem_mail}"></td>
	</tr>	
	<tr>
		<th>직업 ${member.mem_job} , ${member.mem_job_nm} </th>
		<td>
			
			<div class="form-group-sm">
			<select name="mem_job" class="form-control" >
				<option value="" >직업을 선택하세요</option>
				<c:forEach var="code" items="${jobList}">
					<c:if test="${member.mem_job == code.com_cd}">
						<option value="${code.com_cd}"  selected="selected" >${code.com_nm}</option>
					</c:if>
					<c:if test="${member.mem_job ne code.com_cd}">
						<option value="${code.com_cd}" >${code.com_nm}</option>
					</c:if>
				</c:forEach>	
			</select>
			</div>
		</td>
		<th>취미</th>
		<td class="form-group-sm">
			<select name="mem_like" class="form-control" >
				<option value="" >취미를 선택하세요</option>
				<c:forEach var="code" items="${hobbyList}">
					<c:if test="${member.mem_like == code.com_cd}">
						<option value="${code.com_cd}"  selected="selected" >${code.com_nm}</option>
					</c:if>
					<c:if test="${member.mem_job ne code.com_cd}">
						<option value="${code.com_cd}" >${code.com_nm}</option>
					</c:if>
				</c:forEach>			
			</select>
		</td>
	</tr>	
	<tr>
		<td colspan="4">
			<a href="memberList.jsp" class="btn btn-sm btn-default">회원목록</a>
			<button type="submit"    class="btn btn-sm btn-success">수정</button>
		</td>
	</tr>	
</table>
</form>
</div>
</body>
</html>




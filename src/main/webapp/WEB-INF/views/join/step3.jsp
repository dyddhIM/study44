<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>회원가입 3단계</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top_menu.jsp"%>
<div class="container">

<div class="row col-md-8 col-md-offset-2">
	<div class="page-header">
		<h3>회원가입 3단계</h3>
	</div>

	<form action="regist" method="post">
	<table class="table" >
		<colgroup>
			<col width="20%" />
			<col />
		</colgroup>
		
		<tr class="form-group-sm">
			<th>주민번호</th>
			<td >
				<input type="text" name="mem_regno1"  value="${memberJoin.mem_regno1}">
				-
				<input type="password" name="mem_regno2" value="${memberJoin.mem_regno2}">
			</td>
		</tr>
		<tr class="form-group-sm">
			<th>생일</th>
			<td><input type="date" class="form-control col-md-5"  name="mem_bir" value="${memberJoin.mem_bir}"></td>
		</tr>
		<tr class="form-group-sm">
			<th>우편번호</th>
			<td><input type="text" class="form-control" name="mem_zip" value="${memberJoin.mem_zip}"></td>
		</tr>
		<tr class="form-group-sm">
			<th>주소</th>
			<td><input type="text" name="mem_add1" class="form-control" value="${memberJoin.mem_add1}"> 
				<input type="text" name="mem_add2" value="${memberJoin.mem_add2}">
			</td>
		</tr>
		<tr  class="form-group-sm">
			<th>핸드폰</th>
			<td><input type="text" class="form-control" name="mem_hp" value="${memberJoin.mem_hp}"></td>
		</tr>	
		<tr>
			<th>직업 </th>
			<td>
				<div class="form-group-sm">
				<select name="mem_job" class="form-control" >
					<option value="" >직업을 선택하세요</option>
					<c:forEach var="code" items="${jobList}">
						<c:if test="${memberJoin.mem_job == code.com_cd}">
							<option value="${code.com_cd}"  selected="selected" >${code.com_nm}</option>
						</c:if>
						<c:if test="${memberJoin.mem_job ne code.com_cd}">
							<option value="${code.com_cd}" >${code.com_nm}</option>
						</c:if>
					</c:forEach>	
				</select>
				</div>
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td class="form-group-sm">
				<select name="mem_like" class="form-control" >
					<option value="" >취미를 선택하세요</option>
					<c:forEach var="code" items="${hobbyList}">
						<c:if test="${memberJoin.mem_like == code.com_cd}">
							<option value="${code.com_cd}"  selected="selected" >${code.com_nm}</option>
						</c:if>
						<c:if test="${memberJoin.mem_like ne code.com_cd}">
							<option value="${code.com_cd}" >${code.com_nm}</option>
						</c:if>
					</c:forEach>			
				</select>
			</td>
		</tr>	
		<tr>
			<td colspan="2">
				<div class="pull-left" >
					<a href="${pageContext.request.contextPath}/join/cancel" class="btn btn-sm btn-default" >
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						&nbsp;&nbsp;취 소
					</a>
				</div>
				<div class="pull-right">
					<button type="submit" class="btn btn-sm btn-primary" >
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> 
						&nbsp;&nbsp;다 음 
					</button>
				</div>
			</td>
		</tr>		
	</table>
	</form>
</div>

</div> <!-- END : 메인 콘텐츠  컨테이너  -->
</body>
</html>




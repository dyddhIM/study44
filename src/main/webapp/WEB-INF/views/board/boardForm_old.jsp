<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
</head>
<body>
<!-- boardForm.do 의 뷰이면서, boardRegist.do의 입력이 잘못되었을 때의 뷰이기도 합니다. -->
	<div class="container">
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
		<div class="page-header">
			<h3>글작성</h3>
		</div>
		<form action="boardRegist.do" method="post">
			<table class="table">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col  />
				</colgroup>
				<tr>
					<th>글제목</th>
					<td colspan="3">
						<input type="text" name="bo_title" value="${board.bo_title}">
						<span>${errors.bo_title}</span>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="bo_writer" value="${board.bo_writer}" >
						<span>${errors.bo_writer}</span>
					</td>
					<th>패스워드 </th>
					<td><input type="password" name="bo_pass" value=""
					           placeholder="수정 및 삭제시 필요">
					    <span>${errors.bo_pass}</span>       
					</td>
				</tr>
				<tr>
					<th>분류</th>
					<td colspan="3">
						<select name="bo_class">
							<option value="" >게시물 분류를 선택해 주세요 </option>
							<c:forEach var="code" items="${bcList}">
								<option value="${code.com_cd}">${code.com_nm}</option>
							</c:forEach>	
						</select>
						<span>${errors.bo_class}</span>
					</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td colspan="3">
						<textarea rows="10" cols="60" name="bo_content">${board.bo_content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<a href="boardList.jsp" class="btn btn-sm btn-default">글목록</a>
						<button type="submit" class="btn btn-sm btn-success">등록</button>
				</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
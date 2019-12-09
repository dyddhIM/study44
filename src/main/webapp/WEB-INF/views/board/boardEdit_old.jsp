<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!-- trimDirectiveWhitespaces="true" : 공백 삭제, byte 절감 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
</head>
<body>
	<div class="container">
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
		<h3>글수정</h3>
		<form action="boardModify.do" method="post">
			<table class="table">
				<colgroup>
					<col width="15%" />
					<col />
					<col width="15%" />
					<col />
				</colgroup>
				<tr>
					<th>글제목</th>
					<td colspan="3">
						<input type="text" name="bo_title" class="form-control" value="${board.bo_title}">
						<span>${errors.bo_title}</span>
					</td>
				</tr>
				<tr>
					<th><input type="hidden" name="bo_no" value="${board.bo_no}">글번호</th>
					<td colspan="3">${board.bo_no}</td>					
				</tr>
				<tr>	
					<th>작성자</th>
					<td><input type="text" name="bo_writer"  class="form-control" value="${board.bo_writer}">
						<span>${errors.bo_writer}</span>
					</td>
					<th>비밀번호</th>
					<td><input type="password" name="bo_pass"  class="form-control" value="">
						<span>${errors.bo_pass}</span>
					</td>
				</tr>	
				<tr>
					<th>분류</th>
					<td colspan="3">
					<select name="bo_class" class="form-control">
						<option value="" >게시물 분류를 선택해 주세요 </option>
						<c:forEach var="code" items="${bcList}">
							<option value="${code.com_cd}"
							  ${board.bo_class == code.com_cd ? 'selected="selected"' : '' } >
								${code.com_nm}
							</option>
						</c:forEach>	
					</select>
					<span>${errors.bo_class}</span>
					</td>
				</tr>
				
				<tr>	
					<th>ip</th>
					<td>${board.bo_ip}</td>				
					<th>조회수</th>
					<td>${board.bo_hit}</td>
				</tr>			
				<tr>	
					<th>등록일</th>
					<td>${board.bo_reg_date}</td>				
					<th>수정일</th>
					<td>${board.bo_mod_date}</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td colspan="3">
						<textarea rows="5" name="bo_content" class="form-control" >${board.bo_content}</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<a href="boardList.do" class="btn btn-sm btn-default">							
							글목록
						</a>
						<button type="submit" class="btn btn-sm btn-success">
							수정
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
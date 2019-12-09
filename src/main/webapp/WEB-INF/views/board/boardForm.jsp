<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$("#id_btn_new_file").click(function(){
		// 파일추가 버튼 클릭 
		$(".file_area").append("<div class='form-inline'>"
							+ "<input type='file' name='bo_files' class='form-control'>"
							+ " <button type='button' class='btn_delete btn btn-sm'>삭제</button>"
							+ "</div>");
		});
		
		// 파일 삭제버튼 클릭 
		$('.file_area').on('click','.btn_delete', function(){
			$(this).closest('div').remove();
		});
	
});	// ready 


</script>

</head>
<body>
<!-- boardForm.do 의 뷰이면서, boardRegist.do의 입력이 잘못되었을 때의 뷰이기도 합니다. -->
	<div class="container">
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
		<div class="page-header">
			<h3>글작성</h3>
		</div>
		<form:form commandName="board" action="boardRegist.do" 
		           method="post" enctype="multipart/form-data" >		
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
						<form:input path="bo_title"/>
						<form:errors path="bo_title"  />
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><form:input path="bo_writer"/>
						<form:errors path="bo_writer" />
					</td>
					<th>패스워드 </th>
					<td><form:password path="bo_pass" placeholder="수정 및 삭제시 필요" />						
					    <form:errors path="bo_pass"  />       
					</td>
				</tr>
				<tr>
					<th>분류</th>
					<td colspan="3">
						<form:select path="bo_class">
							<option value="" >게시물 분류를 선택해 주세요 </option>
							<form:options items="${bcList}" itemValue="com_cd" itemLabel="com_nm" />
						</form:select>						
						<form:errors path="bo_class" />
					</td>
				</tr>
				
				<tr>
					<th>첨부파일
						<button type= "button" id="id_btn_new_file">추가</button>
					</th>
					<td class= "file_area" colspan="3">
						<div class= "form-inline">
							<input type="file" name="bo_files" class="form-control" >
							<button type="button" class="btn_delete btn btn-sm" > 삭제</button>
						</div>
					</td>
				</tr>
				
				<tr>
					<th>글내용</th>
					<td colspan="3">
						<form:textarea path="bo_content" rows="10" cols="60" />						
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<a href="boardList.jsp" class="btn btn-sm btn-default">글목록</a>
						<button type="submit" class="btn btn-sm btn-success">등록</button>						
				</td>
				</tr>
			</table>
		</form:form>
	</div>
</body></html>
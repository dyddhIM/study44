<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!-- trimDirectiveWhitespaces="true" : 공백 삭제, byte 절감 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<script type="text/javascript">
	function fn_delete(){
		var f = document.forms["frm_board"];
		f.action = "boardDelete.do"
		f.submit();
	}
	
</script>

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
		
		
		// 기존 첨부파일 삭제버튼
		$(".btn_file_delete").click(function(){
			$btn =  $(this);
			atch_no = $btn.data("atch-no");
			// alert($btn.data("atch-no"));
			$(this).closest('div').html("<input type='hidden' name='del_atch_nos' value='" + atch_no + "' />");			
		});
		
		
		
	
});	// ready 


</script>



</head>
<body>
	<div class="container">
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
		<h3>글수정</h3>
		<form:form name="frm_board" commandName="board" action="boardModify.do"
		            method="post" enctype="multipart/form-data" >
			<form:hidden path="bo_no"/>
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
						<form:input path="bo_title" cssClass="form-control"/>
						<form:errors path="bo_title" />
					</td>
				</tr>
				<tr>
					<th>글번호</th>
					<td colspan="3">${board.bo_no}</td>					
				</tr>
				<tr>	
					<th>작성자</th>
					<td>
						<form:input path="bo_writer" cssClass="form-control" />
						<form:errors path="bo_writer" />
					</td>
					<th>비밀번호</th>
					<td>
						<form:password path="bo_pass" cssClass="form-control"/>
						<form:errors path="bo_pass" />
						
					</td>
				</tr>	
				<tr>
					<th>분류</th>
					<td colspan="3">
						<form:select path="bo_class" cssClass="form-control">
							<option value="" >게시물 분류를 선택해 주세요 </option>
							<form:options items="${bcList}" itemValue="com_cd" itemLabel="com_nm" />
						</form:select>					
						<form:errors path="bo_class" />
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
					<th>첨부파일
						<button type= "button" id="id_btn_new_file">추가</button>
					</th>
					<td class= "file_area" colspan="3">
						<c:forEach var="f" items="${board.attaches}">
						<div>
							<a href="<c:url value='/attach/download/${f.atch_no}' />" target="_blank"> 
								<span class="glyphicon glyphicon-save" aria-hidden="true"></span> 
								${f.atch_original_name}
							</a> 
							Size : ${f.atch_fancy_size} 
							Down : ${f.atch_down_cnt}
							<button type="button" data-atch-no="${f.atch_no}" class="btn_file_delete btn btn-sm" > 삭제</button>
						</div>
						</c:forEach>					
						<div class= "form-inline">
							<input type="file" name="bo_files" class="form-control" >
							<button type="button" class="btn_delete btn btn-sm" > 삭제</button>
						</div>
					</td>
				</tr>				
				
				<tr>
					<th>글내용</th>
					<td colspan="3">
						<form:textarea path="bo_content" cssClass="form-control" rows="5" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<a href="boardList.do" class="btn btn-sm btn-default">
							<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 
							&nbsp;&nbsp;글목록
						</a>
						<button type="submit" class="btn btn-sm btn-success">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 
							&nbsp;&nbsp;수정
						</button>
						<button type="button" class="btn btn-sm btn-danger" onclick="fn_delete()">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 
							&nbsp;&nbsp;삭제 
						</button>
						
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
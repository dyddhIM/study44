<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>타이틀 입력</title>
<script type="text/javascript">

// 퐁 서브밋 
var fn_search_submit = function(){
	var f = document.forms["frm_boardSearch"];
	f.submit();
}

// 페이지 변경후 서브밋 호출 
var fn_go_page = function(p){
	var f = document.forms["frm_boardSearch"];
	f.curPage.value = p;
	fn_search_submit();	
}

// 목록갯수 변경후 선택버튼 클리시  서브밋 호출 
var fn_screen_size_change=function(){
	var f = document.forms["frm_boardSearch"];
	// 페이지는 1로 변경 
	// 
	// f.screenListSize.value = ???;
	// fn_search_submit();
}

// 초기화 
var fn_search_reset=function(){	
	var f = document.forms["frm_boardSearch"];
	f.curPage.value = 1;
	f.searchType.value = "";
	f.searchWord.value = "";
	f.searchClass.value = "";	
}


$(document).ready(function(){
	// 전체 체크박스 클릭
	$("#id_board_check_all").click(function(){
		$("input[type=checkbox][name=bo_nos]").prop("checked", $(this).prop("checked"));
	}); // 전체 체크박스 클릭
	
	// 선택글 삭제 버튼 클릭 
	$("#id_check_delete").click(function(){
		f = document.forms["frm_board_list"];
		f.action = "<c:url value='/admin/board/checkedDelete' />";
		f.submit();
		
	}); // 선택글 삭제 버튼 클릭
	
});


</script>
<style type="text/css">
.table.table-ellipsis tbody td
 {
    max-width: 100px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.next-bg-gray {background-color: #f4f3f3;}

</style>


</head>
<body>
	<div class="container">
	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
		<div class="page-header">		
			<h3>자유 게시판 목록</h3>
		</div>
	<div class="panel panel-default">	
		<div class="panel-body form-horizontal">
			<form name="frm_boardSearch" action="boardList.do" >
			<input type="hidden" name="curPage" value="${searchVO.curPage }">
			<input type="hidden" name="screenListSize" value="${searchVO.screenListSize}">
			<div class="form-group">		
				<label class="col-sm-2  control-label">구분</label>
				<div class="col-sm-3">
					<select  name="searchType"  class="form-control input-sm" >
						<option value="">-- 전체 -- </option>
						<option value="T" ${searchVO.searchType == 'T' ? 'selected="selected"' : ''} >제목</option>
						<option value="W" ${searchVO.searchType == 'W' ? 'selected="selected"' : ''}>작성자</option>
						<option value="C" ${searchVO.searchType == 'C' ? 'selected="selected"' : ''}>내용</option>
					</select>
				</div>
			
				<label class="col-sm-2 control-label">검색어</label>
				<div class="col-sm-3">
			     	<input type="text" name="searchWord" class="form-control  input-sm" value="${searchVO.searchWord}"> 
			    </div> 
			</div>
			<div class="form-group">
			    <label class="col-sm-2 control-label">내용분류</label> 
			    <div class="col-sm-3">
			     	   <select name="searchClass"  class="form-control input-sm">
							<option value="" >-- 전체 -- </option>
							<c:forEach var="code" items="${bcList}">								
								<option value="${code.com_cd}" 
								        ${searchVO.searchClass == code.com_cd ? 'selected="selected"' : ''} > 
								   ${code.com_nm}
								</option>
							</c:forEach>	
						</select> 
				</div>
			</div>	
			<div>
				<div class="pull-left" >			
					<button type="button" class="btn btn-sm btn-default" onclick="fn_search_reset()">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
						&nbsp;&nbsp;초기화
					</button>
				</div>
				<div class="pull-right">
					<button type="submit" class="btn btn-sm btn-primary" >
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 
						&nbsp;&nbsp;검 색
					</button>
				</div>
			</div>	
		</form>
	  </div>		
	</div>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="pull-left col-sm-8">			
					<label class="col-sm-4 control-label">
						전체 : ${searchVO.totalRowCount}, ${searchVO.curPage} / ${searchVO.totalPageCount} pages
					</label>
					<div class="col-sm-2">				
						<select name="" class="form-control input-sm" >
							<option value="10" ${searchVO.screenListSize == 10 ? 'selected="selected"' : ''}>10</option>
							<option value="15" ${searchVO.screenListSize == 15 ? 'selected="selected"' : ''}>15</option>
							<option value="20" ${searchVO.screenListSize == 20 ? 'selected="selected"' : ''}>20</option>
							<option value="30" ${searchVO.screenListSize == 30 ? 'selected="selected"' : ''}>30</option>
						</select>
					</div> 
					<button type="button" class="btn btn-sm btn-default" onclick="fn_screen_size_change()" >선택</button>				
			</div>
			<div class="pull-right">
				<button id="id_check_delete" class="btn btn-default btn-sm"> 
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 
					선택글 삭제
				</button>
				<a href="boardForm.do" class="btn btn-primary btn-sm"> 
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 
					글 작성
				</a>
			</div>		
		</div>		
		<form name="frm_board_list" action="" method="post">
		<table class="table table-striped table-bordered table-ellipsis">
			<colgroup>
				<col width="5%" />
				<col width="7%" />
				<col />
				<col width="15%" />
				<col width="10%" />
				<col width="15%" />
				<col width="10%" />
			</colgroup>
			<thead>	
				<tr class="text-center">
					<th class="text-center"><input type="checkbox" id="id_board_check_all" > </th>
					<th>No</th>
					<th>제목</th>
					<th>분류</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr class="text-center">
						<td><input type="checkbox" name="bo_nos" value="${board.bo_no}" > </td>
						<td>${board.bo_no}</td>
						<td class="text-left">
							<a href="boardView.do?bo_no=${board.bo_no}">
								${board.bo_title}
							</a>
						</td>
						<td>${board.bo_class_nm}</td>
						<td>${board.bo_writer}</td>
						<td>${board.bo_reg_date}</td>
						<td>${board.bo_hit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
	</div>
		<nav class="text-center">
		  <ul class="pagination">
		  	<!-- 이전 페이지  -->
		    <li>
		      <a href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    
		    <!-- 페이징  -->
		    <c:forEach var="i" begin="${searchVO.startPage}" end="${searchVO.endPage}" >
		    	<c:if test="${i ==  searchVO.curPage}">
		    		<li class="active"><a href="#">${i}</a></li>
		    	</c:if>
		    	<c:if test="${i !=  searchVO.curPage}">
		    		<li><a href="#" onclick="fn_go_page(${i})">${i}</a></li>
		    	</c:if>
		    </c:forEach>		    
		    
		    <!-- 다음 페이지  -->
		    <li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		    
		    
		  </ul>
		</nav>
		
		
		
		
		
	</div>
</body>
</html>
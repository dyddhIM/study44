<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>boardView.jsp</title>


</head>
<body>
<div class="container">	
<%@include file="/WEB-INF/inc/top_menu.jsp"%>
	<div class="page-header">
		<h3>글 내용 상세</h3>
	</div>
	<table class="table table-bordered">
		<colgroup>
			<col width="15%" />
			<col />
			<col width="15%" />
			<col />
		</colgroup>
		<tr>
			<th>글 제목</th>
			<td>${board.bo_title}</td>
			<th>조회수</th>
			<td>${board.bo_hit}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.bo_writer}</td>
			<th>작성자 IP</th>
			<td>${board.bo_ip}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.bo_reg_date}</td>
			<th>수정일</th>
			<td>${board.bo_mod_date}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="3">
				<c:forEach var="f" items="${board.attaches}">
				<div>
					<a href="<c:url value='/attach/download/${f.atch_no}' />" target="_blank"> 
						<span class="glyphicon glyphicon-save" aria-hidden="true"></span> 
						${f.atch_original_name}
					</a> 
					Size : ${f.atch_fancy_size} 
					Down : ${f.atch_down_cnt}
				</div>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td colspan="3">
				<pre >${board.bo_content}</pre>	
			</td>
		</tr>
		<c:if test="${empty board}">
			<tr>
				<td colspan="4">존재하지 않는 게시글입니다.</td>
			</tr>
		</c:if>
	
		<td colspan="4">
			<a href="boardList.do" class="btn btn-sm btn-default">
				<span class="glyphicon glyphicon-list" aria-hidden="true"></span> 
				&nbsp;&nbsp;글목록
			</a> 
			<a href="boardEdit.do?bo_no=${board.bo_no}" class="btn btn-sm btn-info" >
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 
				&nbsp;&nbsp;수 정
			</a>
		</td>
	</table>
<script type="text/javascript">
var curPage = 1;
var screenListSize = 10;

$(document).ready(function(){
	
	
	// 댓글 수정하기 버튼 이벤트 함수 
	$("#id_reply_list_area").on("click", "button[name=btn_reply_edit]", function(){
		alert("댓글 수정하기 버튼 클릭");
		// 내용은 숨기고 -> textarea 생성 -> 댓글내용을 textarea 복사 
		// 수정버튼은 숨기고 -> 저장버튼 생성
	}); // btn_reply_edit
	
	// 댓글 수정의 저장하기 버튼 이벤트 함수
	$("#id_reply_list_area").on("click", "button[name=btn_reply_modify]", function(){
		// 서버에 보낼 파라미터 취합 
		// ajax로 서버 호출 
		//  - 성공 : textarea의 내용을 댓글내용에 복사, textarea 제거  
		//           저장버튼 삭제, 수정버틍 보이게     
		//  - 실패 : 
	}); // btn_reply_modify
	
	// 댓글 삭제하기 버튼 이벤트 함수
	// 문서가 생성된 후에 추가된 동적으로 생성된 객체는 상위객체이 이벤트를 설정하고
	// 그안에 해당 객체 를 기술  on 을 사용하셔야 합니다.
	$("#id_reply_list_area").on("click", "button[name=btn_reply_delete]", function(){
		$btn = $(this); // 현재 버튼객체 보관 , javascript 객체 -> jquery 개체
		res  = confirm("정말로 삭제하시겠습니까?"); // yes -> true, no = false 리턴  
		if(res){
			params = "re_no="+ $btn.data("reNo"); // "re-no" or "reNo" 접근
			// find : 하위 객체 검색
			// closest : 가장  가까운 상위 객체 검색 
			$.ajax({
				 type : "POST"
			   , data : params 
			   , url  : "<c:url value='/reply/replyDelete' />"
			   , dataType : "json"
			   , success : function(data){
				   if(data.result){
					   alert(data.msg);
					   $btn.closest("div.row").remove();  
				   }else{
					   alert(data.msg);
				   }
			     }
			   , error : function(){
				   alert("error");
			   }
			});  //$.ajax 
		} // if 
		
	}); // btn_reply_delete
	
	
	
	// 더보기 버튼 이벤트 함수 
	$("#btn_reply_list_more").click(function(ev) {
		fn_reply_list();	
	}); // btn_reply_list_more 	
	
	// 댓글 가져오기 함수 
	function fn_reply_list(){
		params = {"curPage" : curPage , "screenListSize" : screenListSize 
				  , "re_category" : "BOARD" , "re_parent_no" : ${board.bo_no} }	
		console.log("params", params);		
		$.ajax({
			 type : "GET"
		   , data : params 
		   , url  : "<c:url value='/reply/replyList' />"
		   , dataType : "json"
		   , success : function(data){
			   console.log("data", data);
			   if(data.result){		
				   // if count < screenListSize : 더보기 버튼 안보이게 
				   // else  curPage 1 증가 
				   if(data.count < screenListSize){
					   $("#id_reply_list_more").hide();
				   }else{
					   curPage++;
				   }
				   // data.data 를 반복문($.each) 화면에 "id_reply_list_area" append
				   $.each(data.data,function(i,e){						
						str = " "; 
						str +='	<div class="row"> ';                                                                                      
						str +=' <div class="col-sm-2 text-right" >' + e.re_mem_name + '..' + e.re_no + '</div>';                                                              
						str +='	 <div class="col-sm-6"><pre> '+ e.re_content +'</pre></div> ';                                                                
						str +='	 <div class="col-sm-2" >'+ e.re_reg_date +'</div>   ';                                                                 
						str +='	 <div class="col-sm-2"> ';     
						if(e.re_mem_id=='${sessionScope.LOGIN_INFO.mem_id}'){
						  str +='	  <button name="btn_reply_edit" type="button" class="btn btn-sm btn-info" >수정</button>';                 
						  str +='	  <button name="btn_reply_delete" type="button" data-re-no="' + e.re_no + '" class="btn btn-sm btn-danger" >삭제</button>';             
						}
						str +='	 </div>';              
						str +='</div>';
                        $("#id_reply_list_area").append(str);
					   })
			   }else{
				   alert(data.msg);
			   }
		     }
		   , error : function(xhr, status, error){
			   console.log("xhr", xhr);
			   console.log("status", status);
			   console.log("error", error);
			   if(xhr.status == 401){
				   alert("로그인이 필요해요..");
				   window.location.href = "<c:url value='/login/login.jsp' />";
			   }
		   }  
		});  //$.ajax 
	} // fn_reply_list
	
	$("#btn_reply_regist").click(function(){		
		params = $("form[name=frm_reply]").serialize();
		alert(params);
		$.ajax({
			 type : "POST"
		   , data : params 
		   , url  : "<c:url value='/reply/replyRegist' />"
		   , dataType : "json"
		   , success : function(data){
			   if(data.result){
				   alert(data.msg);
				   //  
			   }else{
				   alert(data.msg);
			   }
		     }
		   , error : function(){
			   alert("error");
		   }
		});  //$.ajax 
	});  // btn_reply_regist
	
	
	// 초기화 함수 호출
	fn_reply_list();	
	
});


</script>
		
	<div class="panel panel-default">	
		<div class="panel-body form-horizontal">
			<form name="frm_reply"  
			      action="<c:url value='/reply/replyRegist' />" 
			      method="post" 
			      onclick="return false;" >
			<input type="hidden" name="re_parent_no" value="${board.bo_no}">
			<input type="hidden" name="re_category" value="BOARD">
			<div class="form-group">		
				<label class="col-sm-2  control-label">댓글</label>
				<div class="col-sm-8">
					<textarea rows="3" name="re_content" class="form-control" ></textarea>
				</div>			
				<div class="col-sm-2">
					<button id="btn_reply_regist" type="button" class="btn btn-sm btn-info" >등록</button>
				</div>
			</div>
			</form>
		</div>
	</div>	
	
	<div id="id_reply_list_area">
		<div class="row">
			<div class="col-sm-2 text-right" >홍길동</div>
			<div class="col-sm-6"><pre>내용</pre></div>
			<div class="col-sm-2" >12/30 23:45</div>
			<div class="col-sm-2">
				<button name="btn_reply_edit" type="button" class=" btn btn-sm btn-info" >수정</button>
				<button name="btn_reply_delete" type="button" class="btn btn-sm btn-danger" >삭제</button>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-2 text-right">밀키스</div>
			<div class="col-sm-6"><pre> 싸랑해요 밀키스!~~~</pre></div>
			<div class="col-sm-2" >11/25 12:45</div>
			<div class="col-sm-2">			
			</div>
		</div>
	</div>
	
	<div class="row text-center" id="id_reply_list_more">
		<button id="btn_reply_list_more">
			<span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
			더보기 
		</button>
	</div>
		
</div>
</body>
</html>








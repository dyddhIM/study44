<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="/WEB-INF/inc/common_header.jsp"%>
<title>회원가입 1단계</title>
</head>
<body>
<%@include file="/WEB-INF/inc/top_menu.jsp"%>
<div class="container">

<div class="row col-md-8 col-md-offset-2">
	<div class="page-header">
		<h3>회원가입 1단계</h3>
	</div>
	
	<form action="step2" method="post">
	
	<div class="panel panel-default">
	  <div class="panel-heading">
	  	<label>
	  		<input type="checkbox" name="agree_yn"  value="Y" 
			    	       ${memberJoin.agree_yn eq 'Y' ? 'checked="checked"' : ''}  >
			이용약관 동의
	  	</label>
	  </div>
	  <div class="panel-body">
	   <p>Study 사이트에 오신것을 환영합니다.
       <p>Study 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 
	   <p>본 약관은 다양한 서비스의 이용과 관련하여 본 사이트 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 
		  아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.		
	   <p>서비스를 이용하시거나 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 
		잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.
		
	  </div>
	</div>
	
	<div class="panel panel-default">
	  <div class="panel-heading">
	  	<label>
	  		<input type="checkbox" name="privacy_yn" value="Y"
	    	        ${memberJoin.privacy_yn eq 'Y' ? 'checked="checked"' : ''}  > 
	    	개인정보 수집 및 이용에 대한 동의
	     </label>
	  </div>
	  <div class="panel-body">
	   <p>정보통신망법 규정에 따라 회원가입 신청하시는 분께 수집하는 개인정보에 대하여 알려드립니다. 
 	   <p>1. 개인정보의 수집 항목 
	   <p>2. 개인정보의 수집 및 이용목적
	   <p>3. 개인정보의 보유 및 이용기간
	   <p>위 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.	   	  
	   </div>
	</div>
	
	<div class="panel panel-default">
	  <div class="panel-body form-horizontal">
			<div class="form-group">		
				<label class="col-sm-3  control-label">이메일</label>
				<div class="col-sm-7">
					<input type="email" name="mem_mail" class="form-control input-sm" value="${memberJoin.mem_mail}" >
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
	  <div class="panel-body">
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
		</div>	
		</div>
	</form>

</div>


</div> <!-- END : 메인 콘텐츠  컨테이너  -->
</body>
</html>
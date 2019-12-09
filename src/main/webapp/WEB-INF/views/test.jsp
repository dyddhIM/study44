<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>타이틀 입력</title>
    <!-- 부트스트랩 -->
    <link href="${pageContext.request.contextPath}/bootstrap-3.3.2/css/bootstrap.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.2/js/bootstrap.js"></script>
  </head>
  <body>

<form:form commandName="member" action="memberRegist">
ID : <form:input path="mem_id" />
회원명 : <form:input path= "mem_name" />
직업 :
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


직업1 : 
	<form:select cssClass="form-control" path="mem_job"
	             items="${jobList}" itemLabel="com_nm"  itemValue="com_cd" />

직업2 : <form:select path= "mem_job"  cssClass="form-control" >
			<option value="" >직업을 선택하세요</option>
			<form:options items="${jobList}" itemValue="com_cd" itemLabel="com_nm" />
		</form:select>
		
직업3 : <form:select path= "mem_job" cssClass="form-control" >
			<form:option value="JB01">말자</form:option>
			<form:option value="JB02">순자</form:option>
			<form:option value="JB03">놀자</form:option>
			<form:option value="JB04">하자</form:option>
		</form:select>
<hr>
<form:radiobuttons path="mem_job" 
      items="${jobList}" itemLabel="com_nm" itemValue="com_cd" delimiter="<br>" />

<hr>
<form:checkboxes path="mem_jobs" 
                 items="${jobList}" 
                 itemLabel="com_nm" 
                 itemValue="com_cd"  />


</form:form>

  </body>
</html>










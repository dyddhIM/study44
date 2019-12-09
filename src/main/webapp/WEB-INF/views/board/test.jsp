<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="ko">
  <head>
  	<%@include file="/WEB-INF/inc/common_header.jsp"%>
  	<title>업로드 테스트 /WEB-INF/views/board/test.jsp </title>
  </head>
  <body>
 <div class="container"> 
  	<%@include file="/WEB-INF/inc/top_menu.jsp"%>
  	Get 으로 
  	<form action="upload.do" >
  		제목 : <input type="text" name="bo_title"> <br>
  		파일 1 : <input type="file" name="bo_file"> <br>
  		<button type="submit">전송</button>  
  	</form>
  	<hr>
  	Post 로 
  	<form action="upload.do"  method="post" >
  		제목 : <input type="text" name="bo_title"> <br>
  		파일 1 : <input type="file" name="bo_file"> <br>
  		<button type="submit">전송</button>
  	</form>
  	<hr>
  	진짜로 업로드 하실려면... post multipart/form-data
  	<form action="upload.do"  method="post" enctype="multipart/form-data">
  		제목 : <input type="text" name="bo_title"> <br>
  		파일 1 : <input type="file" name="bo_file"> <br>
  		<button type="submit">전송</button>
  	</form>

</div>
  </body>
</html>
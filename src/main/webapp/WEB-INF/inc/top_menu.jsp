<%@page import="com.study.member.vo.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Static navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Project name</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
				<li><a
					href="${pageContext.request.contextPath}/board/boardList.do">About</a></li>
				<li><a href="${pageContext.request.contextPath}/mypage/list.do">회원 관리</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"> 게시판
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a
							href="${pageContext.request.contextPath}/board/boardList.do">자유
								게시판</a></li>
						<li><a href="#">QnA</a></li>
						<li><a href="#">자료실</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath}/board/test.do">첨부파일</a></li>
						<li><a href="#">원석에 의한</a></li>
						<li><a href="#">원석의 프로젝트</a></li>						
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="./">Default <span
						class="sr-only">(current)</span></a></li>
				
				<c:if test="${empty sessionScope.LOGIN_INFO }">
					<li><a href="<c:url value='/login/login.jsp' />">로그인 </a></li>
					<li><a href="<c:url value='/join/step1' />">회원가입</a></li>
				</c:if>
				
				<c:if test="${not empty sessionScope.LOGIN_INFO}">
					<li><a href="#">${sessionScope.LOGIN_INFO.mem_name}반갑습니다</a></li>
					<li><a href="${pageContext.request.contextPath}/login/logout.jsp">로그아웃</a></li>
				</c:if>

				<li><a href="../navbar-fixed-top/">Fixed top</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</nav>







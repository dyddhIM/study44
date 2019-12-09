<%@page import="com.study.member.dao.MemberDaoOracle"%>
<%@page import="com.study.member.dao.IMemberDao"%>
<%@page import="com.study.common.util.CookieBox"%>
<%@page import="com.study.member.vo.MemberVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/ loginCheck.jsp </title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	String msg = "";
	if(id == null || pw == null || id.isEmpty() || pw.isEmpty()){
		// login.jsp 에서 오지 않았다는 뜻 
		msg = URLEncoder.encode("id, pw는 필수입력입니다.", "utf-8" );
		// login.jsp?msg=xxx&pa2=val2&pa3=val3
		response.sendRedirect("login.jsp?msg=" + msg);
		return;
	}
	
	// Map<String, MemberVO> users = (Map)application.getAttribute("users");
	IMemberDao memberDao = new MemberDaoOracle();
	MemberVO vo = memberDao.selectMember(id);
	
	if(vo != null  ){		
		if(vo.getMem_pass().equals(pw)){
			// id_remember가 "Y" 이면 
			//  현재 넘겨진 id 를 쿠키에 저장 합니다. 쿠키이름은 s_id 로 저장, 기간은 일주일 
			// 그런데... 로그인에 성공했는데.. id_remember 가 비어있다는 것은??
			// 기존에 저장된 쿠키를 제거해 달라는 뜻이 됩니다.		
			if("Y".equals(request.getParameter("id_remember"))){
				Cookie cookie = CookieBox.createCookie("s_id", id, "/", 60 * 60 * 24 * 7);
				response.addCookie(cookie);
			}else{
				Cookie cookie = CookieBox.createCookie("s_id","", "/", 0 );
				response.addCookie(cookie);
			}
			// 로그인에 성공 했으므로 세션에 현재 MemberVO 를 저장 
			session.setAttribute("LOGIN_INFO", vo);			
			
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}else{
			msg = URLEncoder.encode("로그인에 실패했습니다.","utf-8");
			response.sendRedirect(request.getContextPath() + "/login/login.jsp?msg=" + msg);
		}
	}else{
		msg = URLEncoder.encode("로그인에 실패했습니다.","utf-8");
		response.sendRedirect(request.getContextPath() + "/login/login.jsp?msg=" + msg);
	}
	
%>


</body>
</html>
package com.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest  req = (HttpServletRequest)request;
		// true 면 세션이 존재하지 않는 경우 신규 세션생성
		HttpSession session = req.getSession(false); 
		boolean login = false;
		if(session != null) {
			if(session.getAttribute("LOGIN_INFO") != null) {
				login = true;
			}	
		}		
		if(login) {
			// 로그인 상태 
			chain.doFilter(request, response); 
		}else {
			// 비 로그인 상태
			// 리다이렉트 (실제 경로이동은 브라우저(클라이언트) req.getContextPath() 필요 
//			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login/login.jsp");
			// 포워드/ 인클루드 내부적으로 이동 (클라이어트는 모름) 
			RequestDispatcher rd = req.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);			
		}
	}

	
	
}

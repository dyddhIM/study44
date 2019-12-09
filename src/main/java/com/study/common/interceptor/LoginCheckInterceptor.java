package com.study.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403, 접근 금지.
			return false;
		}
		if (session.getAttribute("LOGIN_INFO") == null) {
			logger.debug("세션이 존재하지 않음 {}", request.getRequestURI());
			//  ajax, 일반 요청 구분 
			//  "X-Requested-With" 헤더 여부 및 값"XMLHttpRequest"을 확인하고
			String ajax = request.getHeader("X-Requested-With");
			if("XMLHttpRequest".equals(ajax)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401, 권한없음 
				return false;
			}else {
				response.sendRedirect(request.getContextPath() + "/login/login.jsp");
				return false;
			}
		}
		return true;
	}
}

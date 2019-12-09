package com.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

// Tomcat 9.X  : java 8 컴파일 interface 내부적으로 코딩이 가능, default 기능 
// Tomcat 8.X  : java 7 컴파일 interface 모든 메서드 구현 
// Tomcat 9.X 사용중이므로 모든 메서드구현 안해도 됩니다. (init , destroy 가 default 로 구현 )

//@WebFilter( urlPatterns = "/*" 
//           , initParams = { @WebInitParam(name = "encoding" , value = "utf-8")}  
//           )

public class CharacterEncodingFilter implements Filter {
	private String encoding; 
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// System.out.printf("encoding=%s\n", encoding);
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
		encoding = filterConfig.getInitParameter("encoding");
		if(encoding == null) {
			encoding = "utf-8";
		}
	}
	

}

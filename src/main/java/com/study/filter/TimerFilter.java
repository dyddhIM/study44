package com.study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TimerFilter implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터가 생성될때 딱 한번 
		// 공통으로 사용할 자원의 초기화 작업 
	}

	@Override
	public void destroy() {
		// 필터가 was에서 제거될때
		// 공통으로 사용한 자원을 닫아야 한다면.. 
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리 부분 
		long startTime = System.currentTimeMillis();
		HttpServletRequest req = (HttpServletRequest)request; 
		String uri = req.getRequestURI();
		String ip  = req.getRemoteAddr();		
		
		chain.doFilter(request, response); // 다음 필터/요청한 서블릿(또는 jsp) 실행
		// 후처리 부분 
		
		System.out.printf("IP = %16s URI = %s, ExecTime = %d \n",
				            ip,uri,(System.currentTimeMillis() - startTime ));
		
	}

}








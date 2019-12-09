package com.study.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpCheckFilter implements Filter {
	
	// 본인 : 192.168.10.98 , 0:0:0:0:0:0:0:1, 127.0.0.1	
	// 수민 : 192.168.10.37
	// 용권 : 192.168.10.81
	// 지수 : 192.168.10.13
	private Map<String, String> accessMap = null; // ACL (Access Control List)
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		accessMap = new HashMap<String, String>();
		accessMap.put("192.168.10.98", "A");
		accessMap.put("0:0:0:0:0:0:0:1", "A");
		accessMap.put("127.0.0.1", "A");
		accessMap.put("192.168.10.37", "A");
		accessMap.put("192.168.10.81", "D");
	}
	
	@Override
	public void destroy() {
		accessMap = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String ip = request.getRemoteAddr();
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpServletRequest  req  = (HttpServletRequest)request;
		if(accessMap.containsKey(ip)) {
			String auth = accessMap.get(ip); 
			if( auth.equals("A")) {
				// 접근허용
				chain.doFilter(request, response);
			}else {
				// resp.sendError(resp.SC_FORBIDDEN);  // 403
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.print("<html><head><title>접근오류</title></head>");
				out.print("<body><h2>접근하신 IP " + ip + "는 접근 금지입니다.</h2>");
				out.print(" <div>문의사항은 042-123-1234로 연락주세요</div> ");
				out.print("</body></html>");
			}
		}else {
			// 등록되지 않은 IP login 으로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/login/login.jsp");
		}
		
		// chain.doFilter(request, response); 중복처리가 되면 안되요  
	}

}









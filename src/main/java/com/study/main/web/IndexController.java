package com.study.main.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/* 첫 메인 페이지  */
// 스프링이 지향하는바가 간결하고, 종속적이지 않는 방향으로

@Controller
@RequestMapping(value = {"/index.do" , "/mypage"})
public class IndexController {
	
	// "/index.do"
	@RequestMapping()
	public ModelAndView index() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg", "게티스버그 연설"); // 속성에 저장 
		mav.setViewName("index"); // /WEB-INF/views/index.jsp 
		return mav;		
	}
	
	// @RequestMapping에 기술한 정보에 최대한 맞는 메서드로 진입 
	// /mypage/info.do?key=34
	
	// /mypage/info.do
	@RequestMapping(value = "/info.do")
	public String info1(Map<String, Object> map) throws Exception {
		// Map<String, Object> map2 = new HashMap<String, Object>();  // 그냥 맵 
		map.put("msg2", "회원님의 정보를 알려드려요~~~~");
		return "index2";  // WEB-INF/views/index2.jsp
	}
	
	// /mypage/info.do?id=abc 
	@RequestMapping(value = "/info.do", params = {"id"})
	public String info2(ModelMap model) {
		model.addAttribute("msg3", "MVC 컨트롤러 쉬어요..");
		return "index3";  // WEB-INF/views/index3.jsp
	}
	
	
	
}

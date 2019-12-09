package com.study.member.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.code.dao.CommonCodeDaoOracle;
import com.study.code.dao.ICommonCodeDao;
import com.study.code.vo.CommonCodeVO;
import com.study.member.vo.MemberVO;

@Controller
public class FormTagTestController {
	
	private ICommonCodeDao codeDao = new CommonCodeDaoOracle() ;
	
	@RequestMapping("/test")
	public String test(Model model) throws Exception{		
		MemberVO vo = new MemberVO();
		vo.setMem_id("milkis");
		vo.setMem_name("밀키스");
		vo.setMem_job("JB03"); // "공무원"에 해당하는 코드값
		
		String[] a = {"JB02","JB04","JB05"};
		vo.setMem_jobs(a);
		
		List<CommonCodeVO> jobList = codeDao.selectCodeByType("JB00");
		
		model.addAttribute("member", vo);
		model.addAttribute("jobList", jobList);		
		
		return "test"; // /WEB-INF/views/test.jsp
	}
}

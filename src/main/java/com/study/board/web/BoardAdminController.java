package com.study.board.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.board.dao.IBoardDao;

@Controller
public class BoardAdminController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBoardDao boardDao;
	
	// 1. ajax 통신
	// 2. 검색폼으로 들어오게끔 
	// 3. 단순하게 해당글 삭제
	@RequestMapping("/admin/board/checkedDelete")
	public String checkedDelete(int[] bo_nos) throws Exception {
		
		logger.debug("bo_nos={}", bo_nos);
		int cnt = boardDao.deleteBoardChecked(bo_nos);
		logger.debug("삭제건수 = {}", cnt);
		return "redirect:/board/boardList.do";
	}
}




package com.study.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.board.vo.BoardVO;
import com.study.member.vo.MemberVO;
import com.study.reply.dao.IReplyDao;
import com.study.reply.dao.ReplyDaoOracle;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

// @Controller
@RestController
public class ReplyController {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	IReplyDao replyDao = new ReplyDaoOracle() ;
	
	// @ResponseBody 리턴하는것을 그대로 응답의 결과로 만들어 주세요
	// 메서드기술, 반환타입 앞에 기술 
	// String : 그냥 text 
	// 자바객체인 경우 : 라이브러리중에 변환라이브러리를 검색(json, xml,..)해서 
	// 해당 라이브러리를 사용해서 내보 
	// @ResponseBody 
	@RequestMapping(path = "/reply/replyRegist")
	public Map<String, Object> replyRegist (ReplyVO reply
			                                , HttpServletRequest req
			                                , HttpSession session) throws Exception  {
		Map<String, Object> map = new HashMap<String, Object>();
		reply.setRe_ip(req.getRemoteAddr()); 
		MemberVO login = (MemberVO)session.getAttribute("LOGIN_INFO");		
		reply.setRe_mem_id(login.getMem_id());
		
		logger.debug("reply {}", reply);
		int cnt = replyDao.insertReply(reply);
		if(cnt > 0) {
			map.put("result", true);
			map.put("msg", "정상적으로 댓글이 등록되었습니다.");
		}
		return map;
	} // replyRegist
	
	
	/* 댓글 목록 
	 * 상세보기가 로드되고나서
	 * @ResponseBody 를 사용하면 뷰(jsp)로 안가기 때문에 ModelMap을 메서드 파라미터에 기술하지 마세요 
	 */
	// @ResponseBody
	@RequestMapping(value = "/reply/replyList" , method = RequestMethod.GET)
	public Map<String, Object> replyList(ReplySearchVO searchVO) throws Exception {
		logger.debug("searchVO : {}", searchVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		int rowCount = replyDao.selectReplyCount(searchVO);		
		searchVO.setTotalRowCount(rowCount);
		searchVO.pageSetting();
		List<ReplyVO> list = replyDao.selectReplyList(searchVO);
		map.put("result", true );
		map.put("msg", "목록조회 성공");
		map.put("data", list);
		map.put("count", list.size() );
		map.put("totalRow", searchVO.getTotalRowCount() );
		return map;
	}
	
	@RequestMapping(path = "/reply/replyDelete")
	// @ResponseBody
	public Map<String, Object> replyDelete (ReplyVO reply
			                                , HttpSession session) throws Exception  {
		Map<String, Object> map = new HashMap<String, Object>();
		MemberVO login = (MemberVO)session.getAttribute("LOGIN_INFO");		
		reply.setRe_mem_id(login.getMem_id());
		
		logger.debug("reply {}", reply);
		int cnt = replyDao.deleteReply(reply);
		if(cnt > 0) {
			map.put("result", true);
			map.put("msg", "댓글이 삭제되었습니다.");
		}else {
			map.put("result", false);
			map.put("msg", "삭제실패, 해당 글이 조회되지 않았습니다.");
		}
		return map;
	} // replyDelete
	
	
	
	
	
} // ReplyController





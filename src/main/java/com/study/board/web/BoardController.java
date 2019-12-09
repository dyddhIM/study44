package com.study.board.web;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.groups.Default;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.study.attach.vo.AttachVO;
import com.study.board.service.IBoardService;
import com.study.board.vo.BoardSearchVO;
import com.study.board.vo.BoardVO;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CommonCodeVO;
import com.study.common.exception.BizNotFoundException;
import com.study.common.exception.BizNotMatchedException;
import com.study.common.util.StudyAttachUtils;
import com.study.common.valid.ModifyType;
import com.study.common.valid.RegistType;
import com.study.common.vo.ResultMessageVO;

@Controller
public class BoardController {
	
	@Autowired
	private ICommonCodeService codeService;
	
	@Autowired
	private IBoardService boardService;
	
	@Autowired
	private StudyAttachUtils attachUtils;
	
		
	private final Logger logger = LoggerFactory.getLogger(getClass()) ;
	
	// 공통(반복)적으로 사용되는 모델객체를 저장
	// 해당 컨트롤러의 모든 요청전에 호출되어 저장되므로 
	// 자주 사용되는 객체에만 사용하세요   
	@ModelAttribute("bcList")
	public List<CommonCodeVO> getBoardClassList() throws Exception {
		List<CommonCodeVO> obj = codeService.selectCodeByType("BC00");	
		return obj;
	}
	
	@RequestMapping("/board/boardList.do")
	public String boardList(HttpServletRequest req) throws Exception{
		String view = "board/boardList";
		
		BoardSearchVO searchVO = new BoardSearchVO();	
		BeanUtils.populate(searchVO, req.getParameterMap());
		
		List<BoardVO> list = boardService.selectBoardList(searchVO);
		req.setAttribute("boardList", list);
		req.setAttribute("searchVO", searchVO);
		
		return  view;
	}
	
	@RequestMapping(value = {"/board/boardForm.do","/board/boardRegist.do"})
	public String boardForm(@ModelAttribute("board") BoardVO board) throws Exception {
		String view = "board/boardForm";
		
		return view;
	}

	// 커맨드객체(VO)는 자동으로 파라미터 설정, 모델(속성)에 저장 됩니다.
	// 속성에 저장될때 이름은 클래스의 첫글자 소문로 변경되어 저장 
	// BoardVO "boardVO" 로 저장 , 이름을 변경하려면 @ModelAttribute 사용 
	@RequestMapping(value = "/board/boardRegist.do", method = RequestMethod.POST)
	public String boardRegist( HttpServletRequest req
              , ModelMap model
              , @RequestParam(name="bo_files", required=false) MultipartFile[] bo_files
              , @ModelAttribute("board") @Validated(value = {RegistType.class, Default.class }) BoardVO board
              , BindingResult errors
           ) throws Exception {
		String view = "common/message";		
		// 1. Spring Validator 구현체로 검증 
		// new BoardRegistValidator().validate(board, errors);		
		// 2. @Valid 를 통해 자동으로 검증
		if(errors.hasErrors()) {			
			return "board/boardForm";
		}		
		
		if(bo_files != null){
			List<AttachVO> attaches = attachUtils.getAttachListByMultiparts(bo_files,
			"BOARD", "board");
			// 첨부파일 정보를 board에 설정 
			board.setAttaches(attaches);
		}
		
	    // 접근한 사용자 ip 를 vo에 설정 
		board.setBo_ip(req.getRemoteAddr());		
		boardService.insertBoard(board);
		
		// 메시지가 필요없이 바로 상세보기로 이동하고자 할 경우 
		// forward: 또는 redirect: 을 사용하시면 됩니다.
		// view = "forward:/board/boardView.do?bo_no=" + board.getBo_no();
		
		ResultMessageVO messageVO = new ResultMessageVO();
		messageVO.setResult(true)
		         .setTitle("글 등록 성공")
		         .setMessage("해당 글을 등록완료했습니다.")
		         .setUrlTitle("상세보기")
		         .setUrl("/board/boardView.do?bo_no=" + board.getBo_no());

		model.addAttribute("resultMessage", messageVO);
		return view;
	}

	
	@RequestMapping(value = "/board/boardView.do", params = "bo_no")
	public String boardView(HttpServletRequest req, int bo_no) throws Exception {
		String view = "board/boardView";
		
		BoardVO vo = boardService.selectBoard(bo_no, true);
		req.setAttribute("board", vo);

		return view;
	}


	@RequestMapping(value = "/board/boardEdit.do", params = "bo_no")
	public String boardEdit(ModelMap model, @RequestParam("bo_no") int bo_no) throws Exception {
		String view = "board/boardEdit";

		BoardVO vo = boardService.selectBoard(bo_no, false);
		model.addAttribute("board", vo);

		return view;
	}
	
	@RequestMapping(value = "board/boardModify.do", method = RequestMethod.POST)
	public String boardModify(ModelMap model, 
       @ModelAttribute("board") @Validated(value = {ModifyType.class, Default.class}) BoardVO board
	                          , BindingResult errors) throws Exception {
		
		String view = "common/message";
		
		if(errors.hasErrors()) {
			return "board/boardEdit";
		}
		
		int succ = boardService.updateBoard(board);
		ResultMessageVO messageVO = new ResultMessageVO();
		if (succ > 0) {
			messageVO.setResult(true)
			         .setTitle("수정에 성공")
			         .setMessage("수정에 성공하였습니다")
			         .setUrl("/board/boardList.do")
					 .setUrlTitle("목록으로");
		} else {
			messageVO.setResult(false)
			         .setTitle("수정에 실패")
			         .setMessage("수정에 실패하였습니다")
			         .setUrl("/board/boardList.do")
					 .setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessage", messageVO);

		return view;
	}
		
	@RequestMapping(value = "board/boardDelete.do")
	public String boardDelete(ModelMap model, 
			      BoardVO board ) throws Exception {
		String view = "common/message";		
		ResultMessageVO messageVO = new ResultMessageVO();		
		try {
			boardService.deleteBoard(board);
			messageVO.setResult(true)
			         .setTitle("삭제성공")
			         .setMessage("삭제에 성공하였습니다")
			         .setUrl("/board/boardList.do")
					.setUrlTitle("목록으로");
		} catch (BizNotFoundException  e) {
			e.printStackTrace();
			messageVO.setResult(false)
			         .setTitle("삭제실패")
			         .setMessage("해당글이 존재하지 않습니다.")
			         .setUrl("/board/boardList.do")
					.setUrlTitle("목록으로");
		} catch (BizNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false)
			         .setTitle("삭제실패")
			         .setMessage("비밀번호가 일치하지 않습니다.")
			         .setUrl("/board/boardList.do")
					 .setUrlTitle("목록으로");
		}
		model.addAttribute("resultMessage", messageVO);
		return view;
	}
	
	@RequestMapping("/board/upload.do")
	public void name(HttpServletResponse resp, HttpServletRequest req) throws Exception {
		resp.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = resp.getWriter();
		out.println("<pre>");
		out.println("encType : " + req.getContentType());
		out.println("method : " + req.getMethod());
		out.println("bo_title : " + req.getParameter("bo_title"));
		out.println("bo_file : " + req.getParameter("bo_file"));
		Part p = req.getPart("bo_file");
		out.println("파일명 : " +  p.getSubmittedFileName() );
		out.println("사이즈 : " + p.getSize());		
		
		out.println("</pre>");
	}
	
	

}

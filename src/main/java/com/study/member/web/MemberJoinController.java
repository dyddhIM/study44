package com.study.member.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.study.code.dao.CommonCodeDaoOracle;
import com.study.code.dao.ICommonCodeDao;
import com.study.code.vo.CommonCodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.member.dao.IMemberDao;
import com.study.member.dao.MemberDaoOracle;
import com.study.member.vo.MemberJoinVO;

@Controller
@SessionAttributes(names = "memberJoin")
public class MemberJoinController {

	final Logger logger = LoggerFactory.getLogger(getClass());
	private IMemberDao memberDao = new MemberDaoOracle();
	private ICommonCodeDao codeDao = new CommonCodeDaoOracle();

	// 공통(반복)적으로 사용되는 모델객체를 저장
	// 해당 컨트롤러의 모든 요청전에 호출되어 저장됩니다.
	// 만약 이미 모델에 동일한 이름이 있다면 스킵
	// 자주 사용되는 객체에만 사용하세요
	@ModelAttribute("memberJoin")
	public MemberJoinVO getInitJoinVO() {
		MemberJoinVO joinVO = new MemberJoinVO();
		return joinVO;
	}

	@ModelAttribute("jobList")
	public List<CommonCodeVO> getJobList() throws Exception {
		List<CommonCodeVO> list = codeDao.selectCodeByType("JB00");
		return list;
	}

	@ModelAttribute("hobbyList")
	public List<CommonCodeVO> getHobbyList() throws Exception {
		List<CommonCodeVO> list = codeDao.selectCodeByType("HB00");
		return list;

	}

	/**
	 * <b>가입화면 1단계</b><br>
	 * 회원가입 동의 화면
	 * <ul>
	 * <li>이용약관 동의
	 * <li>개인정보 수집동의
	 * <li>이메일
	 * </ul>
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/step1")
	public String joinGet() throws Exception {
		String view = "join/step1";
		return view;
	}

	/**
	 * <b>가입화면 2단계</b><br>
	 * 주요 필수정보 입력단계
	 * <p>
	 * 아이디, 비밀번호, 이름 등
	 * </p>
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/step2")
	public String joinPost(@ModelAttribute("memberJoin") MemberJoinVO joinVO) throws Exception {
		String view = "join/step2";
		logger.debug("step2	{}", joinVO);
		// 1단계의 정보가 없으면 1단계 화면으로 리턴
		if (!"Y".equals(joinVO.getAgree_yn()))
			return "join/step1";
		if (!"Y".equals(joinVO.getPrivacy_yn()))
			return "join/step1";
		if (StringUtils.isBlank(joinVO.getMem_mail()))
			return "join/step1";

		return view;
	}

	/**
	 * <b>가입화면 3단계</b><br>
	 * 사용자 필수 및 선택정보 입력단계
	 * <p>
	 * 필수 : 주민번호, 우편번호, 주소
	 * </p>
	 * <p>
	 * 선택 : HP, 직업, 취미 등
	 * </p>
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/step3")
	public String option(@ModelAttribute("memberJoin") MemberJoinVO joinVO) throws Exception {
		String view = "join/step3";
		logger.debug("step3	{}", joinVO);
		// 1단계의 정보가 없으면 1단계 화면으로 리턴
		if (!"Y".equals(joinVO.getAgree_yn()))
			return "join/step1";
		if (!"Y".equals(joinVO.getPrivacy_yn()))
			return "join/step1";
		if (StringUtils.isBlank(joinVO.getMem_mail()))
			return "join/step1";
		// 2단계의 필수 정보가 없으면 2단계 화면으로 리턴
		if (StringUtils.isBlank(joinVO.getMem_id()))
			return "join/step2";
		if (StringUtils.isBlank(joinVO.getMem_pass()))
			return "join/step2";
		if (StringUtils.isBlank(joinVO.getMem_name()))
			return "join/step2";
		return view;
	}

	/**
	 * <b>가입화면 최종 단계</b><br>
	 * 사용자가 입력한 정보의 결과를 보여준다.
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/regist")
	public String regist(@ModelAttribute("memberJoin") MemberJoinVO joinVO, Model model, SessionStatus sessionStatus)
			throws Exception {
		String view = "join/regist";
		logger.debug("step4	{}", joinVO);
		// 1단계의 정보가 없으면 1단계 화면으로 리턴
		if (!"Y".equals(joinVO.getAgree_yn()))
			return "join/step1";
		if (!"Y".equals(joinVO.getPrivacy_yn()))
			return "join/step1";
		if (StringUtils.isBlank(joinVO.getMem_mail()))
			return "join/step1";
		// 2단계의 필수 정보가 없으면 2단계 화면으로 리턴
		if (StringUtils.isBlank(joinVO.getMem_id()))
			return "join/step2";
		if (StringUtils.isBlank(joinVO.getMem_pass()))
			return "join/step2";
		if (StringUtils.isBlank(joinVO.getMem_name()))
			return "join/step2";
		// 3단계의 필수 정보가 없으면 3단계 화면으로 리턴
		if (StringUtils.isBlank(joinVO.getMem_regno1()))
			return "join/step3";
		if (StringUtils.isBlank(joinVO.getMem_regno2()))
			return "join/step3";
		if (StringUtils.isBlank(joinVO.getMem_zip()))
			return "join/step3";
		if (StringUtils.isBlank(joinVO.getMem_add1()))
			return "join/step3";
		if (StringUtils.isBlank(joinVO.getMem_add2()))
			return "join/step3";
		ResultMessageVO messageVO = new ResultMessageVO();
		try {
			memberDao.insertMember(joinVO);
			messageVO.setResult(true).setTitle("회원가입	성공").setMessage("회원가입을	완료되었습니다.	즐거운	하루	되세요")
					.setUrlTitle("로그인").setUrl("/login/login");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			messageVO.setResult(false).setTitle("회원가입	실패")
					.setMessage("회원가입에	오류가	발생했습니다. 지속적으로	실패를	하시면	관리자에게 문의하시기 바랍니다.").setUrlTitle("문의하기")
					.setUrl("/question/regist");
		}
		sessionStatus.setComplete(); // 세션 자원 정리
		model.addAttribute("resultMessage", messageVO);
		return view;
	}

	/**
	 * <b>가입취소</b><br>
	 * 가입 중 입력한 정보 제거 후 메인화면으로 이동
	 * 
	 * @return String 뷰이름
	 * @throws Exception
	 */
	@RequestMapping(value = "/join/cancel")
	public String cancel(SessionStatus sessionStatus) throws Exception {
		String view = "redirect:/index.jsp";
		sessionStatus.setComplete(); // 세션 자원 정리
		return view;
	}

} // class









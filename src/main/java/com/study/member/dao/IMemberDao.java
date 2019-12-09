package com.study.member.dao;

import java.util.List;

import com.study.member.vo.MemberVO;

public interface IMemberDao {
	
	// selectXxx , findByXxx , getXxx	
	/**
	 * <b>회원목록 전체</b>를 조회한다. <br>
	 * @return List<MemberVO> 
	 * @throws Exception
	 */
	public List<MemberVO> selectMemberList() throws Exception;

	/**
	 * <b>회원상세정보</b>를 조회한다. <br>
	 * @param   
	 * @return MemberVO
	 * @throws Exception
	 */
	public MemberVO selectMember(String mem_id) throws Exception;
	
	/**
	 * 입력된 회원정보를 저장한다. 
	 * @param MemberVO
	 * @return
	 * @throws Exception
	 */
	public int insertMember(MemberVO member) throws Exception;
	
	/**
	 * 입력된 회원정보를 수정한다. 
	 * @param MemberVO
	 * @return
	 * @throws Exception
	 */
	public int updateMember(MemberVO member) throws Exception;
	
	/**
	 * 해당 회원의 탈퇴여부를 "Y" 로 변경 <br>
	 * 회원아이디, 비밀번호가 매칭되어야 한다.  
	 * @param MemberVO
	 * @return
	 * @throws Exception
	 */
	public int deleteMember(MemberVO member) throws Exception;
	
}









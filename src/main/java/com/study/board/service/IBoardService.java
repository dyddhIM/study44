package com.study.board.service;

import java.util.List;

import com.study.board.vo.BoardSearchVO;
import com.study.board.vo.BoardVO;

public interface IBoardService {
	
	/**
	* <b>조건에 맞는 게시판목록을 조회한다. <br>
	* @param searchVO 게시판 조회 VO
	* @return List &lt;BoardVO&gt;
	* @throws Exception
	*/
	public List<BoardVO> selectBoardList(BoardSearchVO searchVO) throws Exception;
	/**
	* <b>게시판 상세정보</b>를 조회한다. <br>
	*
	* @param bo_no 글번호
	* @param incrementHit 조회수 증가여부
	* @return BoardVO
	* @throws Exception
	*/
	public BoardVO selectBoard(int bo_no, boolean incrementHit) throws Exception;
	
	/**
	* 입력된 내용을 저장한다
	* @param board 게시판VO
	* @return 영향받은 건수
	* @throws Exception
	*/
	public int insertBoard(BoardVO board) throws Exception;
	/**
	* 게시판 수정
	* @param board 게시판VO
	* @return 영향받은 건수
	* @throws Exception
	*/
	public int updateBoard(BoardVO board) throws Exception;
	
	/**
	* 게시판 삭제 <br>
	* 해당 게시물의 삭제여부를 "Y"로 변경<br>
	* 회원아이디, 비밀번호가 매칭되어야 한다.
	* @param board
	* @return 영향받은 건수
	* @throws Exception
	*/
	public int deleteBoard(BoardVO board) throws Exception;
}

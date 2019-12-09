package com.study.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.study.board.vo.BoardSearchVO;
import com.study.board.vo.BoardVO;

@Mapper
public interface IBoardDao {
	
	/**
	 * 게시판 조회건수를 리턴한다
	 * @param searchVO TODO
	 * @return
	 * @throws Exception
	 */
	
	int selectBoardCount(BoardSearchVO searchVO) throws Exception;
		
	/**
	 * <b>게시판목록 전체</b>를 조회한다. <br>
	 * @param searchVO TODO
	 * 
	 * @return List<BoardVO>
	 * @throws Exception
	 */

	public List<BoardVO> selectBoardList(BoardSearchVO searchVO) throws Exception;

	/**
	 * <b>게시판 상세정보</b>를 조회한다. <br>
	 * 
	 * @param
	 * @return BoardVO
	 * @throws Exception
	 */
	public BoardVO selectBoard(int bo_no) throws Exception;

	/**
	 * 입력된 내용을 저장한다
	 * 
	 * @param BoardVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(BoardVO board) throws Exception;

	/**
	 * 입력된 내용을 수정한다
	 * 
	 * @param BoardVO
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(BoardVO board) throws Exception;

	/**
	 * 해당 게시물의 삭제여부를 "Y"로 변경<br>
	 * 회원아이디, 비밀번호가 매칭되어야 한다.
	 * 
	 * @param BoardVO
	 * @return
	 * @throws Exception
	 */
	public int deleteBoard(BoardVO board) throws Exception;

	/**
	 * 조회수 증가 
	 * @throws Exception
	 */
	public void increaseBoardCount(int bo_no) throws Exception;
	
	/**
	 * 선택글 삭제  
	 * @throws Exception
	 */
	public int deleteBoardChecked(int[] bo_nos) throws Exception;
 
	
}



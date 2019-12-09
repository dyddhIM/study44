package com.study.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.attach.dao.IAttachDao;
import com.study.attach.vo.AttachVO;
import com.study.board.dao.IBoardDao;
import com.study.board.vo.BoardSearchVO;
import com.study.board.vo.BoardVO;
import com.study.common.exception.BizException;
import com.study.common.exception.BizNotFoundException;
import com.study.common.exception.BizNotMatchedException;

@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired
	private IBoardDao boardDao;	
	@Inject
	private IAttachDao attachDao;
	
	@Override
	public List<BoardVO> selectBoardList(BoardSearchVO searchVO) throws Exception {
		int rowCount = boardDao.selectBoardCount(searchVO);
		
		searchVO.setTotalRowCount(rowCount);
		searchVO.pageSetting();	
		
		List<BoardVO> list = boardDao.selectBoardList(searchVO);
		return list;
	}

	@Override
	public BoardVO selectBoard(int bo_no, boolean incrementHit) throws Exception {
		BoardVO vo = boardDao.selectBoard(bo_no);		
		// 창효아빠 : vo 가 널이 아니면 첨부파을 가져와서 board에 담기 
		if(vo != null ) {
			vo.setAttaches(attachDao.selectAttachByParentNo(bo_no));
		}
		
		if(incrementHit ){
			boardDao.increaseBoardCount(bo_no);
		}
		return vo;
	}

	@Override
	public int insertBoard(BoardVO board) throws Exception {
		int cnt = boardDao.insertBoard(board);
		// 대현아빠 : 첨부파일이 존재하는 경우 첨부파일 등록 , parent_no 설정 필요  
		List<AttachVO> atchList = board.getAttaches();
		if(atchList != null) {
			for(int i = 0; i < atchList.size(); i++) {
				AttachVO vo =  atchList.get(i);
				vo.setAtch_parent_no(board.getBo_no());
				// 일부러 3번째 파일에서 예외를 던질게요..
				if(i > 1) throw new Exception("첨부파일이 너무 많아~~~ ");
				attachDao.insertAttach(vo);
			}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO board) throws Exception {		
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(BoardVO board) throws Exception {
		
		BoardVO vo = boardDao.selectBoard(board.getBo_no());
		if(vo == null) {
			throw new BizNotFoundException("해당 글이 존재하지 않습니다.");
		}
		if( ! vo.getBo_pass().equals(board.getBo_pass())) {
			throw new BizNotMatchedException("비밀번호가 맞지않습니다.");
		}
		
		return boardDao.deleteBoard(board);
	}

}











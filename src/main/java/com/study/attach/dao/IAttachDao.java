package com.study.attach.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.attach.vo.AttachVO;

@Mapper
public interface IAttachDao {
	
	/** 첨부파일 등록 */
	public int insertAttach(AttachVO attach) throws Exception;

	/** 첨부파일 삭제 */
	public int deleteAttach(int atch_no) throws Exception;

	/** 첨부파일 삭제(여러개) */
	public int deleteAttaches(int[] atch_nos) throws Exception;

	/** 첨부파일 상세 조회 */
	public AttachVO selectAttach(int atch_no) throws Exception;

	/** 부모번호에 따른 목록 조회 */
	public List<AttachVO> selectAttachByParentNo(int parent_no) throws Exception;

	/** 다운로드 횟수 증가 */
	public int increaseDownloadCount(int atch_no) throws Exception;
	
}

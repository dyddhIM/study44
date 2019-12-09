package com.study.reply.dao;

import java.util.List;

import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

public interface IReplyDao {
	
	int selectReplyCount(ReplySearchVO searchVO) throws Exception;
	
	List<ReplyVO> selectReplyList(ReplySearchVO searchVO) throws Exception;
	
	ReplyVO selectReply(int re_no) throws Exception;
	
	int insertReply(ReplyVO reply) throws Exception;
	int updateReply(ReplyVO reply) throws Exception;
	int deleteReply(ReplyVO reply) throws Exception;
	
}

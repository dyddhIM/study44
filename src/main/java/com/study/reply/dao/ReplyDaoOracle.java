package com.study.reply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.board.vo.BoardVO;
import com.study.common.sql.CommonSQL;
import com.study.reply.vo.ReplySearchVO;
import com.study.reply.vo.ReplyVO;

public class ReplyDaoOracle implements IReplyDao {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public int selectReplyCount(ReplySearchVO searchVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");			
			sb.append("SELECT count(*) as cnt  ");
			sb.append("  FROM reply            ");
			sb.append(" WHERE re_category  = ? ");
			sb.append("   AND re_parent_no = ? ");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, searchVO.getRe_category());
			pstmt.setInt(2,    searchVO.getRe_parent_no());
			
			logger.debug("SQL: {}", sb.toString());		
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			return cnt;
		} catch (SQLException ex) {
			logger.error(ex.getMessage() , ex);
			throw ex;
		} finally {
			if (rs != null)     rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn != null)   conn.close();
		}
	}

	@Override
	public List<ReplyVO> selectReplyList(ReplySearchVO searchVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			List<ReplyVO> list = new ArrayList<ReplyVO>();
			sb.append( CommonSQL.PAGING_PREFIX );			
			sb.append("SELECT re_no,         ");
		    sb.append("       re_category,   ");
		    sb.append("       re_parent_no,  ");
		    sb.append("       re_mem_id,     ");		    
		    sb.append("       mem_name AS re_mem_name , ");
		    sb.append("       re_content,    ");
		    sb.append("       re_ip,         ");
		    sb.append("       TO_CHAR(re_reg_date,'YY/MM/DD HH24:MI') AS re_reg_date,  ");
		    sb.append("       TO_CHAR(re_mod_date,'YY/MM/DD HH24:MI') AS re_mod_date   ");
			sb.append("  FROM reply,  member ");
			sb.append(" WHERE re_mem_id    = mem_id ");
			sb.append("   AND re_category  = ?      ");
			sb.append("   AND re_parent_no = ?      ");
			sb.append(" ORDER BY re_reg_date ASC    ");
			sb.append(CommonSQL.PAGING_SUFFIX);
			
			logger.debug("SQL: {}", sb.toString());
			
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, searchVO.getRe_category());
			pstmt.setInt(idx++, searchVO.getRe_parent_no());
			pstmt.setInt(idx++, searchVO.getEndRow());
			pstmt.setInt(idx++, searchVO.getStartRow());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReplyVO vo = new ReplyVO();
				vo.setRe_no(rs.getInt("re_no"));
				vo.setRe_category(rs.getString("re_category"));
				vo.setRe_parent_no(rs.getInt("re_parent_no"));
				vo.setRe_mem_id(rs.getString("re_mem_id"));
				vo.setRe_mem_name(rs.getString("re_mem_name"));
				vo.setRe_content(rs.getString("re_content"));
				vo.setRe_ip(rs.getString("re_ip"));
				vo.setRe_reg_date(rs.getString("re_reg_date"));
				vo.setRe_mod_date(rs.getString("re_mod_date"));				
				
				list.add(vo);
			}
			return list;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)     rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn != null)   conn.close();
		}
	}

	@Override
	public ReplyVO selectReply(int re_no) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReply(ReplyVO reply) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("INSERT INTO reply                              ");
		    sb.append("   ( re_no,      re_category,   re_parent_no,  ");
		    sb.append("     re_mem_id,  re_content,    re_ip,         ");
		    sb.append("     re_reg_date                ");
		    sb.append("    ) VALUES                    ");
		    sb.append("   ( seq_reply.nextval , ?,  ?, ");
		    sb.append("     ?, ?, ?,    ");
		    sb.append("     sysdate     ");
		    sb.append("    )            ");
			   
		    pstmt = conn.prepareStatement(sb.toString());
		    int idx = 1;
		    pstmt.setString(idx++ , reply.getRe_category() );
		    pstmt.setInt(idx++ ,    reply.getRe_parent_no());
		    pstmt.setString(idx++ , reply.getRe_mem_id());
		    pstmt.setString(idx++ , reply.getRe_content() );
		    pstmt.setString(idx++ , reply.getRe_ip());
		    
		    int cnt = pstmt.executeUpdate();		
			return cnt;
		}catch(SQLException ex) {
			throw ex;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
	}

	@Override
	public int updateReply(ReplyVO reply) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReply(ReplyVO reply) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("DELETE FROM reply    ");
		    sb.append(" WHERE re_no     = ? ");
		    sb.append("   AND re_mem_id = ? ");			   
		    pstmt = conn.prepareStatement(sb.toString());
		    
		    int idx = 1;
		    pstmt.setInt(idx++ ,    reply.getRe_no());
		    pstmt.setString(idx++ , reply.getRe_mem_id());
		    
		    int cnt = pstmt.executeUpdate();		
			return cnt;
		}catch(SQLException ex) {
			throw ex;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
	}

}

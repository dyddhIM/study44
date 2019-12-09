package com.study.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.member.vo.MemberVO;

public class MemberDaoOracle implements IMemberDao{

	@Override
	public List<MemberVO> selectMemberList() throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			List<MemberVO> list = new ArrayList<MemberVO>();
			sb.append("SELECT mem_id,       ");                                 
			sb.append("       mem_pass,     ");                                 
			sb.append("       mem_name,     ");                                 
			sb.append("       mem_regno1,   ");                                 
			sb.append("       mem_regno2,   ");                                 
			sb.append("       TO_CHAR(mem_bir, 'YYYY.MM.DD') as mem_bir, ");    
			sb.append("       mem_zip,      ");                                 
			sb.append("       mem_add1,     ");                                 
			sb.append("       mem_add2,     ");                                 
			sb.append("       mem_hp,       ");                                 
			sb.append("       mem_mail,     ");                                 
			sb.append("       mem_job,      ");			                        
			sb.append("       mem_like,     ");			                        
			sb.append("       mem_mileage,  ");                                 
			sb.append("       mem_delete    ");                                 
			sb.append("  FROM member        ");                                 
			sb.append(" WHERE mem_delete = 'N'  ");                             
			sb.append("    OR mem_delete is null	");
			
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pass(rs.getString("mem_pass"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_regno1(rs.getString("mem_regno1"));
				vo.setMem_regno2(rs.getString("mem_regno2"));
				vo.setMem_bir(rs.getString("mem_bir"));
				vo.setMem_zip(rs.getString("mem_zip"));
				vo.setMem_add1(rs.getString("mem_add1"));
				vo.setMem_add2(rs.getString("mem_add2"));
				vo.setMem_hp(rs.getString("mem_hp"));
				vo.setMem_mail(rs.getString("mem_mail"));
				vo.setMem_job(rs.getString("mem_job"));
				vo.setMem_like(rs.getString("mem_like"));
				vo.setMem_mileage(rs.getInt("mem_mileage"));
				vo.setMem_delete(rs.getString("mem_delete"));				
				list.add(vo);
			}
			return list;
		}catch(SQLException ex) {
			throw ex;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("SELECT mem_id,       ");
			sb.append("       mem_pass,     ");
			sb.append("       mem_name,     ");
			sb.append("       mem_regno1,   ");
			sb.append("       mem_regno2,   ");
			sb.append("       TO_CHAR(mem_bir, 'YYYY-MM-DD') as mem_bir, ");
			sb.append("       mem_zip,      ");
			sb.append("       mem_add1,     ");
			sb.append("       mem_add2,     ");
			sb.append("       mem_hp,       ");
			sb.append("       mem_mail,     ");
			sb.append("       mem_job,      ");
			sb.append("       (select com_nm from com_code where mem_job = com_cd ) as mem_job_nm , "); 
			sb.append("       mem_like,     ");
			sb.append("       (select com_nm from com_code where mem_like = com_cd ) as mem_like_nm , "); 
			sb.append("       mem_mileage,  ");
			sb.append("       mem_delete    ");			
			sb.append("  FROM member        ");
			sb.append(" WHERE mem_id = ?    ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			MemberVO vo = null;
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMem_id(rs.getString("mem_id"));
				vo.setMem_pass(rs.getString("mem_pass"));
				vo.setMem_name(rs.getString("mem_name"));
				vo.setMem_regno1(rs.getString("mem_regno1"));
				vo.setMem_regno2(rs.getString("mem_regno2"));
				vo.setMem_bir(rs.getString("mem_bir"));
				vo.setMem_zip(rs.getString("mem_zip"));
				vo.setMem_add1(rs.getString("mem_add1"));
				vo.setMem_add2(rs.getString("mem_add2"));
				vo.setMem_hp(rs.getString("mem_hp"));
				vo.setMem_mail(rs.getString("mem_mail"));
				vo.setMem_job(rs.getString("mem_job"));
				vo.setMem_job_nm(rs.getString("mem_job_nm"));
				vo.setMem_like(rs.getString("mem_like"));
				vo.setMem_like_nm(rs.getString("mem_like_nm"));
				vo.setMem_mileage(rs.getInt("mem_mileage"));
				vo.setMem_delete(rs.getString("mem_delete"));
			}		
			return vo;
			
		}catch(SQLException ex) {
			throw ex;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
	}

	@Override
	public int insertMember(MemberVO member) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("INSERT INTO member (                         ");
			sb.append("	    mem_id,     mem_pass,     mem_name,    ");
			sb.append("	    mem_regno1, mem_regno2,   mem_bir,     ");
			sb.append("	    mem_zip,    mem_add1,     mem_add2,    ");
			sb.append("	    mem_hp,     mem_mail,     mem_job,     ");
			sb.append("	    mem_like,   mem_mileage,  mem_delete   ");
			sb.append("	  ) VALUES (       " );
			sb.append("	    ?, ?, ?,       " );
			sb.append("	    ?, ?, ?,       " );
			sb.append("	    ?, ?, ?,       " );
			sb.append("	    ?, ?, ?,       " );
			sb.append("	    ?, 0, 'N'      " );
			sb.append("	   )            " );

			pstmt = conn.prepareStatement(sb.toString());  
			// 값 설정 
			int idx = 1;
			pstmt.setString(idx++, member.getMem_id()  );
			pstmt.setString(idx++, member.getMem_pass()  );
			pstmt.setString(idx++, member.getMem_name()  );
			pstmt.setString(idx++, member.getMem_regno1()  );
			pstmt.setString(idx++, member.getMem_regno2()  );
			pstmt.setString(idx++, member.getMem_bir()  );
			pstmt.setString(idx++, member.getMem_zip()  );
			pstmt.setString(idx++, member.getMem_add1()  );
			pstmt.setString(idx++, member.getMem_add2()  );
			pstmt.setString(idx++, member.getMem_hp()  );
			pstmt.setString(idx++, member.getMem_mail()  );
			pstmt.setString(idx++, member.getMem_job()  );
			pstmt.setString(idx++, member.getMem_like() );

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
	public int updateMember(MemberVO member) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("UPDATE member            ");
			sb.append("   SET mem_name   = ?    ");
			sb.append("     , mem_regno1 = ?    ");
			sb.append("     , mem_regno2 = ?    ");
			sb.append("     , mem_bir    = ?    ");
			sb.append("     , mem_zip    = ?    ");
			sb.append("     , mem_add1   = ?    ");
			sb.append("     , mem_add2   = ?    ");
			sb.append("     , mem_hp     = ?    ");
			sb.append("     , mem_mail   = ?    ");
			sb.append("     , mem_job    = ?    ");
			sb.append("     , mem_like   = ?    ");
			sb.append(" WHERE mem_id     = ?	");		

			pstmt = conn.prepareStatement(sb.toString());  
			// 값 설정 
			int idx = 1;
			pstmt.setString(idx++, member.getMem_name()  );
			pstmt.setString(idx++, member.getMem_regno1()  );
			pstmt.setString(idx++, member.getMem_regno2()  );
			pstmt.setString(idx++, member.getMem_bir()  );
			pstmt.setString(idx++, member.getMem_zip()  );
			pstmt.setString(idx++, member.getMem_add1()  );
			pstmt.setString(idx++, member.getMem_add2()  );
			pstmt.setString(idx++, member.getMem_hp()  );
			pstmt.setString(idx++, member.getMem_mail()  );
			pstmt.setString(idx++, member.getMem_job()  );
			pstmt.setString(idx++, member.getMem_like() );
			pstmt.setString(idx++, member.getMem_id()  );

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
	public int deleteMember(MemberVO member) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			
		
			return 0;
		}catch(SQLException ex) {
			throw ex;
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn  != null) conn.close();
		}
	}

	
}

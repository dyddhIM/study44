package com.study.code.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.study.code.vo.CommonCodeVO;

@Repository("codeOracle")
public class CommonCodeDaoOracle implements ICommonCodeDao {

	@Override
	public List<CommonCodeVO> selectCodeByType(String type) throws Exception {
		Connection         conn  = null;
		PreparedStatement  pstmt = null;
		ResultSet          rs    = null;
		StringBuffer       sb    = new StringBuffer();
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			List<CommonCodeVO> list = new ArrayList<CommonCodeVO>();
			
			sb.append("SELECT com_cd,     ");
			sb.append("       com_nm,     ");
			sb.append("       com_parent, ");
			sb.append("       com_ord,    ");
			sb.append("       com_del_yn  ");
			sb.append("  FROM com_code    ");
			sb.append(" WHERE com_del_yn = 'N' ");
			sb.append("   AND com_parent =  ?  ");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommonCodeVO vo = new CommonCodeVO();
				vo.setCom_cd( rs.getString("com_cd"));
				vo.setCom_nm( rs.getString("com_nm"));
				vo.setCom_parent(rs.getString("com_parent"));
				vo.setCom_ord(rs.getInt("com_ord"));
				vo.setCom_del_yn(rs.getString("com_del_yn"));
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

}

package com.study.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.board.vo.BoardSearchVO;
import com.study.board.vo.BoardVO;
import com.study.common.sql.CommonSQL;

// @Repository("daoOracle")
public class BoardDaoOracle implements IBoardDao {

	@Autowired
	private DataSource dataSource;
	
	private SqlSession sqlSession;
	
//	public void directSqlSessionTest() {
//		// insert,update, delete 모두 내부적으로 하나의 메서드 
//		sqlSession.insert("board.insertBoard"); // 파라미터가 필요없는 경우
//		sqlSession.update("board.updateBoard" , board) 
//		sqlSession.select();
//		sqlSession.selectOne("board.selectBoard", 23);
//		sqlSession.selectList("board.selectBoardList", searchVO);
//		sqlSession.selectMap();
//		
//	}
	
	
	// 싱글턴 패턴 : 해당 클래스가 오직 하나의 인스턴스(객체)만 생성되게 하는 방법
	//  1. 생성자를 private 
	//  2. 클래스 정적변수 생성 (일반적으로 변수이름은 instance )
	//  3. 정적변수에 대한 getter 생성 
	
//	// 1. 생성자를 private
//	private BoardDaoOracle() {
//	}
//	
//	// 2. 클래스 정적변수 생성 (일반적으로 변수이름은 instance )
//	private static BoardDaoOracle instance = new BoardDaoOracle();
//	
//	// 3. 정적변수에 대한 getter 생성
//	public static BoardDaoOracle getInstance() {
//		if(instance == null) instance = new BoardDaoOracle();
//		return instance;
//	}
	
	
	private String searchQueryCreate(BoardSearchVO searchVO) {
		StringBuffer sb = new StringBuffer();
		// 검색어
		if(searchVO.getSearchWord() != null 
				     && !searchVO.getSearchWord().isEmpty()  ) {
			switch (searchVO.getSearchType()) {
			case "T": sb.append(" AND bo_title   like '%' || ? || '%' ");break;
			case "W": sb.append(" AND bo_writer  like '%' || ? || '%' ");break;
			case "C": sb.append(" AND bo_content like '%' || ? || '%' ");break;
			default:  sb.append(" AND ( ");
			          sb.append("          bo_title   like '%' || ? || '%' ");
			          sb.append("       OR bo_writer  like '%' || ? || '%' ");
			          sb.append("       OR bo_content like '%' || ? || '%' ");
			          sb.append("      ) ");
					  break;
			}
		}
		// 내용 분류 
		if(searchVO.getSearchClass() != null 
				     && !searchVO.getSearchClass().isEmpty()) {
			sb.append(" AND bo_class = ? ");
		}
		
		return sb.toString();
	}
	
	private int searchParamSetting(PreparedStatement pstmt, BoardSearchVO searchVO) throws Exception {
		int idx = 1; // 기존에 설정한 값 읽어야 하는데.. 현재는 1로 
		// 검색어
		if(searchVO.getSearchWord() != null 
				     && !searchVO.getSearchWord().isEmpty()  ) {
			switch (searchVO.getSearchType()) {
			case "T": pstmt.setString(idx++, searchVO.getSearchWord() ); break;
			case "W": pstmt.setString(idx++, searchVO.getSearchWord() ); break;
			case "C": pstmt.setString(idx++, searchVO.getSearchWord() ); break;
			default:  pstmt.setString(idx++, searchVO.getSearchWord() );
			          pstmt.setString(idx++, searchVO.getSearchWord() );
			          pstmt.setString(idx++, searchVO.getSearchWord() );
					  break;
			}
		}
		// 내용 분류 
		if(searchVO.getSearchClass() != null 
				     && !searchVO.getSearchClass().isEmpty()) {
			pstmt.setString(idx++, searchVO.getSearchClass() );
		}
		return idx;		
	}
	
	
	@Override
	public int selectBoardCount(BoardSearchVO searchVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			// conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			conn = dataSource.getConnection();
			
			sb.append("SELECT count(*) as cnt ");
			sb.append("  FROM board           ");
			sb.append(" WHERE bo_del_yn = 'N' ");			
			// 검색어 구문 설정 
			sb.append(searchQueryCreate(searchVO));
			
			// 구문객체 생성 ->  파라미터 셋팅  -> 구문실행 
			pstmt = conn.prepareStatement(sb.toString());
			// 검색어 파라미터 설정 
			int idx = searchParamSetting(pstmt, searchVO);
			
			System.out.println("--------------------------------");
			System.out.printf("SQL=%s\n", sb.toString());
			System.out.printf("vo =%s\n", searchVO.toString());
			System.out.println("--------------------------------");
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			return cnt;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)     rs.close();
			if (pstmt != null) 	pstmt.close();
			if (conn != null)   conn.close();
		}
	}
	
	
	@Override
	public List<BoardVO> selectBoardList(BoardSearchVO searchVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			List<BoardVO> list = new ArrayList<BoardVO>();
//			sb.append("SELECT * ");
//			sb.append("  FROM ( SELECT rownum rnum, a.* ");
//			sb.append("           FROM (  ");			
			sb.append(CommonSQL.PAGING_PREFIX);			
			sb.append("SELECT ");
			sb.append("       bo_no,");
			sb.append("       bo_title,");
			sb.append("       bo_writer,");
			sb.append("       bo_pass,");
			sb.append("       bo_hit,");
			sb.append("       bo_ip,");
			sb.append("       TO_CHAR(bo_reg_date, 'yyyy-mm-dd') AS bo_reg_date,");
			sb.append("       TO_CHAR(bo_mod_date, 'yyyy-mm-dd') AS bo_mod_date,");
			sb.append("       bo_del_yn,");
			sb.append("       bo_class,");
			sb.append("       (select com_nm from com_code where bo_class = com_cd ) as bo_class_nm  ");
			sb.append("  FROM BOARD           ");
			sb.append(" WHERE BO_DEL_YN = 'N' ");
			// 검색어 구문 설정
			sb.append(searchQueryCreate(searchVO));  // **			
			sb.append(" ORDER BY bo_no DESC   ");			
			sb.append(CommonSQL.PAGING_SUFFIX);
			
			System.out.println("----------------------------------");
			System.out.println(sb.toString());
			System.out.println(searchVO);
			System.out.println("----------------------------------");
			
			pstmt = conn.prepareStatement(sb.toString());
			// 검색어 파라미터 설정 
			int idx = searchParamSetting(pstmt, searchVO);   // (**
			pstmt.setInt(idx++, searchVO.getEndRow());
			pstmt.setInt(idx++, searchVO.getStartRow());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBo_no(rs.getInt("bo_no"));
				vo.setBo_title(rs.getString("bo_title"));
				vo.setBo_writer(rs.getString("bo_writer"));
				vo.setBo_pass(rs.getString("bo_pass"));
				vo.setBo_hit(rs.getInt("bo_hit"));
				vo.setBo_ip(rs.getString("bo_ip"));
				vo.setBo_reg_date(rs.getString("bo_reg_date"));
				vo.setBo_mod_date(rs.getString("bo_mod_date"));
				vo.setBo_del_yn(rs.getString("bo_del_yn"));
				vo.setBo_class(rs.getString("bo_class"));
				vo.setBo_class_nm(rs.getString("bo_class_nm"));
				
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
	public BoardVO selectBoard(int bo_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			List<BoardVO> list = new ArrayList<BoardVO>();
			sb.append("SELECT");
			sb.append("    BO_NO,");
			sb.append("    BO_TITLE,");
			sb.append("    BO_WRITER,");
			sb.append("    BO_PASS,");
			sb.append("    BO_CONTENT,");
			sb.append("    BO_HIT,");
			sb.append("    BO_IP,");
			sb.append("    TO_CHAR(BO_REG_DATE, 'YYYY.MM.DD') AS BO_REG_DATE,");
			sb.append("    TO_CHAR(BO_MOD_DATE, 'YYYY.MM.DD') AS BO_MOD_DATE,");
			sb.append("    BO_DEL_YN,");
			sb.append("    BO_CLASS");
			sb.append(" FROM");
			sb.append("    BOARD");
			sb.append(" WHERE");
			sb.append("    BO_NO = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, bo_no);
			rs = pstmt.executeQuery();
			BoardVO vo = null;
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBo_no(rs.getInt("bo_no"));
				vo.setBo_title(rs.getString("bo_title"));
				vo.setBo_writer(rs.getString("bo_writer"));
				vo.setBo_pass(rs.getString("bo_pass"));
				vo.setBo_content(rs.getString("bo_content"));
				vo.setBo_hit(rs.getInt("bo_hit"));
				vo.setBo_ip(rs.getString("bo_ip"));
				vo.setBo_reg_date(rs.getString("bo_reg_date"));
				vo.setBo_mod_date(rs.getString("bo_mod_date"));
				vo.setBo_del_yn(rs.getString("bo_del_yn"));
				vo.setBo_class(rs.getString("bo_class"));
				list.add(vo);
			}
			return vo;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)	rs.close();
			if (pstmt != null)pstmt.close();
			if (conn != null)conn.close();
		}
	}

	@Override
	public int insertBoard(BoardVO board) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			sb.append("INSERT INTO board (     ");
			sb.append("	    bo_no, bo_title, bo_writer,     ");
			sb.append("	    bo_pass, bo_content, bo_hit,    ");
			sb.append("	    bo_ip, bo_reg_date, bo_mod_date,");
			sb.append("	    bo_del_yn, bo_class             ");
			sb.append("	  ) VALUES (");
			sb.append("	    seq_board.nextval, ?, ?,");
			sb.append("	    ?, ?, 0,          ");
			sb.append("	    ?, sysdate, null, ");
			sb.append("	    'N', ?            ");
			sb.append("	    )                 ");

			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, board.getBo_title());
			pstmt.setString(idx++, board.getBo_writer());
			pstmt.setString(idx++, board.getBo_pass());
			pstmt.setString(idx++, board.getBo_content());			
			pstmt.setString(idx++, board.getBo_ip());
			pstmt.setString(idx++, board.getBo_class());

			int cnt = pstmt.executeUpdate();
			return cnt;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)    rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null)  conn.close();
		}
	}

	@Override
	public int updateBoard(BoardVO board) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");

			sb.append("UPDATE BOARD");
			sb.append("   SET  BO_TITLE = ?");
			sb.append("     , BO_WRITER = ?");
			sb.append("     , BO_CONTENT = ?");
			sb.append("     , BO_IP = ?");
			sb.append("     , BO_MOD_DATE = sysdate");
			sb.append("     , BO_CLASS = ?");
			sb.append(" WHERE BO_NO   = ? ");
			sb.append("   AND BO_PASS = ? ");

			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;

			pstmt.setString(idx++, board.getBo_title());
			pstmt.setString(idx++, board.getBo_writer());
			pstmt.setString(idx++, board.getBo_content());
			pstmt.setString(idx++, board.getBo_ip());
			pstmt.setString(idx++, board.getBo_class());
			pstmt.setInt(idx++, board.getBo_no());
			pstmt.setString(idx++, board.getBo_pass());

			int cnt = pstmt.executeUpdate();
			return cnt;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public int deleteBoard(BoardVO board) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			return 0;
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	@Override
	public void increaseBoardCount(int bo_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");

			sb.append("UPDATE board");
			sb.append("   SET bo_hit = bo_hit + 1 ");
			sb.append(" WHERE bo_no  = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1,bo_no);			
			pstmt.executeUpdate();			
		} catch (SQLException ex) {
			throw ex;
		} finally {
			if (rs != null)	    rs.close();
			if (pstmt != null)	pstmt.close();
			if (conn != null)   conn.close();
		}
		
	}

	@Override
	public int deleteBoardChecked(int[] bo_nos) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}





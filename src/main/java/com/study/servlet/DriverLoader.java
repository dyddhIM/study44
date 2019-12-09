package com.study.servlet;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

// smb://ssam-pc
public class DriverLoader extends HttpServlet {

	@Override
	public void init() throws ServletException {		
		loadJDBCDriver();
		initConnectPool();
	}
	
	private void  loadJDBCDriver() {
		// 1. 드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("fail to JDBC Driver ", e);
		}
	}
	
	
	private void  initConnectPool() {		
		try {
			// 실제 커낵션을 생성할 ConnectionFactory 생성 
			ConnectionFactory connFactory = 
					 new DriverManagerConnectionFactory("jdbc:oracle:thin:@localhost:1521:xe"
							                            ,"java","oracle");
			// 커넥션 풀...
			PoolableConnectionFactory poolableConnFactory 
			    = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1 from dual");
			
			// 커넥션 풀 설정 
			GenericObjectPoolConfig poolConofig = new GenericObjectPoolConfig();
			poolConofig.setTimeBetweenEvictionRunsMillis(1000L *60L * 5L);
			poolConofig.setTestWhileIdle(true); 
			poolConofig.setMaxTotal(8);   // 동시에 사용할 수 있는 최대 커넥션 개수(기본값: 8)
			poolConofig.setMaxIdle(8);    // 커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수(기본값: 8)
			poolConofig.setMinIdle(4);    // 최소한으로 유지할 커넥션 개수(기본값: 0)
			
			GenericObjectPool<PoolableConnection> connectionPool 
			   = new GenericObjectPool<>(poolableConnFactory, poolConofig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = 
					(PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("study",connectionPool);
			
			System.out.println("DBCP 드라이버 등록 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	
}

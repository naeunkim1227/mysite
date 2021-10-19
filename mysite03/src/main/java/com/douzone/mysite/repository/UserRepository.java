package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVO;

@Repository
public class UserRepository {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	private Connection getconnection() throws SQLException{
		conn = null;
		
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}

		return conn;
	}
	
	//insert
	public void insert(UserVO vo) {
		conn = null;
		pstmt = null;
		
		try {
			conn = getconnection();
			
			//3. SQL 준비
			String sql = "insert into user values (null, ?, ? , ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			//5. SQL 실행
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//select
		public UserVO findbyEmailAndPassword(String email,String password) throws UserRepositoryException {
			UserVO vo  = null;
			
			try {
				conn = getconnection();
				
				sql = "select no,name from user where email = ? and password = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//이메일, 패스워드 일치
					vo = new UserVO();
					
					vo.setNo(rs.getLong(1));
					vo.setName(rs.getString(2));
					
				}else {
					//이메일,패스워드 불일치
					System.out.println("일치 항목 없음");
				}

			
			
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString());
			}
			return vo;
			
			
		}

		/* 내코드..
		public UserVO findByNo(long no) throws UserRepositoryException {
			UserVO vo  = null;
			
			try {
				conn = getconnection();
				
				sql = "select name,email,gender from user where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, no);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo = new UserVO();
					
					vo.setName(rs.getString(1));
					vo.setEmail(rs.getString(2));
					vo.setGender(rs.getString(3));
					
				}
				
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString());
			}
			
			return vo;
		}*/
		

		public UserVO findByNo(Long no) throws UserRepositoryException {
			UserVO vo = null;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
					
			try {
				conn = getconnection();
				
				String sql =
					" select no, name, email, gender " + 
				    "   from user " + 
					"  where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, no);

				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					vo = new UserVO();
					
					vo.setNo(rs.getLong(1));
					vo.setName(rs.getString(2));
					vo.setEmail(rs.getString(3));
					vo.setGender(rs.getString(4));
				}
			} catch (SQLException e) {
				throw new UserRepositoryException(e.toString());
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return vo;
		}
		

		public UserVO update(UserVO vo) {
			try {
				conn = getconnection();
				sql  = "select no from user where password = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getPassword());
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					sql = "update user set name= ?,gender=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, vo.getName());
					pstmt.setString(2, vo.getGender());
					
					pstmt.executeUpdate();
				}else {
					System.out.println("비밀번호 불일치");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return vo;
			
		}
	
}

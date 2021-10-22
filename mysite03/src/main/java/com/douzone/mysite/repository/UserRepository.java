package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	//insert
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert" , vo);
	}
	
	
	//select
		public UserVo findbyEmailAndPassword(String email,String password)
				throws UserRepositoryException {
			//두개 보내줘야함
			Map<String, String> map  =new HashMap<>();
			map.put("e", email);
			map.put("p", password);
			UserVo vo = sqlSession.selectOne("user.findByEmailAndPassword", map );
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
		

		public UserVo findByNo(Long no) throws UserRepositoryException {
			return sqlSession.selectOne("user.findByNo", no);
		}
		

		public boolean update(UserVo vo) {
			 int count = sqlSession.update("user.update", vo);
		 return count == 1;
			
		}
	
}

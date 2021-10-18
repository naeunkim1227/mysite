package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardDTO;

public class BoardDAO {
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


	public void write(BoardDTO dto) {
		
		try {
			conn = getconnection();
			sql = "insert into board values (null,?,?,0,now(),"
					+ "(select ifnull((select max(group_no) from board a),0))+1,1,0,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			pstmt.setLong(3, dto.getUser_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public List<BoardDTO> listall() {
		List<BoardDTO> list = new ArrayList<BoardDTO>(); 
		
		try {
			conn = getconnection();
			sql = "select a.no, a.title, a.contents, a.hit, "
					+ "a.reg_date, a.group_no, a.order_no,"
					+ " a.depth, a.user_no, b.name "
					+ "from board a join member b on a.user_no = b.no";
			
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContents(rs.getString(3));
				dto.setHit(rs.getInt(4));
				dto.setReg_date(rs.getDate(5));
				dto.setGroup_no(rs.getInt(6));
				dto.setOrder_no(rs.getInt(7));
				dto.setDepth(rs.getInt(8));
				dto.setUser_no(rs.getInt(9));
				dto.setName(rs.getString(10));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}

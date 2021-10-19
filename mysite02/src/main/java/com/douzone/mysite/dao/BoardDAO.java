package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.UserVO;

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
					+ "(select ifnull((select max(group_no) from board a),0))+1,1,0,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContents());
			pstmt.setLong(3, dto.getUser_no());
			pstmt.setString(4, "false");
			
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
					+ "a.reg_date, a.group_no, a.order_no,a.depth, "
					+ "a.user_no, b.name, a.is_del from board a join "
					+ "member b on a.user_no = b.no order by a.no desc";
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
				dto.setIs_del(rs.getString(11));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	public BoardDTO findwrite(long no) {
		BoardDTO dto = new BoardDTO();
		
		try {
			conn = getconnection();
			sql = "select no,title,contents from board where no= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

		
			if (rs.next()) {
				dto.setNo(rs.getLong(1));
				dto.setTitle(rs.getString(2));
				dto.setContents(rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}


	public void modify(BoardDTO dto) {
		try {
			conn = getconnection();
			sql = "select * from member where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getUser_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String sql2 = "update board set title= ?, contents = ? where no = ?";
				
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, dto.getTitle());
				pstmt2.setString(2, dto.getContents());
				pstmt2.setLong(3, dto.getNo());
				
				pstmt2.executeUpdate();
			}else {
				System.out.println("유저 번호 없음");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	public void hitcount(long no) {
		try {
			conn = getconnection();
			sql = "update board set hit=hit+1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public long checkwriter(Long no) {
		long checkwriter = 0;
		
		try {
			conn =getconnection();
			sql = "select user_no from board where no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkwriter = rs.getLong(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkwriter;
		
		
	}


	public void delete(Long no) {
		try {
			conn = getconnection();
			sql = "delete from board where no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	
	
}

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
	PreparedStatement pstmt,pstmt1,pstmt2 = null;
	ResultSet rs = null;
	String sql,sql1,sql2 = null;
	
	
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
					+ "user b on a.user_no = b.no order by a.group_no desc ,a.order_no desc ,a.depth asc";
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
			sql = "update board set is_del=true where no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public void replyinsert(BoardDTO dto) {
		try {
			conn = getconnection();
			sql = "select group_no,depth from board where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, dto.getNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				Long groupno = rs.getLong(1);
				Long depth = rs.getLong(2);
				
				System.out.println("1");
				sql1 = "update board set order_no = order_no+1 where group_no = ? and order_no >= 1";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setLong(1, groupno);
				pstmt1.executeUpdate();
				
				System.out.println("2");
				sql2 = "insert into board values (null,?,?,0,now(),?,1,?,?,'false')";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, dto.getTitle());
				pstmt2.setString(2, dto.getContents());
				pstmt2.setLong(3, groupno);
				pstmt2.setLong(4, depth+1);
				pstmt2.setLong(5, dto.getUser_no());
				pstmt2.executeUpdate();
				System.out.println("3");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<BoardDTO> searchlist(String kwd) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			
			
			conn = getconnection();
			sql = "select a.no, a.title, a.hit, a.reg_date, b.name from board a join user b on a.user_no = b.no where a.is_del = 'false' and a.title like ? or a.contents like ? or b.name like ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+kwd +"%");
			pstmt.setString(2, "%"+kwd +"%");
			pstmt.setString(3, "%"+kwd +"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getLong(1));
				dto.setTitle(rs.getString(2));
				dto.setHit(rs.getInt(3));
				dto.setReg_date(rs.getDate(4));
				dto.setName(rs.getString(5));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


	
	
}

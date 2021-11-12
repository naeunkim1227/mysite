package com.douzone.mysite.repository;

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

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.guestbookVO;


@Repository
public class GuestbookRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlsession;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	//insert
	public void insert(guestbookVO vo) {
		System.out.println(vo);
		sqlsession.insert("guestbook.insert", vo);
	}
	
	//select
	public List<guestbookVO> findall() throws GuestbookRepositoryException {
		List<guestbookVO> list = sqlsession.selectList("guestbook.findAll");
		return list;
	}
	
	//AJAX insert
	public List<guestbookVO> findall(Long no) {
		List<guestbookVO> list = sqlsession.selectList("guestbook.findAllno", no);
		return list;
	}
	
	//delete
	public void delete(guestbookVO vo) {
		sqlsession.delete("guestbook.delete", vo);	
	}


	
}

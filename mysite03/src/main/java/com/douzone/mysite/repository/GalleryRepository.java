package com.douzone.mysite.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.guestbookVO;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(GalleryVo vo) {
		sqlSession.insert("gallery.insert" , vo);
	}

	public List<GalleryVo> getimages() {
		return sqlSession.selectList("gallery.getimages");
		
	}

	public int delete(int no) {
		return sqlSession.delete("gallery.delete", no);
	}
	
	
}

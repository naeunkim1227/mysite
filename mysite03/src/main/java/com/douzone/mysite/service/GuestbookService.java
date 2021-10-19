package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.guestbookVO;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<guestbookVO> getlist() {
		return guestbookRepository.findall();
	}

	public void write(guestbookVO vo) {
		guestbookRepository.insert(vo);
	}

	public void delete(guestbookVO vo) {
		guestbookRepository.delete(vo);
	}

}

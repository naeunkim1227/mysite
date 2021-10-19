package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void join(UserVO vo) {
		userRepository.insert(vo);
	}

	public UserVO getuser(Long no) {
		return userRepository.findByNo(no);
	}
	
	
	public UserVO getuser(String email, String password) {
		return userRepository.findbyEmailAndPassword(email, password);
	}
	
	
	
	
	
}

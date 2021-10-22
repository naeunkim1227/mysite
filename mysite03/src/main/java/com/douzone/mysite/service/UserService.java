package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
	}

	public UserVo getuser(Long no) {
		return userRepository.findByNo(no);
	}
	
	
	public UserVo getuser(String email, String password) {
		return userRepository.findbyEmailAndPassword(email, password);
	}

	public void updateuser(UserVo userVo) {
		userRepository.update(userVo);
	}
	
	
	
	
	
}

package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join" ,method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	
	@RequestMapping(value = "/join" ,method = RequestMethod.POST)
	public String join(UserVO vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session) {
		return "user/update";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value = "email", required = true, defaultValue = "" ) String email, 
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			Model model) {
		 UserVO userVo = userService.getuser(email,password);
		 if(userVo == null) { 
		 model.addAttribute("result", "fail");
		 }

		 //인증처리
		 session.setAttribute("authUser", userVo);
		 
		return "redirect:/";
	}
	
	
	
	
	
	
}

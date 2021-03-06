package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join" ,method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	
	@RequestMapping(value = "/join" ,method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		//@Valid 잘못된 값이 무엇잇지 알려주기 위해 데이터 다시 가져가야 하므로 @ModelAttribute사용해서 가져가기
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			
			/*for(ObjectError error : list) {
				System.out.println(error);
			}*/
			
			//model.addAttribute("userVo",map.get("userVo")); == 아래와 같은 결과
			
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		
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
	
	
	/*Login interceptor에서 처리하므로 메소드 삭제
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value = "email", required = true, defaultValue = "" ) String email, 
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			Model model) {
		 UserVo userVo = userService.getuser(email,password);
		 if(userVo == null) { 
		 model.addAttribute("result", "fail");
		 	return "user/login";
		 }

		 //인증처리
		 session.setAttribute("authUser", userVo);
		 
		return "redirect:/";
	*/
	
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser ,Model model) {
		//session에 있는 정보가 필요하므로 @Auth로 인증만 하면 안된다.
		//@AuthUser 로 세션에 있는 사용자 정보를 가져와서 보낸다.
		//@AuthUser를 생성해줘야 한다.
		
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		
		//수정후 헤더의 아이디도 바뀌도록 새로 지정
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
	

	
	
	
	
	
	
	
	
}

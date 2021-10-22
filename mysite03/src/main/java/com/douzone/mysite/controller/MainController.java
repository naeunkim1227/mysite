package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;

@Controller
public class MainController {
	
	@Auth
	@RequestMapping({"", "/main"})
	public String index() {
		/*
		@Auth("USER") USER 로 로그인한 사람들만 뭘 할 수 있게..만든다...
		default 값을 유저라고 생성해둔다.
		ADMIN만 접근 가능 , USER는 접근 불가
		@Auth(role="ADMIN") ADMIN만 접근가능
		@Auth(메소드 이름="접근가능자")
		@Auth 모두 접근가능
		
		-------------------------------
		인터셉터가 핸들러에게 auth가 있냐 물어본다. Auth가 있을때, 세션에서 값을 꺼내서 ADMIN이면 
		들어갈 수 있다.
		
		데이터를 가져오는 방법 > Repository에서... UServo를 가져와서 DB에서 조회 Role을 가져온다 >> ADMIN, USER인지 가져와서 처리
		
		
		*/
		return "main/index";
	}
	
	
}
 
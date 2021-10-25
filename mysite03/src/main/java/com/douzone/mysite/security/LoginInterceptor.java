package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		//UserVo authUser =  new UserService().getuser(email, password);
		//new 로 선언해주면 안 된다. > new 로 선언해버리면 새로운 객체 생성 > 레포지토리가 null값이 떠서 , null예외 발생함
		//autowired를 통해 주입이 가능하다.
		System.out.println("LoginInterceptor 실행");
		UserVo authUser = userService.getUser(email, password);
		if(authUser == null) {
			request.setAttribute("result", "fail");
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").
			forward(request, response);
			
			return false;
		}
		
		//session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		response.sendRedirect(request.getContextPath());
		return false;
	}

	
}

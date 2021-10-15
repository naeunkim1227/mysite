package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDAO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("update 실행");
		
		UserVO vo = new UserVO();
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setGender(request.getParameter("gender"));
		
		UserVO user = new UserDAO().update(vo);
		
	
		HttpSession session = request.getSession();
		
		session.setAttribute("user", user);
		
		MvcUtil.redirect("/mysite02/", request, response);
		
	}

}

package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.UserDAO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"); 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO vo= new UserVO();
		vo.setEmail(email);
		vo.setGender(gender);
		vo.setName(name);
		vo.setPassword(password);
		
		new UserDAO().insert(vo);
	}

}

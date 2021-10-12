package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.util.MvcUtil;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("a");
		
		if("joinform".equals(action)) {
			MvcUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);
		}else if("join".equals(action)){
			System.out.println("join 호출");
			
			
			
		}else {
			MvcUtil.redirect(request.getContextPath() , request, response);
			
		}
		

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.douzone.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcUtil {
	public static void forward(String path, HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//이리로 가는 rd를 달라, 주소지를 path 변수 로 받아서 보낸다.
		RequestDispatcher rd =  request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

	public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException {
		
		response.sendRedirect(url);
		
	}
}

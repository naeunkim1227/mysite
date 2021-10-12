package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.guestbookDAO;
import com.douzone.mysite.vo.guestbookVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		
		guestbookVO vo  =  new guestbookVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(content);
		new guestbookDAO().insert(vo);
		
		MvcUtil.redirect("/mysite02/guest?g=list", request, response);

	}

}

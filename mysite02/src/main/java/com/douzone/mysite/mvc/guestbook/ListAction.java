package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.guestbookDAO;
import com.douzone.mysite.vo.guestbookVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<guestbookVO> list =  new guestbookDAO().findall();
		
		HttpSession session = request.getSession(true);
		session.setAttribute("list", list);
		
		MvcUtil.forward("guestbook/list", request, response);
	}

}

package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.dao.UserDAO;
import com.douzone.mysite.dao.guestbookDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		
		new BoardDAO().hitcount(no);
		BoardDTO dto = new BoardDAO().findwrite(no);
		request.setAttribute("dto", dto);
		
		MvcUtil.forward("board/view", request, response);
	}

}

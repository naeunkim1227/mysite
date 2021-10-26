package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDAO22;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class deleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userno = Long.parseLong(request.getParameter("userno"));
		Long no = Long.parseLong(request.getParameter("no"));
		
		Long checkwriter = new BoardDAO22().checkwriter(no);
		
		System.out.println(userno);
		if(userno.equals(checkwriter)) {
			new BoardDAO22().delete(no);
		}else {
			System.out.println("글 작성자 아님,삭제 할 수 없음");
		}
		
		MvcUtil.redirect("/mysite02/board", request, response);
	}

}

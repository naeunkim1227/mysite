package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.web.mvc.Action;

public class deleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long userno = Long.parseLong(request.getParameter("userno"));
		Long no = Long.parseLong(request.getParameter("no"));
		
		String checkwriter = new BoardDAO().checkwriter(no);
		
		
		if(userno.equals(checkwriter)) {
			
			new BoardDAO().delete(no);
			
		}else {
			System.out.println("글 작성자 아님,삭제 할 수 없음");
		}
		

	}

}

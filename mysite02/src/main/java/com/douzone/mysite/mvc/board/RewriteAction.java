package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO22;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class RewriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	      HttpSession session = request.getSession();
	      UserVO authUser = (UserVO) session.getAttribute("authUser");   
	      if(authUser == null) {
	         MvcUtil.redirect(request.getContextPath(), request, response);
	         return;
	      }
	      
		BoardDTO dto = new BoardDTO();
		dto.setContents(request.getParameter("content"));
		dto.setTitle(request.getParameter("title"));
		dto.setUser_no(authUser.getNo());
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		
		new BoardDAO22().replyinsert(dto);
		
		MvcUtil.redirect(request.getContextPath()+"/board", request, response);
	}

}

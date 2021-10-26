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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");

		if(authUser == null) {
			System.out.println("유저정보 없음 메인으로 이동");
			MvcUtil.redirect(request.getContextPath(), request, response);
			return; //밑의 코드가 실행되지 않도록 한다.
		}
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContents(request.getParameter("content"));
		dto.setUser_no(authUser.getNo());
		
		new BoardDAO22().write(dto);
		
		MvcUtil.redirect("/mysite02/board", request, response);
	}

}

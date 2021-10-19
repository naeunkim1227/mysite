package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");

		if(authUser == null) {
			System.out.println("유저정보 없음 메인으로 이동");
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		BoardDTO dto = new BoardDTO();
		dto.setUser_no(authUser.getNo());
		dto.setNo(no);
		dto.setContents(content);
		dto.setTitle(title);
		
		new BoardDAO().modify(dto);
		MvcUtil.redirect("/mysite02/board", request, response);

	}

}

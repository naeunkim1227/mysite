package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO22;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kwd = request.getParameter("kwd");
		System.out.println(kwd +"를 찾습니다..");
		
		List<BoardDTO> kwdlist = new BoardDAO22().searchlist(kwd);
		
		request.setAttribute("kwdlist", kwdlist);
		
		MvcUtil.forward("/board/list", request, response);
	}

}

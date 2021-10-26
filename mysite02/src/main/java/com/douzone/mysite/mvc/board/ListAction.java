package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.mysite.vo.PagingVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 0;
		if(request.getParameter("pageNum") == null) {
			String p = request.getParameter("pageNum");
			p = "1";
			pageNum = Integer.parseInt(p);
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		System.out.println(pageNum);
		
		int pagesize = 5;
		int currentpage = pageNum;
		int startRow = (currentpage-1)*pagesize+1;
		int endRow =currentpage*pagesize;
		
		
		List<BoardDTO> list = new BoardDAO().listall(startRow,pagesize);
		int boardcnt = new BoardDAO().getboardcount();
		
		
		HttpSession session = request.getSession(true);
		session.setAttribute("list", list);
		
		
		PagingVO pvo = new PagingVO(); 
		pvo.setCurrentpage(currentpage);
		pvo.setPageNum(pageNum);
		pvo.setPagesize(pagesize);
		pvo.setStartRow(startRow);
		pvo.setEndRow(endRow);
		
		session.setAttribute("pvo", pvo);
		session.setAttribute("boardcnt", boardcnt);
		
		MvcUtil.forward("/board/list", request, response);
	}

}
package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDAO22;
import com.douzone.mysite.vo.BoardDTO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction22 implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kwd = request.getParameter("kwd");
		int pageNum = 0;
		if(request.getParameter("pageNum") == null) {
			String p = request.getParameter("pageNum");
			p = "1";
			pageNum = Integer.parseInt(p);
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int Pagesize = 5;
		int ListSize = 5;
		int boardcount = new BoardDAO22().getboardcount(kwd);
		int pagecount = boardcount / ListSize;
		int blockcount = pagecount / Pagesize;
		int currentblock = pageNum / Pagesize;
		
		if(pageNum > pagecount) {
			pageNum = pagecount;
			currentblock = pageNum / Pagesize;
		}
		
		if(pageNum < 1) {
			pageNum = 1;
			currentblock = 1;
		}
		
		
		int beginPage = currentblock == 0? 1 : (currentblock -1) * Pagesize +1;
		int prevPage = (currentblock > 1)? (currentblock - 1) * Pagesize : 0;
		int nextPage = (currentblock < blockcount) ? currentblock * Pagesize +1 :0;
		int endPage = (nextPage > 0)? (beginPage -1 ) + ListSize : pagecount;
		
		List<BoardDTO> list = new BoardDAO22().listall(pageNum ,kwd, ListSize );
		
		HttpSession session = request.getSession(true);
		
		Map<String, Object> map  = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("listSize", ListSize);
		map.put("boardCount", boardcount);
		map.put("beginPage", beginPage);
		map.put("pageNum", pageNum);
		map.put("endPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("keyword", kwd);
		
		session.setAttribute("map", map);
		
		MvcUtil.forward("/board/list", request, response);
	}

}

package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDAO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//액세스 제어(로그인 유무,인증체크,보안처리를 해줘야함) Access Control
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");

		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return; //밑의 코드가 실행되지 않도록 한다.
		}
		
		//authUser가 가지고 있는 데이터가 no,name뿐임 세션에 데이터를 많이 저장하는것은 좋지 않기때문에
		//no를 가지고 쿼리를 한번더 짜주기.
		long no = authUser.getNo();
		UserVO user = new UserDAO().findByNo(no);
		
		request.setAttribute("user", user);
		
		MvcUtil.forward("user/updateform", request, response);

	}

}

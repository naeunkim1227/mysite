package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
			//업로드 이미지에서 exclude를 하지 않는 이유는 handler가 알아서..뭐..타입을...분류해줘서 라고..하는거 같은데..
			
		//1.handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			//컨트롤러가 아니면, 이미지등의 다른..데이터면...
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handelr Method의 @Auth 정보 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handelr Method에 @Auth가 없으면, Type에 있는지 확인
		if(auth == null) {
			//과제
			//클래스에 @Auth가 선언되어있는지 확인
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			//auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		//5. Type과 Method에 @Auth가 적용이 안되어 있는 경우
		//인증이 필요없는 경우
		if(auth == null) {
			return true;
		}
		
		
		//6. @Auth가 적용이 되어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			//세션 없을경우 로그인 창으로 보내기
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//7. 권한(Authorization) 체크를 위해서 @Auth의 role가져오기("USER", "ADMIN")
		String role = auth.role();
		
		
		System.out.println("----------------");
		System.out.println(role);
		System.out.println(authUser.getRole());
		System.out.println("----------------");
		//로그인한 유저가 admin이면 
		
		//role을 뺏더니 ADMIN이다 그럼 admin페이지로 보내고 user면 user 페이지로 보내기
		if(role.equals(authUser.getRole()) || authUser.getRole().equals("ADMIN")){
			return true;
		}
		
		//8.권한 체크
		//과제....
		response.sendRedirect(request.getContextPath());
		
		
		//여기서 컨트롤러로 보내면 안돼서 false
		return false;
	}

	
}

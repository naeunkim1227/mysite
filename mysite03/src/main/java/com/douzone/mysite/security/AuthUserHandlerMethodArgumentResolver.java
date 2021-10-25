package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	//@AuthUser 사용하면 쓰는 메소드....
	//파라미터가 넘어오면, 내가 원하는 데이터면 true리턴, 아니면 false리턴
	// @AuthUser 가 붙어 있는지,UserVo 타입인지 확인이 필요하다.
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		//컨트롤러 쪽 검사 함
		
		//어노테이션을 가져온다...
		 AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		 
		 //@AuthUser가 안 붙어 있음, 타입이 안맞음 
		 if(authUser == null) {
			 return false;
		 }

		 //어노테이션은 붙어있는데 타입이 틀린경우
		 if(parameter.getParameterType().equals(UserVo.class) == false) {
			 return false;
		 }
		 
		 
		 //@AuthUser 붙어있고, UserVo 타입일때
		 
		return true;
	}
	
	
	
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		
		if(! supportsParameter(parameter)) {
			//파라미터 값이 null이면 풀수없다고...보내준다..
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//톰캣의..뭐..?
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		//세션이 살아있다.
		//@AuthUser UserVo authUser 로 값을 넘겨주는 것이다
		return session.getAttribute("authUser");
	}


}

package com.douzone.mysite.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

import ch.qos.logback.core.spi.FilterAttachableImpl;

public class MysiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//root-context파일을 초기화할 코드를 넣으면 된다. 
		
		//return new Class<?>[] {String.class}; 스트링타입 클래스의 객체를 반환
		//?은 제네릭 타입으로 모든 타입 반환 가능
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	//언어 설정을 위한 필터 설정
	@Override
	protected Filter[] getServletFilters() {
		
		//필터 매핑은 안해줘도 되는..? 이 메소드를 사용할 경우 getServletMappings()에 명시된 주소로 매핑!
		//지정하고 싶으면 다른 메소드를 써줘야함 
		return new Filter[] {new CharacterEncodingFilter("UTF-8", false)};
	}

	
	//404,500 에러페이지 등록
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		//핸들러가 없으면 ControllerExceptionHandler로 이동해라.
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
	
	
	
	
	
}

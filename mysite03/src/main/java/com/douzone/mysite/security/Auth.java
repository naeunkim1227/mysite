package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

	//@interface로 명시하면 어노테이션으로 사용가능
	//Target으로 어디에 쓸건지 명시한다.
	//ElementType.METHOD 메소드 위에 @auth 사용 가능,ElementType.TYPE 클래스 위에 @auth 사용가능
	//컨트롤러에 있는 모든 메소드가 인증이 필요하다고 할때 클래스 위에 @Auth 선언한다.
	//실행시까지 붙어있어라... RetentionPolicy.RUNTIME
	
	
	//롤을 준다. 디폴트값 유저
	//public String value() default "USER";
	public String role() default "USER";
		
	//public boolean test() default false;
}

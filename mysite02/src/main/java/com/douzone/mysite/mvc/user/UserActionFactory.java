package com.douzone.mysite.mvc.user;

import com.douzone.mysite.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)) {
			System.out.println("파라미터 > joinform");
			action = new JoinFormAction();
		}else if("join".equals(actionName)) {
			System.out.println("파라미터 > join");
			action = new JoinAction();
		}else if("joinsuccess".equals(actionName)) {
			System.out.println("파라미터 > joinsuccess");
			action = new JoinSuccessAction();
		}else if("loginform".equals(actionName)) {
			System.out.println("파라미터 > loginform");
			action = new LoginFormAction();
		}else if("login".equals(actionName)) {
			System.out.println("파라미터 > login");
			action = new LoginAction();
		}else if("logout".equals(actionName)){
			System.out.println("파라미터 > logout");
			action = new LogoutAction();
		}else if("updateform".equals(actionName)){
			System.out.println("파라미터 > logout");
			action = new UpdateFormAction();
		}else if("update".equals(actionName)){
			System.out.println("파라미터 > logout");
			action = new UpdateAction();
		}else {
			action = new MainAction();
			
		}
		
		return action;
	}
	
	
}

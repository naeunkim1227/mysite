package com.douzone.mysite.mvc.guestbook;

import com.douzone.mysite.mvc.main.MainAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("write".equals(actionName)) {
			System.out.println("방명록 작성");
			action = new WriteAction();
		}else if("deleteform".equals(actionName)) {
			System.out.println("deleteform으로 이동");
			action = new DeleteformAction();
		}else if("delete".equals(actionName)) {
			System.out.println("delete 실행");
			action = new DeleteAction();
		}else {
			action = new ListAction();
		}
		
		
		
		return action;
	}

}

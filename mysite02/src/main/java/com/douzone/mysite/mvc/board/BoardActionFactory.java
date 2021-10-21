package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("list".equals(actionName)) {
			action = new ListAction();
		}else if("view".equals(actionName)){
			action = new ViewAction();
		}else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		}else if("modify".equals(actionName)) {
			action = new ModifyAction();
		}else if("delete".equals(actionName)){
			action = new deleteAction();
		}else if("writeform".equals(actionName)){
			action = new WriteFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();
		}else if("rewriteform".equals(actionName)) {
			action = new RewriteFormAction();
		}else if("rewrite".equals(actionName)) {
			action = new RewriteAction();
		}else if("search".equals(actionName)) {
			action = new SearchAction();
		}else{
			action = new ListAction();
		}
		
		return action;
	}

}

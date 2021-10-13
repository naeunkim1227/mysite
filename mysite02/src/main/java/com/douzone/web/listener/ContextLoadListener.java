package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce)  { 
		ServletContext sc = sce.getServletContext();
		String ContextConfigLocation = sc.getInitParameter("contextConfigLocation");
		
		//new WebXmlApplicationContext(ContextConfigLocation);
		
		System.out.println("Application Starts...." + ContextConfigLocation);
	}

	public void contextDestroyed(ServletContextEvent sce)  { 
    }

	
}

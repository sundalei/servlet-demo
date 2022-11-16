package com.wileyedge;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SimpleServletListener implements ServletRequestListener, ServletContextListener {
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("SimpleServletListener requestInitialized invoked.");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("SimpleServletListener contextInitialized invoked.");
	}
}

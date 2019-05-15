package com.cafe24.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextLoadListener implements ServletContextListener {
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	System.out.println("container starts");
    	servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    }	
}

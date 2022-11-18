package com.wileyedge;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/context")
public class ContextServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext servletContext = getServletContext();
		
		// Resource associated with the web context
		String contextPath = servletContext.getContextPath();
		String realPath = servletContext.getRealPath("."); // webapps folder
		
		// Object-valued attributes
		servletContext.setAttribute("username", "test");
		
		// Logging capabilities
		servletContext.log("This is a log message");
		
		System.out.println("contextPath: " + contextPath);
		System.out.println("realPath: " + realPath);
	}

}

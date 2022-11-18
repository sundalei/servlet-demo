package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/initParam", initParams = {
		@WebInitParam(name="username", value="John"),
		@WebInitParam(name="email", value="john@example.com")
})
public class MyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Enumeration<String> initParams = getInitParameterNames();
		Enumeration<String> contextInitParams = getServletContext().getInitParameterNames();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("<h2>Init params from MyServlet</h2>");
		for (Iterator<String> iterator = initParams.asIterator(); iterator.hasNext();) {
			String key = initParams.nextElement();
			String value = getInitParameter(key);
			builder.append("<h3>").append(key).append(":").append(value).append("</h3>");
		}
		
		builder.append("<h2>Init params from Servlet Context</h2>");
		for (Iterator<String> iterator = contextInitParams.asIterator(); iterator.hasNext();) {
			String key = contextInitParams.nextElement();
			String value = getServletContext().getInitParameter(key);
			builder.append("<h3>").append(key).append(":").append(value).append("</h3>");
		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(builder.toString());
		out.flush();
	}

}

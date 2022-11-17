package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		
		req.setAttribute("username", username);
		
		String protocol = req.getProtocol();
		Locale locale = req.getLocale();
		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String queryString = req.getQueryString();
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.print("<h1>Information</h1>");
		out.print("<h2>" + username + "</h2>");
		out.print("<h2>" + protocol + "</h2>");
		out.print("<h2>" + locale.toString() + "</h2>");
		out.print("<h2>" + contextPath + "</h2>");
		out.print("<h2>" + servletPath + "</h2>");
		out.print("<h2>" + queryString + "</h2>");
		
		out.flush();
		
	}

}

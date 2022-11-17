package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/info")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("MyServlet doGet begin");
		
		Enumeration<String> headers = req.getHeaderNames();
		
		StringBuilder builder = new StringBuilder();
		for (Iterator<String> iterator = headers.asIterator(); iterator.hasNext();) {
			String header = iterator.next();
			builder.append("<h2>").append(header).append(":").append(req.getHeader(header)).append("</h2>");
		}
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<h1>headers<h1>");
		out.println(builder.toString());
		
		out.flush();
	}

}

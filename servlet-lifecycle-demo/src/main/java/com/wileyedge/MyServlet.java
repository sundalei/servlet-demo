package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("MyServlet init invoked.");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("MyServlet service invoked.");
		super.service(req, res);
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MyServlet doGet invoked.");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Hello World Servlet</h1>");
        out.flush();
    }

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("MoodServlet destroy invoked.");
	}
}

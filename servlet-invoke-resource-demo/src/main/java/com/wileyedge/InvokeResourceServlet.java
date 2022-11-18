package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class InvokeResourceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username.equals("test") && password.equals("test")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("welcome");
			dispatcher.forward(req, resp);
		} else {
			out.print("User name or password is incorrect!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.include(req, resp);
		}
	}

}

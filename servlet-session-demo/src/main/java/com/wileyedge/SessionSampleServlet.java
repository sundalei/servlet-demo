package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionSampleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get the Session object
		HttpSession session = req.getSession();
		
		// Get the session data value
		String username = (String) session.getAttribute("session.username");
		if (username == null) {
			session.setAttribute("session.username", "testUser");
		}
		
		// Output the page.
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		if (username == null) {
			out.println("Please Login in");
		} else {
			out.println("User : " + username);
		}
		
		out.flush();
	}

}

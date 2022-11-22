package com.wileyedge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/xyzpresource"})
public class XYZPUpgradeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if ("XYZP".equals(req.getHeader("Upgrade"))) {
			// Accept upgrade request
			resp.setStatus(101);
			resp.setHeader("Upgrade", "XYZP");
			resp.setHeader("Connection", "Upgrade");
			resp.setHeader("OtherHeaderB", "Value");
			
			// Delegate the connection to the upgrade handler
			XYZPUpgradeHandler handler = req.upgrade(XYZPUpgradeHandler.class);
		} else {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<h1>Hello World</h1>");
			out.flush();
		}
	}

}

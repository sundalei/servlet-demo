package com.wileyedge;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/asyncservlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		AsyncContext acontext = req.startAsync();
		acontext.start(new Runnable () {

			@Override
			public void run() {
				String param = acontext.getRequest().getParameter("param");
				HttpServletResponse response = (HttpServletResponse) acontext.getResponse();
				try {
					response.getWriter().write(param);
					response.getWriter().flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				acontext.complete();
			}});
	}
}

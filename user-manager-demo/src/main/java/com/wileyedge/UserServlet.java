package com.wileyedge;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wileyedge.dao.UserDao;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String INSERT_OR_EDIT = "/user.jsp";
	private static final String LIST_USER = "/listUser.jsp";
	
	private UserDao dao;
	
	public UserServlet() {
		this.dao = new UserDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String forward = "";
		String action = req.getParameter("action");
		
		if (action.equalsIgnoreCase("delete")) {
			
		} else if (action.equalsIgnoreCase("edit")) {
			
		} else if (action.equalsIgnoreCase("listUser")) {
			forward = LIST_USER;
			req.setAttribute("users", dao.getAllUsers());
		} else {
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

}

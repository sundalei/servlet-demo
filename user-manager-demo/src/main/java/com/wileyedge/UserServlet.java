package com.wileyedge;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wileyedge.dao.UserDao;
import com.wileyedge.model.User;
import com.wileyedge.util.DbUtil;

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
			
			int userId = Integer.parseInt(req.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            req.setAttribute("users", dao.getAllUsers());  
			
		} else if (action.equalsIgnoreCase("edit")) {
			
			forward = INSERT_OR_EDIT;
			int userId = Integer.parseInt(req.getParameter("userId"));
			User user = dao.getUserById(userId);
			req.setAttribute("user", user);
			
		} else if (action.equalsIgnoreCase("listUser")) {
			forward = LIST_USER;
			req.setAttribute("users", dao.getAllUsers());
		} else {
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		user.setFirstName(req.getParameter("firstName"));
		user.setLastName(req.getParameter("lastName"));

		try {
			Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("dob"));
			user.setDob(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		user.setEmail(req.getParameter("email"));
		String userId = req.getParameter("userid");

		if (userId == null || userId.isEmpty()) {
			dao.addUser(user);
		} else {
			user.setUserId(Integer.parseInt(userId));
			dao.updateUser(user);
		}

		RequestDispatcher view = req.getRequestDispatcher(LIST_USER);
		req.setAttribute("users", dao.getAllUsers());
		view.forward(req, resp);
	}
	
	@Override
	public void destroy() {  
		System.out.println("context destroy");  
		try {
			if (DbUtil.getConnection() != null) {
				DbUtil.getConnection().close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("e: " + e.getMessage());
			e.printStackTrace();
			
		}
	}
}

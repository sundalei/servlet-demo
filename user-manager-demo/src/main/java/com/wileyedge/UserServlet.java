package com.wileyedge;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wileyedge.dao.UserDao;
import com.wileyedge.model.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String INSERT_OR_EDIT = "/user.jsp";
	private static final String LIST_USER = "/listUser.jsp";
	
	private UserDao dao;
	
	@Override
	public void init() throws ServletException {
		
		System.out.println("UserServlet init invoked.");
		
		Properties properties = new Properties();
		InputStream inputStream = this.getServletContext().getResourceAsStream("db.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		
		this.dao = new UserDao(driver, url, user, password);
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
		System.out.println("UserServlet destroy invoked.");
		
		// ISSUE. The web application [user-manager-demo] registered the JDBC driver [com.mysql.cj.jdbc.Driver] 
		// but failed to unregister it when the web application was stopped. To prevent a memory leak, the JDBC Driver has been forcibly unregistered.
		// https://stackoverflow.com/questions/3320400/to-prevent-a-memory-leak-the-jdbc-driver-has-been-forcibly-unregistered
		
		Connection connection = dao.getConnection();
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

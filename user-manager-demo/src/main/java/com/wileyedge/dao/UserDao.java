package com.wileyedge.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wileyedge.model.User;
import com.wileyedge.util.DbUtil;

public class UserDao {
	
	private Connection connection;
	
	public UserDao() {
		this.connection = DbUtil.getConnection();
	}
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users");

			while(resultSet.next()) {
				User user = new User();
				user.setUserId(resultSet.getInt("userid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setDob(resultSet.getDate("dob"));
				user.setEmail(resultSet.getString("email"));
				users.add(user);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
}

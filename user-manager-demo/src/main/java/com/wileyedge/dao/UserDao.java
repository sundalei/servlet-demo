package com.wileyedge.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.wileyedge.model.User;
import com.wileyedge.util.DbUtil;

public class UserDao {
	
	private Connection connection;
	
	private String driver;
	private String url;
	private String user;
	private String password;
	

	public UserDao(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into users(firstname,lastname,dob,email) values (?, ?, ?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int userId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from users where userid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
							"where userid=?");
			// Parameters start with 1
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setInt(5, user.getUserId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public User getUserById(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from users where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				user.setUserId(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public Connection getConnection() {
		return connection;
	}
}

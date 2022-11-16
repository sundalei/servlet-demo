package com.wileyedge;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.wileyedge.util.DbUtil;

@WebListener
public class UserServletListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroy");
		System.out.println("aaaaa");
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

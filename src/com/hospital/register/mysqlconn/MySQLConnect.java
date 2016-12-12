package com.hospital.register.mysqlconn;

import java.sql.*;

import com.hospital.register.server.GlobalData;

public class MySQLConnect {
	
	// JDBC 驱动名及数据库 URL
	private static final String JDBC_DRIVER = GlobalData.JDBC_DRIVER;  
	private static final String DB_URL = GlobalData.DB_URL;
	
	// 数据库的用户名与密码，需要本地环境在GlobalData中修改
	private static final String USER = GlobalData.MYSQL_USER;
	private static final String PASS = GlobalData.MYSQL_PASSWORD;
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	public static void getConnected() {
		try {
			// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// 创建用于执行静态sql语句的Statement对象
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createUser(int userID, String username, String password, String truename) {
		getConnected();
		System.out.println("Connected to MySQL with Database chatto_demo to insert user.");
		String sql = "INSERT INTO users (id, username, password, truename) VALUES (" + userID + ", '" + username + "', md5('" + password + "'),'" + truename +"')";
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkPassword(String username, String password) {
		boolean result = false;
		getConnected();
		String sql = "SELECT * FROM users WHERE username = '" + username + "'";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("password").equals(MD5Password.MD5(password))) {
					System.out.println("The password is correct!");
					result = true;
				} else {
					System.out.println("The password isn't correct!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int getUserID(String username) {
		int id = -1;
		getConnected();
		String sql = "SELECT * FROM users WHERE username = '" + username + "'";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static void main (String[] args) {
		// for test
//		System.out.println(checkPassword("mohanyi", "password"));
//		createUser(UserID.getIdentifier(), "guoxin", "88888888", "郭鑫");
		System.out.println(getUserID("guoxin"));
	}
}


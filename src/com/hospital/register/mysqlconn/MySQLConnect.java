package com.hospital.register.mysqlconn;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hospital.register.server.GlobalData;
import com.hospital.register.server.PatientCheckInfo;
import com.hospital.register.server.PatientInfo;

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
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
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
	
	public static boolean createUser(int userID, String username, String password, String truename) {
		boolean result = false;
		getConnected();
		System.out.println("Connected to MySQL with Database hospital_register to insert user.");
		String sql = "INSERT INTO users (id, username, password, truename) VALUES (" + userID + ", '" + username + "', md5('" + password + "'),'" + truename +"')";
		try {
			stmt.execute(sql);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public static boolean updatePass(int userid, String oldPass, String newPass) {
		boolean result = false;
		getConnected();
		String sql = "SELECT * FROM users WHERE id = " + userid;
		String sql_2 = "UPDATE users SET password = md5('" + newPass + "') WHERE id = " + userid;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (!rs.getString("password").equals(MD5Password.MD5(oldPass))) return result;
			}
			stmt.execute(sql_2);
			result = true;
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean checkPassword(String username, String password, String identity) {
		boolean result = false;
		getConnected();
		String sql = "";
		if (identity.equals("user")) {
			sql = "SELECT * FROM users WHERE username = '" + username + "'";
		} else if (identity.equals("admin")) {
			sql = "SELECT * FROM admin WHERE username = '" + username + "'";
		}
		
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
	
	public static void createPatient(PatientInfo pi, int userid, int type) {
		getConnected();
		System.out.println();
		Date d= new Date();
		String today = sdf.format(d);
		String sql = "";
		if (type == 1) {
			sql = "INSERT INTO patients (number, id, name, gender, age, telephone, office, classification, price, userid, daytime, type) VALUES ('" + pi.getNumber() + "', '" + pi.getID() + "', '" + pi.getName() + "','" + pi.getGender() + "', '" + pi.getAge() + "', '" + pi.getTelephone() + "', '" + pi.getOffice() + "', '" + pi.getClassification() + "', '" + pi.getPrice() + "', '" + userid + "', '" + today + "', '" +  "初诊')";
		} else if (type == 2) {
			sql = "INSERT INTO patients (number, id, name, gender, age, telephone, office, classification, price, userid, daytime, type) VALUES ('" + pi.getNumber() + "', '" + pi.getID() + "', '" + pi.getName() + "','" + pi.getGender() + "', '" + pi.getAge() + "', '" + pi.getTelephone() + "', '" + pi.getOffice() + "', '" + pi.getClassification() + "', '" + pi.getPrice() + "', '" + userid + "', '" + today + "', '" +  "复诊')";
		}
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PatientInfo getPatientInfo(String number) {
		String id = null;
		String name = null;
		String gender = null;
		String age = null;
		String telephone = null;
		getConnected();
		String sql = "SELECT * FROM patients WHERE number = '" + number + "'";
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getString("id");
				name = rs.getString("name");
				gender = rs.getString("gender");
				age = rs.getString("age");
				telephone = rs.getString("telephone");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new PatientInfo(id, name, gender, age, telephone);
	}
	
	public static List<PatientCheckInfo> getPatients(String date, String office, String select) {
		List<PatientCheckInfo> patients = new ArrayList<PatientCheckInfo>();
		List<Integer> userids = new ArrayList<Integer>();
		String sql = "";
		getConnected();
		if (office.equals("所有科室")) {
			if (select.equals("1")) {
				sql = "SELECT * FROM patients WHERE daytime = '" + date +"' AND type = '初诊'"; 
			} else if (select.equals("2")) {
				sql = "SELECT * FROM patients WHERE daytime = '" + date +"' AND type = '复诊'"; 
			} else if (select.equals("3")) {
				sql = "SELECT * FROM patients WHERE daytime = '" + date +"'"; 
			}
		} else {
			if (select.equals("1")) {
				sql = "SELECT * FROM patients WHERE daytime = '" + date + "' AND office = '" + office + "' AND type = '初诊'";
			} else if (select.equals("2")) {
				sql = "SELECT * FROM patients WHERE daytime = '" + date + "' AND office = '" + office + "' AND type = '复诊'";
			} else if (select.equals("3")) {
				sql = "SELECT * FROM patients WHERE daytime = '" + date + "' AND office = '" + office + "'";
			}
		}
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				patients.add(new PatientCheckInfo(rs.getString("number"), rs.getString("name"), rs.getString("gender"), rs.getString("age"), rs.getString("telephone"), rs.getString("office"), rs.getString("classification"), rs.getString("price"), rs.getString("daytime"), rs.getString("type")));
				userids.add(rs.getInt("userid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < patients.size(); i++) {
			String sql2 = "SELECT truename FROM users WHERE id = '" + userids.get(i) + "'";
			try {
				rs = stmt.executeQuery(sql2);
				while (rs.next()) {
					patients.get(i).setUsername(rs.getString("truename"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return patients;
	}
	
	public static void main (String[] args) {
		// for test
//		System.out.println(checkPassword("mohanyi", "password"));
//		createUser(UserID.getIdentifier(), "guoxin", "88888888", "郭鑫");
		System.out.println(getUserID("guoxin"));
	}
}


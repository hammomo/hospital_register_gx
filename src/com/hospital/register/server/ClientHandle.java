package com.hospital.register.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.hospital.register.mysqlconn.MySQLConnect;

/*
 * 服务器线程处理类
 */
public class ClientHandle extends Thread {
	// 和本线程相关的socket
	private Socket socket = null;
	private InputStream is = null;
	private BufferedReader br = null;
	private OutputStream os = null;
	private PrintWriter pw = null;
	private boolean running = false;
	private String username, password, userID;
	private int userid;
	
	public ClientHandle(Socket socket) {
		this.socket = socket;
	}
	
	// 线程执行的操作，响应客户端的请求
	public void run() {
		running = createResources();
		
		while (running) {
			try {
				String str = br.readLine();
				if (str.startsWith("/q/")) {
					System.out.println("The user " + username + " login on " + socket.getInetAddress().toString() + ":" + socket.getPort() + " is about to login off...");
					break;
				}
				if (str.startsWith("/c/")) {
					boolean loginResult = login();
					send(loginResult + "");
				} else if (str.startsWith("/id/")) {
					userID = getUserID(str);
					send(userID);
				} else if (str.startsWith("/r/")) {
					handlePatientInfo();
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		closeResources();
	}
	
	public void handlePatientInfo() {
		try {
			System.out.println("I've got information from user " + userID);
			String id = br.readLine();
			String name = br.readLine();
			String gender = br.readLine();
			String age = br.readLine();
			String telephone = br.readLine();
			String office = br.readLine();
			String classification = br.readLine();
			String price = br.readLine();
			PatientInfo pi = new PatientInfo(id, name, gender, age, telephone, office, classification, price);
			pi.setNumber(PatientID.getPatientID());
			MySQLConnect.createPatient(pi, userid, 1);
			send(pi.getNumber());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeResources() {
		try {
			if(pw != null)
				pw.close();
			if(os != null)
				os.close();
			if(br != null)
				br.close();
			if(is != null)
				is.close();
			if(socket != null)
				socket.close();
			System.out.println("I've close resources for current client...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String str) {
		pw.write(str + "\n");
		pw.flush();
	}
	
	public boolean login() {
		try {
			username = br.readLine();
			password = br.readLine();
			return MySQLConnect.checkPassword(username, password);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getUserID(String str) {
		String username = str.substring(4);
		userid = MySQLConnect.getUserID(username);
		return userid + "";
	}
	
	public boolean createResources() {
		try {
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}

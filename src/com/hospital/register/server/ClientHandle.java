package com.hospital.register.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

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
					boolean loginResult = login(str);
					send(loginResult + "");
				} else if (str.startsWith("/id/")) {
					userID = getUserID(str);
					send(userID);
				} else if (str.startsWith("/r/")) {
					handlePatientInfo_1();
				} else if (str.startsWith("/2/")) {
					getPatientInfo(str);
				} else if (str.startsWith("/r2/")) {
					handlePatientInfo_2();
				} else if (str.startsWith("/u/")) {
					handlePassword();
				} else if (str.startsWith("/i/")) {
					handleCheckInfo(str);
				} else if (str.startsWith("/new/")) {
					String s = str.substring(5);
					String[] info = s.split("/n/");
					String username = info[0];
					String password = info[1];
					String truename = info[2];
					int userid = UserID.getIdentifier();
					boolean result = MySQLConnect.createUser(userid, username, password, truename);
					send(result + "");
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		closeResources();
	}
	
	public void handleCheckInfo(String str) {
		str = str.substring(3);
		String[] info = str.split("/n/");
		String date = info[0];
		String office = info[1];
		String select = info[2];
		List<PatientCheckInfo> patients = MySQLConnect.getPatients(date, office, select);
		System.out.println("patients size is " + patients.size());
		for (int i = 0; i < patients.size(); i++) {
			PatientCheckInfo p = patients.get(i);
			send(p.getNumber() + "/n/" + p.getName() + "/n/" + p.getGender() + "/n/" + p.getAge() + "/n/" + p.getTelephone() + "/n/" + p.getOffice() + "/n/" + p.getClassification() + "/n/" + p.getPrice() + "/n/" + p.getDate() + "/n/" + p.getType() + "/n/" + p.getUsername());
		}
		send("/end/");
	}
	
	public void handlePassword() {
		try {
			String oldPass = br.readLine();
			String newPass = br.readLine();
			boolean result = MySQLConnect.updatePass(userid, oldPass, newPass);
			send(result + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getPatientInfo(String str) {
		String patientNumber = str.substring(3);
		System.out.println("The patient number is " + patientNumber);
		PatientInfo patient_2 = MySQLConnect.getPatientInfo(patientNumber);
		send(patient_2.getID());
		send(patient_2.getName());
		send(patient_2.getGender());
		send(patient_2.getAge());
		send(patient_2.getTelephone());
	}
	
	public PatientInfo handlePatientInfo() {
		PatientInfo pi = null;
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
			pi = new PatientInfo(id, name, gender, age, telephone, office, classification, price);	
			pi.setNumber(PatientID.getPatientID());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pi;
	}
	
	public void handlePatientInfo_1() {
		PatientInfo pi = handlePatientInfo();
		MySQLConnect.createPatient(pi, userid, 1);
		send(pi.getNumber());
	}
	
	public void handlePatientInfo_2() {
		PatientInfo pi = handlePatientInfo();
		MySQLConnect.createPatient(pi, userid, 2);
		send(pi.getNumber());
	}
	
	public void closeResources() {
		try {
			if (pw != null)
				pw.close();
			if (os != null)
				os.close();
			if (br != null)
				br.close();
			if (is != null)
				is.close();
			if (socket != null)
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
	
	public boolean login(String str) {
		try {
			String identity = str.substring(3);
			username = br.readLine();
			password = br.readLine();
			return MySQLConnect.checkPassword(username, password, identity);
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

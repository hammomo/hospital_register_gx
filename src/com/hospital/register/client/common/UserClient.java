package com.hospital.register.client.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class UserClient {

	private String userID;
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private BufferedReader inFromServer;
	private PrintWriter pw;
	
	public UserClient(Socket socket, String userID) {
		this.socket = socket;
		this.userID = userID;
		openResources();
	}
	
	public void openResources() {
		try {
			in = socket.getInputStream();
			inFromServer = new BufferedReader(new InputStreamReader(in));
			out = socket.getOutputStream();
			pw = new PrintWriter(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String str) {
		pw.write(str + "\n");
		pw.flush();
	}
	
	public void closeResources() {
		send("/q/");
		try {
			inFromServer.close();
			in.close();
			pw.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String sendPatientInfo_1(RegisterInfo ri) {
		send("/r/" + userID);
		return sendPatientInfo(ri);
	}
	
	public String sendPatientInfo_2(RegisterInfo ri) {
		send("/r2/" + userID);
		return sendPatientInfo(ri);
	}
	
	public String sendPatientInfo(RegisterInfo ri) {
		String patientID = "";
		send(ri.getID());
		send(ri.getName());
		send(ri.getGender());
		send(ri.getAge());
		send(ri.getTelephone());
		send(ri.getOffice());
		send(ri.getClassification());
		send(ri.getPrice());
		try {
			patientID = inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return patientID;
	}
	
	public RegisterInfo secondRegister(String number) {
		send("/2/" + number);
		String id = null;
		String name = null;
		String gender = null;
		String age = null;
		String telephone = null;
		try {
			id = inFromServer.readLine();
			name = inFromServer.readLine();
			gender = inFromServer.readLine();
			age = inFromServer.readLine();
			telephone = inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RegisterInfo secondRegister = new RegisterInfo(id, name, gender, age, telephone);
		return secondRegister;
	}
	
	
}

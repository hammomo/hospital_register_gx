package com.hospital.register.client.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AdminClient {
	
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private PrintWriter pw;
	private BufferedReader inFromServer;
	
	public AdminClient(Socket socket) {
		this.socket = socket;
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
	
	public boolean createNewUser(String username, String password, String truename) {
		boolean result = false;
		send("/new/" + username + "/n/" + password + "/n/" + truename);
		try {
			result = Boolean.parseBoolean(inFromServer.readLine());
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public List<PatientCheckInfo> sendCheckInfo(String date, String office, int select) {
		List<PatientCheckInfo> patients = new ArrayList<PatientCheckInfo>();
		send("/i/" + date + "/n/" + office + "/n/" + select);
		while (true) {
			try {
				String str = inFromServer.readLine();
				if (str.equals("/end/")) break;
				System.out.println(str);
				String[] data = str.split("/n/");
				PatientCheckInfo p = new PatientCheckInfo(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10]);
				patients.add(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return patients;
	}
}

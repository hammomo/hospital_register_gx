package com.hospital.register.client.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginClient {
	
	private String username, password, identity, userID;
	private Socket clientSocket;
	private String address;
	private int port;
	private InputStream in;
	private OutputStream out;
	private BufferedReader inFromServer;
	private PrintWriter pw;
	private boolean loginResult;
	
	public LoginClient(String username, String password, String identity) {
		this.username = username;
		this.password = password;
		if (identity.equals("挂号人员")) this.identity = "user";
		this.address = GlobalData.ADDRESS;
		this.port = GlobalData.PORT;
	}
	
	public Socket getSocket() {
		return clientSocket;
	}
	
	public void sendUserInfo() {
		try {
			out = clientSocket.getOutputStream();
			pw = new PrintWriter(out);
			pw.write("/c/" + identity + '\n');
			pw.flush();
			pw.write(username + '\n');
			pw.flush();
			pw.write(password + '\n');
			pw.flush();
			System.out.println("Already send user info to server...");
		} catch (IOException e) {
			System.out.println("Can't send user info to server...");
			e.printStackTrace();
		}
	}
	
	public void getServerRequest() {
		try {
			in = clientSocket.getInputStream();
			inFromServer = new BufferedReader(new InputStreamReader(in));
			loginResult = Boolean.parseBoolean(inFromServer.readLine());
			System.out.println("The login result is " + loginResult);
		} catch (IOException e) {
			System.out.println("Can't get request from server...");
			e.printStackTrace();
		}
	}
	
	public String getUserID() {
		try {
			pw.write("/id/" + username + "\n");
			pw.flush();
			String str = inFromServer.readLine();
			return str;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "0000";
		} catch (IOException e) {
			e.printStackTrace();
			return "0000";
		}
	}
	
	public boolean openConnection() {
		boolean result;
		try {
			clientSocket = new Socket(address, port);
			result = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			result = false;
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} 
		return result;
	}
	
	public boolean getLoginResult() {
		return loginResult;
	}
}

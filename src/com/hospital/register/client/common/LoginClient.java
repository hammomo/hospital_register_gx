package com.hospital.register.client.common;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginClient {
	
	private String username, password, identity;
	private Socket clientSocket;
	private String address;
	private int port;
	
	public LoginClient(String username, String password, String identity) {
		this.username = username;
		this.password = password;
		this.identity = identity;
		this.address = GlobalData.address;
		this.port = GlobalData.port;
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
}

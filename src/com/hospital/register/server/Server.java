package com.hospital.register.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.hospital.register.mysqlconn.MySQLConnect;

public class Server implements Runnable {
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private BufferedReader inFromClient;
	private PrintWriter pw;
	private String username, password;
	private boolean running = false;
	private Thread run, handle;
	
	public Server() {
		try {
			serverSocket = new ServerSocket(GlobalData.PORT);
			running = true;
			System.out.println("The server is started on port: " + serverSocket.getLocalPort());
		} catch (IOException e) {
			running = false;
			e.printStackTrace();
		}
		run = new Thread(this, "Login Server");
		run.start();
	}

	@Override
	public void run() {
		while (running) {
			System.out.println("This thread is running...LOL");
			try {
				clientSocket = serverSocket.accept();
				System.out.println("The client is running on port: " + clientSocket.getPort());
				openResources();
				handle();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public void openResources() {
		try {
			in = clientSocket.getInputStream();
			inFromClient = new BufferedReader(new InputStreamReader(in));
			out = clientSocket.getOutputStream();
			pw = new PrintWriter(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void handle() {
		handle = new Thread("Handle") {
			public void run() {
				while (running) {
					try {
						String str = inFromClient.readLine();
						System.out.println(str);
						if (str == null) break;
						if (str.startsWith("/c/")) {
							str = str.substring(3);
							username = inFromClient.readLine();
							password = inFromClient.readLine();
							pw.write(getLoginResult() + "\n");
							pw.flush();
						} else if (str.startsWith("/id/")) {
							str = str.substring(4);
							int userID = MySQLConnect.getUserID(str);
							pw.write(userID + "\n");
							pw.flush();
						} else {
							
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		handle.start();
	}

	
	public boolean getLoginResult() {
		return MySQLConnect.checkPassword(username, password);
	}

	
	public static void main(String[] args) {
		Server server = new Server();
	}

}

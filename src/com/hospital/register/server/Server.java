package com.hospital.register.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private boolean running = false;
	private Thread run;
	private int count = 0;
	
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
				ClientHandle client = new ClientHandle(clientSocket);
				client.start();
				count++;
				System.out.println("客户端的数量：" + count);
				InetAddress address = clientSocket.getInetAddress();
				System.out.println("当前客户端的IP：" + address.getHostAddress());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	
	public static void main(String[] args) {
		Server server = new Server();
	}

}

package com.cafe24.network.chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class ChatServer {
	public static final int PORT = 5000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<PrintWriter> listWriters = new ArrayList<PrintWriter>();
		
		String os = System.getProperty("os.name").toUpperCase();
		String hostAddress = "";

		if(os.contains("LINUX")) {
			try {
				boolean isLoopBack = true;
				Enumeration<NetworkInterface> en;
				en = NetworkInterface.getNetworkInterfaces();
				
				while(en.hasMoreElements()) {
					NetworkInterface ni = en.nextElement();
					if(ni.isLoopback())
						continue;
					
					Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
					while(inetAddresses.hasMoreElements()){
						InetAddress ia = inetAddresses.nextElement();
						if(ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
							hostAddress = ia.getHostAddress();
							// System.out.println(hostAddress);
							isLoopBack = false;
							break;
						}
					}
					if(isLoopBack)
						break;
				}
				// System.out.println("IP = " + hostAddress);
			}catch(SocketException e) {
				e.printStackTrace();
			}
		}else {
			try {
				hostAddress = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩
			serverSocket.bind( new InetSocketAddress(hostAddress, PORT) );
			consoleLog("Wait Connection " + hostAddress + ":" + PORT);
			
			// 3. 요청 대기
			while(true) {
				Socket socket = serverSocket.accept();
				consoleLog("Connection Accept"); 
				new ChatServerProcessThread(socket, listWriters).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if( serverSocket != null && !serverSocket.isClosed() ) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void consoleLog(String log) {
		System.out.println("[server " + Thread.currentThread().getId() + "] " + log);
	}
}
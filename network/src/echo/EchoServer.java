package echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		final int SERVER_PORT = 5001;	
		try {
			// 1. Create ServerSocket
			serverSocket = new ServerSocket();
			
			// 2. Binding
			//  : Socket -> SocketAddress(IPAddress + Port)
			InetAddress inetAddress = InetAddress.getLocalHost();
			serverSocket.bind( new InetSocketAddress("0.0.0.0", SERVER_PORT));
			log("server starts...[port:" + SERVER_PORT + "]");
			// 3. Accept
			//  : Waiting for connecting by Client
			//    Blocking Line...
			
			while(true) {
				Socket socket = serverSocket.accept();
				Thread thread = new EchoServerReceiveThread(socket);
				thread.start();	
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if( serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log(String log) {
		System.out.println("[server#" + Thread.currentThread().getId() + "]" + log);
	}
}

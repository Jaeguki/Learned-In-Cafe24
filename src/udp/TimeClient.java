package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class TimeClient {
	private static final String SERVER_IP = "127.0.0.1";

	public static void main(String[] args) {
		// 1. Create Socket
		Scanner scanner = null;
		DatagramSocket socket = null;
		try {
			// 1. Create Scanner
			scanner = new Scanner(System.in);
			
			// 2. Create Socket
			socket = new DatagramSocket();

			while(true) {
				// 5. Input Keyboard
				System.out.print(">");
				String line = scanner.nextLine();
				if("quit".equals(line)) {
					break;
				}
				
				// 6. Write Data
				byte[] sendData = line.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, new InetSocketAddress(SERVER_IP, UDPEchoServer.SERVER_PORT));
				
				socket.send(sendPacket);
			

				
				DatagramPacket receivePacket = new DatagramPacket(new byte[UDPEchoServer.BUFFER_SIZE], UDPEchoServer.BUFFER_SIZE);
				socket.receive(receivePacket);
				
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(data, 0, length, "UTF-8");
				
				
				// 7. Read Data
				// when closed server

				
				// 8. Print Console
				System.out.println("<" + message);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(scanner != null) {
				scanner.close();
			}
			if(socket != null && socket.isClosed() == false) {
				socket.close();				
			}
		
		}
	}
	
	public static void log(String log) {
		System.out.println("[client]"+ log);
	}
}

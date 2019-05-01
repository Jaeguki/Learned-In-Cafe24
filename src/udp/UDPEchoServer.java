package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {
	public static final int SERVER_PORT = 5000;
	public static final int BUFFER_SIZE = 1024;
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		try {
			// 1. Create Socket
			socket = new DatagramSocket(SERVER_PORT);
			
			while(true) {
				// 2. Receive Data
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);
				
				byte[] data = receivePacket.getData();
				int length = receivePacket.getLength();
				String message = new String(data, 0, length, "UTF-8");
				
				System.out.println("[server] received:" + message);
				
				byte[] sendData = message.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				
				socket.send(sendPacket);
			
			}	
		}catch(SocketException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
		
	}
}

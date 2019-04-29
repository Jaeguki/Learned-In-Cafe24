package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	private static final String SERVER_IP = "192.168.1.30";
	private static final int SERVER_PORT = 5001;
	
	public static void main(String[] args) {
		// 1. Create Socket
		Socket socket = new Socket();
		try {
	
			
			
			// 2. Server Connection
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[client] connected");
			
			// 3. Receive IOStream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 4. Write
			//   : 경계가 없기 때문에 응용프로그램단에서 개행문자를 넣어줘야한다.
			String data = "Hello World\n";
			os.write(data.getBytes("utf-8"));
			
			// 5. Read
			byte[] buffer = new byte[256];
			
			int readByteCount = is.read(buffer); // Block
			if(readByteCount == -1) {
				System.out.println("[client closed by server");
			}
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received:"+data);
			
			// 6. Write data
			os.write(data.getBytes("utf-8"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();				
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		}
	}
}

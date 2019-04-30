package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPClient {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 5001;
	
	public static void main(String[] args) {
		// 1. Create Socket
		Socket socket = null;
		try {
			socket = new Socket();
			// 1-1. Check Socket Buffer Size
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println(receiveBufferSize + ":" + sendBufferSize);
			
			// 1-2. SEt Socket Buffer Size
			socket.setReceiveBufferSize(1024*10);
			socket.setSendBufferSize(1024*10);
			System.out.println(socket.getReceiveBufferSize() + ":" + socket.getSendBufferSize());
			
			// 1-3. do not use Nagle Algorithm
			socket.setTcpNoDelay(false);
			
			// 1-4. set Timeout
			socket.setSoTimeout(2000);
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
		
		}catch(SocketTimeoutException e) {
			System.out.println("[client] time out");
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

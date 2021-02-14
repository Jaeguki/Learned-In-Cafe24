package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final String SERVER_IP = "192.168.1.30";
	private static final int SERVER_PORT = 5001;
	public static void main(String[] args) {
		// 1. Create Socket
		Scanner scanner = null;
		Socket socket = null;
		try {
			// 1. Create Scanner
			scanner = new Scanner(System.in);
			
			// 2. Create Socket
			socket = new Socket();

			
			// 3. Server Connection
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			log("connected");

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			while(true) {
				// 5. Input Keyboard
				System.out.print(">");
				String line = scanner.nextLine();
				if("quit".equals(line)) {
					break;

				}
				
				// 6. Write Data
				pw.println(line);
				
				// 7. Read Data
				// when closed server
				String data = br.readLine();
				if(data == null) {
					log("closed by server");
				}
				
				// 8. Print Console
				System.out.println("<" + data);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(scanner != null) {
					scanner.close();
				}
				if(socket != null && socket.isClosed() == false) {
					socket.close();				
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	public static void log(String log) {
		System.out.println("[client]"+ log);
	}
}

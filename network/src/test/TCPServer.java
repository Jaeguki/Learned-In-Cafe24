package test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. ServerSocket Make
			serverSocket = new ServerSocket();
			
			// 1-1. Assign Port Time-wait
			serverSocket.setReuseAddress(true);
			
			
			// 2. Binding
			//  : Socket -> SocketAddress(IPAddress + Port)
			InetAddress inetAddress = InetAddress.getLocalHost();
			// String localhost = inetAddress.getHostAddress();
			// serverSocket.bind( new InetSocketAddress(localhost, 5000));
			serverSocket.bind( new InetSocketAddress("0.0.0.0", 5000));

			// 3. Accept
			//  : Waiting for connecting by Client
			//    Blocking Line...
			Socket socket = serverSocket.accept();
			
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetRemoteSocketAddress.getPort();
			
			System.out.println("[server] connected by client [" + remoteHostAddress + ":" + remoteHostPort + "]");
			System.out.println();
			System.out.println();
			
			try {
				// 4. Receive IOStream
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				try {
					while(true) {
						
						// 5. Read Data
						// : Internet use byte data
						byte[] buffer = new byte[256];
						int readByteCount = is.read(buffer);
						if(readByteCount == -1) {
							// Correct End
							//  : Call close() Method by Client
							System.out.println("[server] closed by client ");
							break;
						}
						
						String data = new String(buffer, 0, readByteCount, "utf-8");
						System.out.println("[server] client says > " + data);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						os.write(data.getBytes("utf-8"));
					}
				}catch(SocketException e) {
					e.printStackTrace();
					System.out.println(e + " [server] sudden close");
				}catch(IOException e) {
					e.printStackTrace();				
				}finally {
					try {
						if(socket != null && !socket.isClosed()) {
							socket.close();
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}catch(IOException e) {
				e.printStackTrace();
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
}

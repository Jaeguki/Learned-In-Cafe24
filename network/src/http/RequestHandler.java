package http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	
	private static String documentRoot = "";
	
	static {
		try {
			documentRoot = new File(RequestHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
			documentRoot += "/webapp";
			System.out.println("--->" + documentRoot);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Socket socket;
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = ( InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog( "connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort() );

			// get IOStream
			OutputStream os = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				// if browser disconnect
				if(line == null) {
					break;
				}
				
				// Readonly Request Header
				if("".equals(line)) {
					break;
				}
				
				// Readonly First Line
				if(request == null) {
					request = line;
				}
				
				// consoleLog("Received:" + line);
			}
			
			consoleLog("Received:" + request);
			String[] tokens = request.split(" ");
			if("GET".contentEquals(tokens[0])) {
				consoleLog("request:" + tokens[0]);
				responseStaticResource(os, tokens[1], tokens[2]);
			}
			// Ignore Method(Like Post, Put, Delete, Head, Connect...)
			else {
				response400Error(os, tokens[2]);
				consoleLog("Bad Request:" + tokens[1]);
			}
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			// os.write( "HTTP/1.1 200 OK\r\n".getBytes( "UTF-8" ) );
			//os.write( "Content-Type:text/html; charset=utf-8\r\n".getBytes( "UTF-8" ) );
			// os.write( "\r\n".getBytes() );
			// 개행문자를 통한 헤더 분석
			// os.write( "<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes( "UTF-8" ) );

		} catch( Exception ex ) {
			consoleLog( "error:" + ex );
		} finally {
			// clean-up
			try{
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
				
			} catch( IOException ex ) {
				consoleLog( "error:" + ex );
			}
		}			
	}

	private void responseStaticResource(OutputStream os, String url, String protocol) throws IOException{
		
		if("/".equals(url)) {
			url = "/index.html";
		}
		File file = new File(documentRoot + url);
		if(file.exists() == false) {
			/*
			 * HTTP/1.1 404 File Not Found \r\n
			 * Content-Type:text/html; charset=utf=8\r\n
			 * 	\r\n
			 * error HTML DOC
			 */
			response404Error(os, protocol);
			return;
		}

		// nio
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		// response
		os.write( (protocol +" 200 OK\r\n").getBytes( "UTF-8" ) );
		os.write( (contentType  + "; charset=utf-8\r\n").getBytes( "UTF-8" ) );
		os.write( "\r\n".getBytes() );
		os.write( body );

	
	}

	private void response404Error(OutputStream os, String protocol) throws IOException{
		File file = new File(documentRoot + "/error/404.html"); 
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		// response
		os.write( (protocol +" 404 Not Found\r\n").getBytes( "UTF-8" ) );
		os.write( (contentType  + "; charset=utf-8\r\n").getBytes( "UTF-8" ) );
		os.write( "\r\n".getBytes() );
		os.write( body );
		consoleLog("404 Not Found");
	}
	
	private void response400Error(OutputStream os, String protocol) throws IOException{
		File file = new File(documentRoot + "/error/400.html"); 
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath());
		// response
		os.write( (protocol +" 400 Bad Request\r\n").getBytes( "UTF-8" ) );
		os.write( (contentType  + "; charset=utf-8\r\n").getBytes( "UTF-8" ) );
		os.write( "\r\n".getBytes() );
		os.write( body );
		consoleLog("400 Bad Request");
	}

	public void consoleLog( String message ) {
		System.out.println( "[RequestHandler#" + getId() + "] " + message );
	}
}

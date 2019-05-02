package com.cafe24.network.chat.server;

import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Base64;

public class ChatServerProcessThread extends Thread{
	private String nickname = null;
	private Socket socket = null;
	List<PrintWriter> listWriters = null;

	public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader buffereedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
			
			while(true) {
				String request = buffereedReader.readLine();
				
				if( request == null) {
					doQuit(printWriter);
					break;
				}
				consoleLog(request);
				String[] tokens = request.split(":");
				if("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
				}
				else if("message".equals(tokens[0])) {
					if(tokens.length == 1) {}
					else {
						
						// 사용자 입력에서 프로토콜 구분자 사용을 막기위한 Base64 Encoding을
						// Decoding 해주는 작업
						String message =new String(Base64.getDecoder().decode(tokens[1]));
						doMessage(message);
					}
				}
				else if("quit".equals(tokens[0])) {
					doQuit(printWriter);
				}
			}
		}
		catch(IOException e) {
			consoleLog(this.nickname + "님이 채팅방을 나갔습니다.");
		}
	}
	
	private void doQuit(PrintWriter writer) {
		removeWriter(writer);
		String data = this.nickname + "님이 퇴장했습니다.";
		broadcast(data);
	}

	private void removeWriter(PrintWriter writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}

	private void doMessage(String data) {
		broadcast(this.nickname + ":" + data);
	}

	private void doJoin(String nickname, PrintWriter writer) {
		this.nickname = nickname;
		String data = nickname + "님이 입장하였습니다.";
		System.out.println(data);
		broadcast(data);
		
		// writer pool에 저장
		addWriter(writer);
	}

	private void addWriter(PrintWriter writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for(PrintWriter writer : listWriters) {
				writer.println(data);
				writer.flush();
			}
		}
	}

	private void consoleLog(String log) {
		System.out.println(log);
	}
}
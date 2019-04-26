package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardTest {
	public static void main(String args[]) {
		BufferedReader br = null;
		try {
			// FrameworkStream (standard input, keyboard, System.in)
			
			// SupportStream
			// byte | byte | byte -> char
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");
			
			// SupportStream2
			// char1 | char2 | char3 | \n -> "char1char2char3"
			br = new BufferedReader(isr);
			
			// read
			String line = null;
			while( (line = br.readLine()) != null ) {
				if(line.equals("exit"))
					break;
				System.out.println(">>" + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
}

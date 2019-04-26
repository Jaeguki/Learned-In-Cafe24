package util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		InetAddress inetAddress = null;
		InetAddress[] inetAddresses = null;
		System.out.println("if you want program end");
		System.out.println("typing : " + "exit");
		System.out.println();
		System.out.println("input address");
		while(true) {
			try {
				System.out.print(">");
				String targetAddress = new Scanner(System.in).next();
				if (targetAddress.equals("exit")) {
					System.out.println("..program end..");
					break;
				}
				inetAddresses = InetAddress.getAllByName(targetAddress);
			}catch(UnknownHostException e) {
				System.out.println("INVALID ADDRESS");
				System.out.println(e);
				System.out.println();
				System.out.println("if you want program end");
				System.out.println("typing : " + "quit");
				System.out.println();
				System.out.println("input address");
				continue;
			}
			for(InetAddress ia : inetAddresses) {
				if (ia instanceof Inet6Address) {
		            System.out.println(ia.getHostName() + " : " + ia.getHostAddress());
		        }else {
		        	System.out.println(ia.getHostName() + " : " + ia.getHostAddress());
		        }
			}
		}
	}
}

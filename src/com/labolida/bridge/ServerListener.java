package com.labolida.bridge;
import java.net.ServerSocket;
import java.net.Socket;

import com.labolida.startup.Context;

public class ServerListener {
	
	public ServerListener(){
		try {
			ServerSocket ss = new ServerSocket(Context.bridgeFromPORT);
			while(true){
				Socket socket = ss.accept();
				System.out.println("[ServerListener] received a request connection.");
				ServerAttend attend = new ServerAttend(socket);
				attend.start();
			}
		} 
		catch (Exception e) {
			System.out.println("error at " + this.getClass().getName() + " message:" + e.getMessage() );
		}
	}
}

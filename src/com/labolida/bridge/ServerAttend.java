package com.labolida.bridge;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.labolida.startup.Context;

public class ServerAttend extends Thread{

	private Socket socket = null;
	
	public ServerAttend(Socket socket){
		this.socket=socket;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("[ServerAttend] received a request.");

			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			Socket socket2 = new Socket( Context.bridgeToIP , Context.bridgeToPORT );
			OutputStream out2 = socket2.getOutputStream();
			InputStream in2 = socket2.getInputStream();
			
			//while(true) {
				byte buff[] = new byte[1024*8];
				int length = in.read(buff);
				byte buff2[] = getNewByteArray(buff,length);
				System.out.println("[ServerAttend]: data received: " + new String(buff2) );
				System.out.println("[ServerAttend] connecting to " + Context.bridgeToIP + ":" + Context.bridgeToPORT );
				System.out.println("[ServerAttend] sending data " + new String(buff2) );
				out2.write(buff2);
				out2.flush();
				sleep(20);
				length = in2.read(buff);
				buff2 = getNewByteArray(buff,length);
				System.out.println("[ServerAttend] received data " + new String(buff2) );
				System.out.println("[ServerAttend] and sending back to the sourcesocket."  );
				out.write(buff2); // sending response to the RequestSocket
				out.flush();
			//}
		}
		catch (Exception e) {
			System.out.println("error at " + this.getClass().getName()+ " message:" + e.getMessage());
		}
		super.run();
	}
	
	/**
	 * get a new byteArray (clone) resized 
	 */
	private byte[] getNewByteArray( byte arr[] , int length){
		byte buff[] = new byte[length];
		for (int i = 0; i < length; i++) {
			buff[i]=arr[i];
		}
		return buff;
	}
}
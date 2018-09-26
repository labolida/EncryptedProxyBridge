package com.labolida.bridge;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.labolida.security.Cryptography;
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
			
			
			// RECEIVE REQUEST DATA FROM PREVIOUS NODE
			byte data[] = new byte[1024*8];
			int length = in.read(data);
			data = getNewByteArray(data,length);
			System.out.println("[ServerAttend]: data received: " + new String(data) );
			
			if (Context.sideplay==1){
				data = Cryptography.encode(data, Context.KEY);
				System.out.println("Message encrypted!");
			}
			if (Context.sideplay==2){
				data = Cryptography.decode(data, Context.KEY);
				System.out.println("Message decrypted!");
			}
			
			// SEND REQUEST DATA TO THE NEXT NODE
			System.out.println("[ServerAttend] connecting to " + Context.bridgeToIP + ":" + Context.bridgeToPORT );
			System.out.println("[ServerAttend] sending data " + new String(data) );
			out2.write(data);
			out2.flush();
			//out2.close();
			sleep(20);
			
			// RECEIVE RESPONSE DATA FROM NEXT NODE
			data = new byte[1024*8];
			length = in2.read(data);
			data = getNewByteArray(data,length);
			System.out.println("[ServerAttend] received data " + new String(data) );
			
			if (Context.sideplay==2){
				data = Cryptography.encode(data, Context.KEY);
				System.out.println("Message encrypted!");
			}
			if (Context.sideplay==1){
				data = Cryptography.decode(data, Context.KEY);
				System.out.println("Message decrypted!");
			}
			
			// SEND RESPONSE TO THE PREVIOUS NODE
			System.out.println("[ServerAttend] and sending back to the sourcesocket."  );
			out.write(data); // sending response to the RequestSocket
			out.flush();
			Thread.sleep(200);
			out.close();
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
	
	
	
	
	private byte[] socketRead(Socket socket){
		try {
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuffer buff = new StringBuffer();
			String r;
			while ((r = reader.readLine()) != null){
				buff.append(r);
			}
			return new String(buff).getBytes();
		}
		catch (Exception e) {
			System.out.println("error at " + this.getClass().getName() + " message:"+ e.getMessage());
			return null;
		}
	}
	
	
}






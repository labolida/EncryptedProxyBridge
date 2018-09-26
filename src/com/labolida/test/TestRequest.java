package com.labolida.test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class TestRequest {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",963);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			Thread.sleep(20);
			
			out.write("GET /login.php HTTP/1.1\n\n".getBytes());
			out.write("\n\n".getBytes());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String something;

			  while ((something = reader.readLine()) != null){
			    	 System.out.println("" + something );
			  }
		}
		catch (Exception e) {
			System.out.println("error at " + TestRequest.class.getName()+ " message:" + e.getMessage());
		}
	}
}
/*
PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
*/
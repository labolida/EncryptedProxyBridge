package com.labolida.test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class TestRequest {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1",963);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			Thread.sleep(200);
			
			out.write("GET /login.php HTTP/1.1\n\n".getBytes());
			out.write("\n\n".getBytes());
			
			byte buff[] = new byte[1024*8];
			int length = in.read(buff);

			in.read( buff );
			
			System.out.println("TestRequest: received:" + new String(buff) );
			
		}
		catch (Exception e) {
			System.out.println("error at " + TestRequest.class.getName()+ " message:" + e.getMessage());
		}

	}

}

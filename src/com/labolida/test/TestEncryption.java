package com.labolida.test;

import com.labolida.security.Cryptography;

public class TestEncryption {

	public static void main(String[] args) {
		
		try {
			String message = "This is the secret message";
			String password = "leonardo.labolida.com";
			
			byte encMessage[] = Cryptography.encode(message.getBytes("US-ASCII"), password.getBytes("US-ASCII"));
			
			System.out.println( new String(encMessage) );
			
			byte textPlain[] = Cryptography.decode( encMessage, password.getBytes() );
			
			System.out.println( new String(textPlain) );
			
		} 
		catch (Exception e) {
			System.out.println("error at " + TestEncryption.class.getName()
					+ " message:" + e.getMessage());
		}
	}
}

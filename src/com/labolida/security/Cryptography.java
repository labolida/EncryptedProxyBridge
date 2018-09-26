package com.labolida.security;

public class Cryptography {
	public static byte[] encode( byte message[] , byte password[] ){
		return xcode(message , password , 0 );
	}
	public static byte[] decode( byte message[] , byte password[] ){
		return xcode(message , password , 1 );
	}
	private static byte[] xcode( byte message[] , byte password[] , int action ){
		byte buff[] = new byte[message.length];
		int c = 0;
		int p = 0;
		for (int i = 0; i < message.length; i++) {
			c = message[i];
			if (c<0) c=c & 0xFF;
			p = password[i%password.length];
			if (p<0) p= p & 0xFF;
			if (action==0) c=c+p; else c=c-p;
			buff[i]=(byte)c ;
		}
		return buff;	
	}
}

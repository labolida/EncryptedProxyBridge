package com.labolida.startup;

public class Context {

	public static int bridgeFromPORT = 0;
	
	public static String bridgeToIP = null;
	public static int bridgeToPORT = 0;
	

	/**
	 encryptionMode
		IF sideplay == 1 ENCODE messages
		IF sideplay == 2 DECODE messages
	*/
	public static int sideplay = 0 ; 
	//public static byte KEY[] = {11,22,33,44,55,66,77,88,99,127,-51};
	public static byte KEY[] = {11,22,33};
}

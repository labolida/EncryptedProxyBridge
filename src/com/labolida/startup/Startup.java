package com.labolida.startup;
import com.labolida.bridge.ServerListener;

public class Startup {
	public static void main(String[] args) {
		try {
			System.out.println("Starting up the EncryptedProxyBridge ");
			
			Context.bridgeFromPORT  = Integer.parseInt( args[0] );   // The local port of the listener
			Context.bridgeToIP      = args[1];                       // The remote address to connect
			Context.bridgeToPORT    = Integer.parseInt( args[2] );   // The remote port to connect
			
			System.out.println("bridgeFromPORT : " + Context.bridgeFromPORT );
			System.out.println("bridgeToIP : " + Context.bridgeToIP );
			System.out.println("bridgeToPORT : " + Context.bridgeToPORT );
			
			new ServerListener();
			
		}
		catch (Exception e) {
			System.out.println("error at " + Startup.class.getName()+ " message:" + e.getMessage());
		}
	}
}

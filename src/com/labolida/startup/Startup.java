package com.labolida.startup;
import com.labolida.bridge.ServerListener;

public class Startup {
	public static void main(String[] args) {
		try {
			System.out.println("Starting up the EncryptedProxyBridge ");
			
			Context.bridgeFromPORT  = Integer.parseInt( args[0] );   // The local port of the listener
			Context.bridgeToIP      = args[1];                       // The remote address to connect
			Context.bridgeToPORT    = Integer.parseInt( args[2] );   // The remote port to connect
			Context.sideplay        = Integer.parseInt( args[3] );   // node1 or node2
			
			System.out.println("bridgeFromPORT : " + Context.bridgeFromPORT );
			System.out.println("bridgeToIP : " + Context.bridgeToIP );
			System.out.println("bridgeToPORT : " + Context.bridgeToPORT );
			System.out.println("sideplay : " + Context.sideplay );
			
			new ServerListener();
		}
		catch (Exception e) {
			System.out.println("error at " + Startup.class.getName()+ " message:" + e.getMessage());
		}
	}
}

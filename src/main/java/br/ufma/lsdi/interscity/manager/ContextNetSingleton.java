package br.ufma.lsdi.interscity.manager;

public class ContextNetSingleton {
	private static ContextNet contextnet;
	
	public static void init(String ip, int port) {
		contextnet=new ContextNet(ip,port);
	}
	
	public static ContextNet get() {
		return contextnet;
	}
	
	public static boolean check() {
		return contextnet!=null&&contextnet.checkConnection();
	}
	
}

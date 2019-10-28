package br.ufma.lsdi.interscity.mamager;

public class SingletonManager {
	public static String URL = "http://cidadesinteligentes.lsdi.ufma.br/eq1";
	public static InterSCityManagerFactory manager;
	public static String url_connection;
	public static void init(String url) {
		if(manager==null||!url_connection.equals(url)) {
			url_connection=url;
			manager=new InterSCityManagerFactory(url_connection);
		}
	}
	
	public static void init() {
		init(URL);
	}
	public static InterSCityManagerFactory get() {
		return manager;
	}


}

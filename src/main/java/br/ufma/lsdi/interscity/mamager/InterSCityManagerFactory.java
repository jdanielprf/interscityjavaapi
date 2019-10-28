package br.ufma.lsdi.interscity.mamager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class InterSCityManagerFactory {

	private String url;
	private RestTemplate restTemplate ;
	private DataManager resourceData;
	private CapabilitiesManager capabilitiesManager;
	private ResourceManager resourceManager;
	
	
	
	public InterSCityManagerFactory(String url){
		this.url=url;
		restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
	}
	

		
	public ResourceManager resources() {
		if(resourceManager==null)
			resourceManager=new ResourceManager(restTemplate, url);
		return resourceManager;
	}
	
	public CapabilitiesManager capabilities() {
		if(capabilitiesManager==null)
			capabilitiesManager=new CapabilitiesManager(restTemplate, url);
		return capabilitiesManager;
	}

	public DataManager data() {
		if(resourceData==null)
			resourceData=new DataManager(restTemplate, url);
		return resourceData;
	}
}

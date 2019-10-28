

package br.ufma.lsdi.interscity.mamager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import br.ufma.lsdi.interscity.beans.Capability;
import br.ufma.lsdi.interscityl.beans.rest.ListCapabilities;

public class CapabilitiesManager {
	public static final String TYPE_SENSOR="sensor";
	public static final String TYPE_ACTUATOR="actuator";
	private String url ;
	private RestTemplate restTemplate ;
	
	CapabilitiesManager(RestTemplate restTemplate,String url	){
		
		this.restTemplate=restTemplate;
		this.url=url;
	}
	
	
	public Capability[] getAll() {
		ListCapabilities resp=restTemplate.getForObject(url+"/catalog/capabilities/",ListCapabilities.class);

		return resp.getCapabilities();
	}
	public Capability getCapability(String name) {
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", name);
	
		Capability resp=restTemplate.getForObject(url+"/catalog/capabilities/{name}",Capability.class, params);
		
		return resp;
		
	}
	
	public Capability createCapability(String name,String description,String type) {
		Map<String, String> data=new HashMap<String, String>();
		data.put("name", name);
		data.put("description", description);
		data.put("capability_type", type);
		
		Capability resp=restTemplate.postForObject(url+"/catalog/capabilities/", data, Capability.class);
		return resp;
	}

	public void dateleCapability(String name) {
		Map<String, String> data=new HashMap<String, String>();
		data.put("name", name);
		restTemplate.delete(url+"/catalog/capabilities/{name}", data);
	}
	
	public void updateCapability(String name,String description,String type) {
		Map<String, String> params=new HashMap<String, String>();
		params.put("name", name);
		
		Map<String, String> data=new HashMap<String, String>();
		data.put("name", name);
		data.put("description", description);
		data.put("capability_type", type);
		
		restTemplate.put(url+"/catalog/capabilities/{name}", data, params);
	}


}

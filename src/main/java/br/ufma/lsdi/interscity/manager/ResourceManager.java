package br.ufma.lsdi.interscity.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import br.ufma.lsdi.interscity.beans.Resource;
import br.ufma.lsdi.interscityl.beans.rest.GetResources;
import br.ufma.lsdi.interscityl.beans.rest.ListResources;

public class ResourceManager {
	public static final String STATUS_INACTIVED="inactive"; 
	public static final String STATUS_ACTIVE="active"; 
	private String url ;
	private RestTemplate restTemplate ;
	
	ResourceManager(RestTemplate restTemplate,String url	){
		
		this.restTemplate=restTemplate;
		this.url=url;
	}
	
	
	
	public Resource[] getAll() {
		ListResources resources = restTemplate.getForObject(
				url+"/catalog/resources/", ListResources.class);
		return resources.getResources();
	}

	public Resource[] getAllResouceWithSensors() {
		ListResources resources = restTemplate.getForObject(
				url+"/catalog/resources/sensors", ListResources.class);
		return resources.getResources();
	}

	public Resource[] getAllResouceWithActuators() {
		ListResources resources = restTemplate.getForObject(
				url+"/catalog/resources/actuators", ListResources.class);
		return resources.getResources();
	}
	
	public Resource getResource(String uuid) {
		GetResources resposta = restTemplate.getForObject(url+"/catalog/resources/"+uuid, GetResources.class);
		return resposta.getData();
	}

	public Resource[] findResourceActive(String key,String value) {
		ListResources resources = restTemplate.getForObject(
				url+"/catalog/resources/search?"+key+"="+value, ListResources.class);
		return resources.getResources();
	}
	public Resource[] findResourceActiveByDescription(String description) {
		return findResourceActive("description", description);
	}

	public Resource createResource(Resource r) {
		GetResources request=new GetResources();
		request.setData(r);
		GetResources resposta = restTemplate.postForObject(url+"/catalog/resources/", request, GetResources.class);
		return resposta.getData();
	}
	
	public void updateResource(Resource r) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("uuid",r.getUuid());
		r.setUuid(null);
		GetResources request=new GetResources();
		request.setData(r);
		restTemplate.put(url+"/adaptor/resources/{uuid}", request, param);
	//	restTemplate.exchange(url+"/adaptor/resources/{uuid}",HttpMethod.PUT, r,GetResources.class,param);
	}
	
	public void createOrUpdateResource(Resource r) {
		if(r.getUuid()==null||r.getUuid().equals("")) {
			createResource(r);
		}else {
			updateResource(r);
		}
	}
}

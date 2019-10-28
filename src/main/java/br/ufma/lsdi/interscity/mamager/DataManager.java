package br.ufma.lsdi.interscity.mamager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufma.lsdi.interscity.beans.CapabilityValue;
import br.ufma.lsdi.interscityl.beans.rest.GetResources;
import br.ufma.lsdi.interscityl.beans.rest.SendData;
import br.ufma.lsdi.interscityl.beans.rest.SendaDataValues;

public class DataManager {
	private String url ;
	private RestTemplate restTemplate ;
	DataManager(RestTemplate restTemplate,String url	){
		
		this.restTemplate=restTemplate;
		this.url=url;
	}
	
	public void sendDataCapability(String uuid,List<CapabilityValue> dados) {
		Object list[]=new Object[dados.size()];
		int i=0;
		for (Iterator<CapabilityValue> iterator = dados.iterator(); iterator.hasNext();) {
			CapabilityValue capabilityValue = (CapabilityValue) iterator.next();
			HashMap<String, Object> val=new HashMap<String, Object>();
			val.put(capabilityValue.getName(),capabilityValue.getValue());
			val.put("date",capabilityValue.getTimeStamp());
			list[i++]=val;
		}
		
		SendaDataValues values=new SendaDataValues();
		values.setData(list);
		SendData request=new SendData();
		request.setData(values);
		GetResources resposta = restTemplate.postForObject(url+"/adaptor/resources/"+uuid+"/data/capability", request, GetResources.class);
		
	}



	public void sendDataResource(String uuid,List<CapabilityValue> dados) {
		Object list[]=new Object[dados.size()];
		int i=0;
		for (Iterator<CapabilityValue> iterator = dados.iterator(); iterator.hasNext();) {
			CapabilityValue capabilityValue = (CapabilityValue) iterator.next();
			HashMap<String, Object> val=new HashMap<String, Object>();
			val.put(capabilityValue.getName(),capabilityValue.getValue());
			val.put("timestamp",capabilityValue.getTimeStamp());
			list[i++]=val;
		}
		
		SendaDataValues values=new SendaDataValues();
		values.setData(list);
		SendData request=new SendData();
		request.setData(values);
		GetResources resposta = restTemplate.postForObject(url+"/adaptor/resources/"+uuid+"/data", request, GetResources.class);
	}
	
	public List<CapabilityValue> getData(String uuid) {
		return getData(uuid, "data");
	}

	public List<CapabilityValue> getData(String uuid,String description) {
		ResponseEntity<String> response = restTemplate.getForEntity(
				url+"/collector/resources/"+uuid+"/data", String.class);
		List<CapabilityValue> list=new ArrayList<CapabilityValue>();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		try {
			root = mapper.readTree(response.getBody());
		} catch (IOException e) {
			return list;
		}
		JsonNode jsonResources = root.findPath(description);

		Iterator<JsonNode> elems = jsonResources.elements();
				
		while(elems.hasNext()) {			
			JsonNode cap = elems.next();

			for (Iterator<Entry<String, JsonNode>> iterator = cap.fields(); iterator.hasNext();) {
				Entry<String, JsonNode> capabilityValue = (Entry<String, JsonNode>) iterator.next();
			//	System.out.println(">>"+capabilityValue.getKey()+":"+capabilityValue.getValue());
				if(capabilityValue.getKey().equals("date")) {
					continue;
				}
				CapabilityValue value=new CapabilityValue();
				value.setName(capabilityValue.getKey());
				value.setValue(capabilityValue.getValue());
				value.setTimeStamp(cap.get("date").toString());
				list.add(value);
			}

		}
		
		return list;
	}
}

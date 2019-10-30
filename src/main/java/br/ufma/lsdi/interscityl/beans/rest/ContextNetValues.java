package br.ufma.lsdi.interscityl.beans.rest;

import java.io.Serializable;
import java.util.List;

import br.ufma.lsdi.interscity.beans.CapabilityValue;

public class ContextNetValues implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String uuidData;
	private String description;
	private List<CapabilityValue> list;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CapabilityValue> getValues() {
		return list;
	}
	public void setValues(List<CapabilityValue> list) {
		this.list = list;
	}
	public String getUuidData() {
		return uuidData;
	}
	public void setUuidData(String uuidData) {
		this.uuidData = uuidData;
	}
	
	
}

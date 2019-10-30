package br.ufma.lsdi.interscity.beans;

import java.io.Serializable;

public class CapabilityValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Object value;
	private String timeStamp;
	
	@Override
	public String toString() {
		return "name:"+name+" value:"+value+" date:"+timeStamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}

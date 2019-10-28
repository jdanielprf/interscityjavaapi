package br.ufma.lsdi.interscityl.beans.rest;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.ufma.lsdi.interscity.beans.Capability;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCapabilities implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Capability capabilities[];
	public ListCapabilities(){
		
	}
	public Capability[] getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(Capability capabilities[]) {
		this.capabilities = capabilities;
	}
	

}

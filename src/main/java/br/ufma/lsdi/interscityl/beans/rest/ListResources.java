package br.ufma.lsdi.interscityl.beans.rest;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.ufma.lsdi.interscity.beans.Resource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResources implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Resource resources[];
	public ListResources(){
		
	}
	public Resource[] getResources() {
		return resources;
	}

	public void setResources(Resource[] resources) {
		this.resources = resources;
	} 
}

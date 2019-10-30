package br.ufma.lsdi.interscityl.beans.rest;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.ufma.lsdi.interscity.beans.Resource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResources implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Resource data;
	public GetResources(){
		
	}
	public Resource getData() {
		return data;
	}
	public void setData(Resource data) {
		this.data = data;
	}

}

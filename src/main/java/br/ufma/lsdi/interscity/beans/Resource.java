package br.ufma.lsdi.interscity.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private Double lat;
	private Double lon;
	private String description;
	private String capabilities[];
	private String status = "active";

	@Override
	public String toString() {
		return "uuid:" + uuid + "description:" + description;
	}

	public Resource() {

	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String[] capabilities) {
		this.capabilities = capabilities;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

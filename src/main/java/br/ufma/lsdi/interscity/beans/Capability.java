package br.ufma.lsdi.interscity.beans;

public class Capability {
	
	public static String CAPABILITY_TYPE_SENSOR="sensor";
	public static String CAPABILITY_TYPE_ACTUATOR="actuator";
	
	private String id;
	private String name;
	private String description;
	private String type;
	
	@Override
	public String toString() {
		return name+""+type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

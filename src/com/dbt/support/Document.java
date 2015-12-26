package com.dbt.support;

public class Document {
	int id;
	String url;
	String name;
	int entityId;
	String type;
	
	public Document(int id, String url, String name, int entityId, String type) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.entityId = entityId;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

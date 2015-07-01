package com.dbt.data;

import java.util.ArrayList;
import java.util.List;

public class Privilege 
{
	public List<Privilege> subprivs;
	String name;
	String path;
	int id;
	String iconClass;
	String userType;
	
	public Privilege()
	{
		subprivs = new ArrayList<Privilege>();
		name = "";
		path = "";
		id = 0;
		userType = "";
		iconClass = "";
	}
	
	
	public Privilege(List<Privilege> subprivs, String name, String path,
			int id, String userType,String icon) {
	
		this.subprivs = subprivs;
		this.name = name;
		this.path = path;
		this.id = id;
		this.userType = userType;
		this.iconClass = icon;
	}

	
	public String getIconClass()
	{
		return this.iconClass;
	}

	
	public void setIconClass(String iconClass)
	{
		this.iconClass = iconClass;
	}
	
	public List<Privilege> getSubprivs() {
		return subprivs;
	}
	public void setSubprivs(List<Privilege> subprivs) {
		this.subprivs = subprivs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}

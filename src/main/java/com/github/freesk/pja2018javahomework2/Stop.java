package com.github.freesk.pja2018javahomework2;

import java.util.UUID;

public class Stop {
	
//	Since a user cannot create a new instance of this class 
//	there is no need to write advanced parameter validation 

	final public String id = UUID.randomUUID().toString();
	
	private String name = "";
	private int type = 0;
	
	public Stop() {}
	
	public Stop(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "{\n" + 
				"  id  : " + id + "\n" +
				"  name: " + name + "\n" + 
				"  type: " + type + "\n" +
				"}";				
				
	}
}

package com.mywebsite.mymaventest.MyMavenTest;

import java.util.UUID;

public class Stop {

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
		String typeStr = "";
		
		if (type == 1)
			typeStr = "BUS";
		else if (type == 2)
			typeStr = "TRAIN";
		
		return this.name + " (" + typeStr + ")";
	}
	
	@Override
	public String toString() {
		return "{\n" + 
				"  id  : " + id + ";\n" +
				"  name: " + name + ";\n" + 
				"  type: " + type + ";\n" +
				"},";				
				
	}
}

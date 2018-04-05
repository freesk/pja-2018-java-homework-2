package com.mywebsite.mymaventest.MyMavenTest;

import java.util.ArrayList;

public class Route {

	public ArrayList<Stop> stops = new ArrayList<Stop>();
	private int type = 0;
	private String name = "";
	
	public Route() {}
	
	public Route(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public Route(int type, String name, ArrayList<Stop> stops) {
		this(type, name);
		this.stops = stops;
	}
	
	public void unassingStops() {
		stops = new ArrayList<Stop>();
	}
		
	public void assingStops(ArrayList<Stop> stops) {
		unassingStops();
		this.stops = stops;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
 	
	public int getNumberOfStops() {
		return stops.size();
	}
	
	public int getType() {
		return type;
	}
	
	public boolean hasStop(Stop stop) {
		int index = stops.indexOf(stop);
		return index > -1;
	}
	
	public void addStop(Stop stop) {
		if (stop.getType() != this.type) 
			throw new RuntimeException("[Error] cannot add " + stop.getName() + " to the route because of its type");
		
		stops.add(stop);
	}
	
	public void removeStop(Stop stop) {
		int index = stops.indexOf(stop);
		if (index < 0)
			throw new RuntimeException("[Error] " + stop.getName() + "does not exist in the route");
		
		stops.remove(index);
	}
	
}

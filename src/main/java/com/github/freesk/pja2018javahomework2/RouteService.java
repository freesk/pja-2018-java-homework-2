package com.github.freesk.pja2018javahomework2;

import java.io.IOException;
import java.util.ArrayList;

public class RouteService {
	
	final static int maxLength = 30;
	
	public static ArrayList<Route> routes = new ArrayList<Route>();

	public RouteService() {}
	
	public static boolean hasRoute(String name) {
		for(Route r : routes)
			if (r.getName().equals(name)) return true;
		
		return false;
	} 
	
	public static String parseName(String string) throws IOException {
		if (string == null) 
			throw new IOException("[Error] cannot be null");
		else if (string.isEmpty())
    		throw new IOException("[Error] cannot be empty");
    	else if (string.length() > maxLength)
    		throw new IOException("[Error] cannot exceed 30 symobls");
    	else if (RouteService.hasRoute(string))
    		throw new IOException("[Error] route " + string + " already exists");
		
		return string;
	}
	
	public static int parseType(int n) throws IOException {
        if (n != 1 && n != 2)
        	throw new IOException("[Error] only 1 or 2");
        return n;
	}
	
	public static void addRoute(Route r) {
		if (r == null)
			System.out.println("[Warning] cannot add null");
		else 
			routes.add(r);
	}
	
	public static ArrayList<Route> getRoutesByStop(Stop stop) {
		ArrayList<Route> res = new ArrayList<Route>();
		
		for(Route r : routes)
			if (r.hasStop(stop)) res.add(r);
		
		return res;
	}
	
	public static Route getRoutById(int n) {
		return routes.get(n);
	}
	
	public static String getRouteType(Route r) {
		if (r.getType() == 1)
			return "BUS";
		else if (r.getType() == 2) 
			return "TRAIN";
		else 
			return "undefined";
	}
	
	public static void removeRoute(Route r) {
		int index = routes.indexOf(r);
		
		if (index < 0)
			System.out.println("[Warning] route " + r.getName() + " is unknown");
		else 
			routes.remove(index);
	}

}

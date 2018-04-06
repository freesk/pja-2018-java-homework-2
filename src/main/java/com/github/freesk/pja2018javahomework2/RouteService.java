package com.github.freesk.pja2018javahomework2;

import java.util.ArrayList;

public class RouteService {
	
	public static ArrayList<Route> routes = new ArrayList<Route>();

	public RouteService() {}
	
	public static boolean hasRoute(String name) {
		for(Route r : routes)
			if (r.getName().equals(name)) return true;
		
		return false;
	} 
	
	public static void addRoute(Route r) {
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

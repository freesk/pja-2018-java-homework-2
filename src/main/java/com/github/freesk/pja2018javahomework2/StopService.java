package com.github.freesk.pja2018javahomework2;

import java.util.ArrayList;

public class StopService {

	public StopService() {}
	
    public static ArrayList<Stop> getStopsByType(ArrayList<Stop> stops, int type) {
    	ArrayList<Stop> list = new ArrayList<Stop>();
    	
    	for (Stop s : stops)
    		if (s.getType() == 0 || s.getType() == type) list.add(s);
    		
    	return list;
    }
    
	public static String getStopType(Stop s) {
		if (s.getType() == 0)
			return "BUS & TRAIN";
		else if (s.getType() == 1)
			return "BUS";
		else if (s.getType() == 2) 
			return "TRAIN";
		else 
			return "undefined";
	}

}

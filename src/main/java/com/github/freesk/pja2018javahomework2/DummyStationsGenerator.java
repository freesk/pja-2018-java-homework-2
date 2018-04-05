package com.mywebsite.mymaventest.MyMavenTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

public class DummyStationsGenerator {

	public DummyStationsGenerator() {}
	
    public static ArrayList<Stop> getData(int maxNumberOfStopsPerJoint,
    									  int numberOfJoints, 
    									  int numberOfBusStops,
    									  int numberOfTrainStops) {
    	
        Faker faker = new Faker();
        
    	ArrayList<Stop> stops = new ArrayList<Stop>();
    	
    	int i, j;
    	
        for (i = 0; i < numberOfBusStops; i++) {    	
        	String streetName = faker.address().streetName() ;
        	Stop stop = new Stop(streetName, 1);
        	stops.add(stop);
        }
        
        for (i = 0; i < numberOfTrainStops; i++) {    	
        	String streetName = faker.address().streetName() ;
        	Stop stop = new Stop(streetName, 2);
        	stops.add(stop);
        }
        
        Collections.shuffle(stops);
        
        ArrayList<Stop> joinedStops = new ArrayList<Stop>();
        
        for (i = 0; i < numberOfJoints; i++) {
        	int n = getRandomInt(2, maxNumberOfStopsPerJoint);
        	JoinedStop joint = new JoinedStop();
        	for (j = 0; j < n; j++) {
        		joint.addStop(stops.get(j));
        		stops.remove(j);
        	}
        	joinedStops.add(joint);
        }
        
        for (i = 0; i < numberOfJoints; i++)
        	stops.add(joinedStops.get(i));
        
        Collections.shuffle(stops);
        
        return stops;
           	
    }
    
    public static int getRandomInt(int min, int max) {
    	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}

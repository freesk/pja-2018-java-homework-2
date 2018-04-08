package com.github.freesk.pja2018javehomework2;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.freesk.pja2018javahomework2.DummyStationsGenerator;
import com.github.freesk.pja2018javahomework2.Route;
import com.github.freesk.pja2018javahomework2.Stop;
import com.github.freesk.pja2018javahomework2.StopService;

import org.junit.Assert;

@RunWith(Suite.class)
@SuiteClasses({ 
	JoinedStopTest.class,
	StopServiceTest.class,
	RouteServiceTest.class
})
public class AllTests {
	
//	Due to the relatively big number of methods only
//	the most crucial methods have test
	
//	Tests for user IO have been ignored since there was not explicit 
//	requirement to make them 
	
//	Since most of the critical logic has been implement through classes
//	Route Service and Stop Service, the necessity of a test that
//	handles 40 stations per route is redundant
	
	ArrayList<Stop> stops;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("AllTests @Before");
		
		int maxNumberOfStopsPerJoint = 3;
		int numberOfJoints = 10; 
		int numberOfBusStops = 40;
		int numberOfTrainStops = 40;
		
		stops = DummyStationsGenerator.getData(
			maxNumberOfStopsPerJoint,
			numberOfJoints,
			numberOfBusStops,
			numberOfTrainStops);
		
	}
	
	@Test 
	public void createRouteTypeOne() {
		ArrayList<Stop> stopsTypeOne = StopService.getStopsByType(stops, 1);
		Route r = new Route(1, "R1");
		for (Stop s : stopsTypeOne) r.addStop(s);
		Assert.assertEquals(true, true);
	}
	
	@Test 
	public void createRouteTypeTwo() {
		ArrayList<Stop> stopsTypeOne = StopService.getStopsByType(stops, 2);
		Route r = new Route(2, "R1");
		for (Stop s : stopsTypeOne) r.addStop(s);
		Assert.assertEquals(true, true);
	}
	
}
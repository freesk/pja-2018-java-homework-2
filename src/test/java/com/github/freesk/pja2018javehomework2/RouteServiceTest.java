package com.github.freesk.pja2018javehomework2;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.github.freesk.pja2018javahomework2.Route;
import com.github.freesk.pja2018javahomework2.RouteService;
import com.github.freesk.pja2018javahomework2.Stop;

public class RouteServiceTest {

	@Test
	public void hasRouteTest() {
		Route r = new Route(1, "R1");
		RouteService.addRoute(r);
		boolean answer = RouteService.hasRoute("R1");
		Assert.assertEquals(true, answer);
	}
	
	@Test 
	public void removeRouteTest() {
		Route r = new Route(1, "R1");
		RouteService.addRoute(r);
		RouteService.removeRoute(r);
		boolean answer = RouteService.hasRoute("R1");
		Assert.assertEquals(false, answer);
	}
	
	@Test 
	public void getRoutesByStopTest() {
		Stop s = new Stop("test-stop-1", 1);
		Route r = new Route(1, "R1");
		r.addStop(s);
		RouteService.addRoute(r);
		ArrayList<Route> list = RouteService.getRoutesByStop(s);
		Assert.assertEquals(1, list.size());
	}
	
	@Test
	public void getRouteTypeOneTest() {
		Route r = new Route(1, "R1");
		String type = RouteService.getRouteType(r);
		Assert.assertEquals("BUS", type);
	}
	
	@Test
	public void getRouteTypeTwoTest() {
		Route r = new Route(2, "R1");
		String type = RouteService.getRouteType(r);
		Assert.assertEquals("TRAIN", type);
	}
	
	@Test(expected = IOException.class)
	public void paraseNameWithNull() throws IOException {
		String string = null;
		RouteService.parseName(string);	
	}
	
	@Test(expected = IOException.class)
	public void paraseNameWithTooLongString() throws IOException {
		String string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		RouteService.parseName(string);	
	}
	
	@Test(expected = IOException.class)
	public void paraseNameWithEmptyString() throws IOException {
		String string = "";
		RouteService.parseName(string);	
	}
	
	@Test(expected = IOException.class)
	public void paraseNameWithAlreadyExistingString() throws IOException {
		String string = "R1";
		Route r = new Route(1, "R1");
		RouteService.addRoute(r);
		RouteService.parseName(string);	
	}

}

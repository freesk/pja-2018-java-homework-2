package com.github.freesk.pja2018javehomework2;

import java.util.ArrayList;

import com.github.freesk.pja2018javahomework2.JoinedStop;
import com.github.freesk.pja2018javahomework2.Stop;
import com.github.freesk.pja2018javahomework2.StopService;

import org.junit.Test;
import org.junit.Assert;

public class StopServiceTest {
	
	@Test
	public void getByTypeOneTest() {
		
		ArrayList<Stop> in = new ArrayList<Stop>();
		
		JoinedStop j = new JoinedStop();
		j.addStop(new Stop("test-stop-4", 1));
		j.addStop(new Stop("test-stop-5", 2));
		
		// should pass 
		in.add(new Stop("test-stop-1", 1));
		in.add(j);
		in.add(new Stop("test-stop-3", 1));
		// shouldn't pass
		in.add(new Stop("test-stop-6", 2));
		
		ArrayList<Stop> out = StopService.getStopsByType(in, 1);
		
		Assert.assertEquals(3, out.size());
	}
	
	@Test
	public void getByTypeTwoTest() {
		
		ArrayList<Stop> in = new ArrayList<Stop>();
		
		JoinedStop j = new JoinedStop();
		j.addStop(new Stop("test-stop-4", 1));
		j.addStop(new Stop("test-stop-5", 2));
		
		// should pass 
		in.add(j);
		in.add(new Stop("test-stop-6", 2));
		// shouldn't pass
		in.add(new Stop("test-stop-1", 1));
		in.add(new Stop("test-stop-3", 1));

		ArrayList<Stop> out = StopService.getStopsByType(in, 2);
		
		Assert.assertEquals(2, out.size());
	}
	
	@Test
	public void getStopTypeOneTest() {
		Stop s = new Stop("test-stop-1", 1);
		String type = StopService.getStopType(s);
		Assert.assertEquals("BUS", type);
	}
	
	@Test
	public void getStopTypeTwoTest() {
		Stop s = new Stop("test-stop-1", 2);
		String type = StopService.getStopType(s);
		Assert.assertEquals("TRAIN", type);
	}
	
	@Test
	public void getStopTypeThreeTest() {
		Stop s = new Stop("test-stop-1", 0);
		String type = StopService.getStopType(s);
		Assert.assertEquals("BUS & TRAIN", type);
	}

}

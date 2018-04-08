package com.github.freesk.pja2018javehomework2;

import com.github.freesk.pja2018javahomework2.JoinedStop;
import com.github.freesk.pja2018javahomework2.Stop;

import org.junit.Assert;
import org.junit.Test;

public class JoinedStopTest {

	@Test
	public void getTypeOneTest() {
		JoinedStop j = new JoinedStop();
		j.addStop(new Stop("test-stop-1", 1));
		j.addStop(new Stop("test-stop-2", 1));
		int type = j.getType();
		
		Assert.assertEquals(1, type);
	}
	
	@Test
	public void getTypeTwoTest() {
		JoinedStop j = new JoinedStop();
		j.addStop(new Stop("test-stop-1", 2));
		j.addStop(new Stop("test-stop-2", 2));
		int type = j.getType();
		
		Assert.assertEquals(2, type);
	}
	
	@Test
	public void getTypeThreeTest() {
		JoinedStop j = new JoinedStop();
		j.addStop(new Stop("test-stop-1", 1));
		j.addStop(new Stop("test-stop-2", 2));
		int type = j.getType();
		
		Assert.assertEquals(0, type);
	}

}

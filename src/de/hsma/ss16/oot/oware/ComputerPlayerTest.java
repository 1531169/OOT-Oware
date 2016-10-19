package de.hsma.ss16.oot.oware;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;

public class ComputerPlayerTest extends Assert {

	@Test (expected = InvalidParameterException.class)
	public void testComputerPlayer() {
		new ComputerPlayer(-100);
	}
	
	@Test
	public void testPosConstructor() {
		new ComputerPlayer(1);
	}
}

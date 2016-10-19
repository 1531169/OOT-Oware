package de.hsma.ss16.oot.oware;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

public class PitchTest {
	
	int[] sit1 = {
	4,4,4,4,4,4,
	0,5,5,5,5,4
	};
	
	int[] sit2 = {
	4,4,4,4,4,4,
	0,0,0,0,0,0
	};

	@Test
	public void testMoveOnFullHollowSit1() {
		Pitch p = new Pitch(sit1);
		int expected = 4;
		int actual = p.move(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMoveOnEmptyHollowSit1() {
		Pitch p = new Pitch(sit1);
		int expected = -1;
		int actual = p.move(6);
		assertEquals(expected, actual);
	}

	@Test
	public void testMove() {
		Pitch p= new Pitch();
		int exepted = 5;
		int actual = p.move(1);
		assertEquals(exepted, actual);
		
	}


	@Test
	public void testCollect() {
		Pitch p = new Pitch();
		int expected = 0;
		int actual= p.collect(2, 2);
	
		assertEquals(expected, actual);
	}

	@Test
	public void testCollectPitchIntInt() {
		Pitch p = new Pitch();
		int expected = 0;
		int actual = p.collect(2, 2);
		assertEquals(expected, actual);
	
	}

	@Test (expected = InvalidParameterException.class)
	public void testIsDrawValid() {
		Pitch p= new Pitch(sit2);
		boolean expected = true;
		Player player = new HumanPlayer("Rudolf", null);
		boolean actual=p.isDrawValid(player, 5);
		assertEquals(expected, actual);
		
		
		
	}

	

	@Test
	public void testGetField() {
		Pitch p = new Pitch();
		int expected = 4;
		int actual= p.getField(6);
		assertEquals(expected, actual);
		
	}


	@Test
	public void testGetLength() {
		Pitch p= new Pitch();
		int expected = 12;
		int actual = p.getLength();
		assertEquals(expected, actual);
	}

}

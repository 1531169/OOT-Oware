package de.hsma.ss16.oot.oware;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArtificialIntelligenceTest {
	
	@Test
	public void testArtificialIntelligence() {
		int difficulty = 0;
		Pitch currentPitch = null;
		ArtificialIntelligence p = new ArtificialIntelligence(difficulty, currentPitch);
		int expected = difficulty;
		int actual = p.deep();
		assertEquals(expected, actual);
		
	}

	@Test
	public void testCalc() {
	//	ArtificialIntelligenceTest p= new ArtificialIntelligenceTest(deep, level);
		
	}

	@Test
	public void testDeep() {
		ArtificialIntelligence p = new ArtificialIntelligence(0, null);
		int expected = 0;
		int actual=p.deep();
		assertEquals(expected, actual);
	}

	@Test
	public void testSize() {
	ArtificialIntelligence p = new ArtificialIntelligence(2, null);
	int expected = 0;
	int actual = p.size();
	assertEquals(expected, actual);
	}

}

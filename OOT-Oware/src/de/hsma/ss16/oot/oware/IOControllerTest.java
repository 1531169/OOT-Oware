package de.hsma.ss16.oot.oware;

import static org.junit.Assert.*;

import org.junit.Test;

public class IOControllerTest {



	@Test
	public void testPrintWelcome() {
		IOController p = new IOController();
		String expected=("Herzlich Willkommen zum Spiel Oware!\n" +
				" Sie k�nnen nach Spielstart das Spiel jederzeit\n" +
				" �ber die Eingabe von \"fertig\" beenden!\n");
		//String actual= p.TXT_WELCOME;  //Test wird m�glich, wenn 
		//assertEquals(expected, actual);
	}

	@Test
	public void testPrintPitch() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintTie() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintOnDraw() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintCanceled() {
		fail("Not yet implemented");
	}

}

package de.hsma.ss16.oot.oware;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

public class DrawTreeNodeTest {


	@Test
	public void testCalc() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		DrawTreeNode p = new DrawTreeNode(1, 1, 1);
		int expected=1;
		int actual = p.size();
		assertEquals(expected, actual);
	}

}

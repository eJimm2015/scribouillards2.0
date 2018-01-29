package com.parisdescartes.scrib.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.parisdescartes.scrib.exceptions.ThemeInexistantException;

public class ThemeInexistantExceptiontTest {
	
	ThemeInexistantException theme;

	@Before
	public void setUp() throws Exception {
		theme = new ThemeInexistantException("error msg");
	}

	@Test
	public void test() {
		assertEquals("error msg",theme.getMessage());
	}

}

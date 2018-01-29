package com.parisdescartes.scrib.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.parisdescartes.scrib.exceptions.DejaVoteException;

public class DejaVoteExceptionTest {
	DejaVoteException exp;

	@Before
	public void setUp() throws Exception {
	
	exp = new DejaVoteException("mon message");
	}

	@Test
	public void getMessageTest() {
		assertEquals("mon message",exp.getMessage());
	}

}

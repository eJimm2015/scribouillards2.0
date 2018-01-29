package com.parisdescartes.scrib.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.parisdescartes.scrib.exceptions.ArticleInexistantException;

public class ArticleInexistantExceptionTest {
	
	ArticleInexistantException exp;

	@Before
	public void setUp() throws Exception {
		exp = new ArticleInexistantException("message erreur");
	}

	@Test
	public void getMessagetest() {
		assertEquals("message erreur",exp.getMessage());
	}

}

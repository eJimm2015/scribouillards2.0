package com.parisdescartes.scrib.config;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.parisdescartes.scrib.auth.AuthAfterSignUp;
import com.parisdescartes.scrib.entities.User;

public class AuthAfterSignUpTest {
	
	@InjectMocks
	AuthAfterSignUp auth;
	
	@Mock
	Authentication authi;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = AuthenticationServiceException.class)
	public void authenticateTest_ThrowAuthenticationServiceException() {
		Mockito.when(authi.getDetails()).thenReturn(null);
		auth.authenticate(authi);
	}
	
	@Test
	public void authenticateTest_ReturnUsernamePasswordAuthenticationToken() {
		User u = new User();
		u.setEmail("mail.fr");
		u.setPassword("password");
		Mockito.when(authi.getDetails()).thenReturn(u);
		assertEquals(new UsernamePasswordAuthenticationToken(u.getEmail(),u.getPassword(),Collections.emptyList()),auth.authenticate(authi));
	}

}

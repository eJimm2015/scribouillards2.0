package com.parisdescartes.scrib.config;


import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import com.parisdescartes.scrib.auth.CustomAuthenticationProvider;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.validators.UserConnexionValidator;

public class CustomAuthenticationProviderTest {
	
	@InjectMocks
	CustomAuthenticationProvider cap;
	
	@Mock
	UserConnexionValidator validator;
	
	@Mock
	BlogService blogService;
	
	@Mock
	Authentication auth;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	//@Test
	public void authenticateTest() {
		User u = new User();
		u.setEmail("name");
        u.setPassword("pass");
		Mockito.when(auth.getName()).thenReturn("name");
		Mockito.when(auth.getCredentials()).thenReturn("pass");
	
		cap.authenticate(auth);
	}

}

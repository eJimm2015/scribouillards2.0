package com.parisdescartes.scrib.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import com.parisdescartes.scrib.auth.AuthSuccessHandler;
import com.parisdescartes.scrib.entities.User;

public class AuthSuccessHandlerTest {
	
	@InjectMocks
	AuthSuccessHandler ash;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	Authentication auth;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	HttpSession hs;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void onAuthenticationSuccessTest() throws IOException, ServletException {
		User u = new User();
		
		Mockito.when(auth.getDetails()).thenReturn(u);
		Mockito.when(request.getSession(true)).thenReturn(hs);
		
		ash.onAuthenticationSuccess(request, response, auth);
		
		Mockito.verify(response,Mockito.times(1)).sendRedirect("/themes");

	}

}

package com.parisdescartes.scrib.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;

import com.parisdescartes.scrib.auth.AuthFailureHandler;

public class AuthFailureHandlerTest {
	
	@InjectMocks
	AuthFailureHandler afh;
	
	@Mock
	HttpServletResponse response;
	
	@Mock
	AuthenticationException exception;
	
	@Mock
	HttpServletRequest request;
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void onAuthenticationFailureTest_RedirectIf() throws IOException, ServletException {
		Mockito.when(exception.getMessage()).thenReturn("inexists");
		afh.onAuthenticationFailure(request, response, exception);
		Mockito.verify(response,Mockito.times(1)).sendRedirect("/");
	}
	
	@Test
	public void onAuthenticationFailureTest_RedirectElse() throws IOException, ServletException {
		Mockito.when(exception.getMessage()).thenReturn("salut");
		afh.onAuthenticationFailure(request, response, exception);
		Mockito.verify(response,Mockito.times(1)).sendRedirect("/?error=true");
	}
	

}

package com.parisdescartes.scrib.controllers;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.parisdescartes.scrib.controllers.Signup;
import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.validators.UserInscriptionValidator;

@RunWith(SpringRunner.class)
public class SignUpTest {
	
	@InjectMocks
	private Signup signupcontroller;
	
	@Mock
	private BlogService bs;
	
	@Mock
	BindingResult br;
	
	@Mock
	private UserInscriptionValidator userInscriptionValidator;
	
	@Mock
	private User u;
	
	@Mock
	AuthenticationProvider auth;
	
	@Mock
	Authentication au;
	
	@Mock
	private HttpSession session;
	
	@Mock
	HttpServletRequest request;
	
	@Before 
	public void setUp() throws Exception {
		u = new User();
		MockitoAnnotations.initMocks(this);
		Mockito.when(u.getEmail()).thenReturn("marinavxcvfxcvbmail.fr");
		Mockito.when(u.getConfirm()).thenReturn("moi");
		Mockito.when(u.getPassword()).thenReturn("moi");
		Mockito.when(bs.addUser(u)).thenReturn(u);
		Mockito.when(auth.authenticate(au)).thenReturn(au);	
	}
	
	@Test
	public void signupTest() {
		signupcontroller.signup();
		assertNotNull(signupcontroller.signup());
		
		assertEquals("inscription",signupcontroller.signup().getViewName());
	}
	
	@Test
	public void signUpProceedTest_ErrorInfosUser() {
		Mockito.when(br.hasErrors()).thenReturn(true);
		assertEquals("inscription",signupcontroller.signUpProceed(u,br,"dd",request));
	}
	
	@Test
	public void signUpProceedTest_NoErrorInfosUser() {
		Mockito.when(br.hasErrors()).thenReturn(false);
		assertEquals("redirect:themes",signupcontroller.signUpProceed(u,br,"dd",request));
		
	}
	
	
	
	
	
	
	
	
	
	
	

}

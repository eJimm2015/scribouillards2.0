package com.parisdescartes.scrib.validators;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.validators.UserConnexionValidator;

public class UserConnexionValidatorTest {
	
	@Mock
	BlogService bs;

	@InjectMocks
	UserConnexionValidator uvc;
	
	@Mock
	BindingResult br;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Before
	public void setUp() throws Exception {
		//uvc = new UserConnexionValidator();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void validateTest_IfPassword_Email_Vide() {
		User u = new User();
		u.setEmail("");
		u.setPassword("");

		Mockito.when(bs.findUserByEmail(u.getEmail())).thenReturn(u);
		
		uvc.validate(u, br);
		
		Mockito.verify(br,Mockito.times(1)).rejectValue("email", "inexists", "Les champs de doivent pas etre vides.");
		Mockito.verify(br,Mockito.times(1)).rejectValue("password", "inexists", "Les champs de doivent pas etre vides.");
	}
	
	@Test
	public void validateTest_Email_NonVide_NonValide() {
		User u = new User();
		u.setEmail("mail");
		u.setPassword("pass");

		Mockito.when(bs.findUserByEmail("mail")).thenReturn(null);
		
		uvc.validate(u, br);
		
		Mockito.verify(br,Mockito.times(1)).rejectValue("email", "email.inexists", "L'email saisi n'existe pas.");
	}
	
	@Test
	public void validateTest_PassWord_NonMatch() {
		User u = new User();
		u.setEmail("mail");
		u.setPassword("pass");

		Mockito.when(bs.findUserByEmail("mail")).thenReturn(u);
		Mockito.when(passwordEncoder.matches(u.getPassword(), null)).thenReturn(false);
		
		uvc.validate(u, br);
		
		Mockito.verify(br,Mockito.times(1)).rejectValue("password", "password.incorrect","Le mot de passe saisie est incorrect.");
		
	}
}

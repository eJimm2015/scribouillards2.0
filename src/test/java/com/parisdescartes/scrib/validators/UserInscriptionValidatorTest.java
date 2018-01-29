package com.parisdescartes.scrib.validators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.service.CaptchaService;
import com.parisdescartes.scrib.validators.UserInscriptionValidator;

public class UserInscriptionValidatorTest {
	
	@Mock
	BlogService bs;
	
	@InjectMocks
	UserInscriptionValidator uic;
	
	@Mock
	BindingResult br;
	
	@Mock
	CaptchaService captchaService;
	
	@Before
	public void setUp() throws Exception {
		//uic = new UserInscriptionValidator();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validateTest_ErrorMdp_Mail_Captcha() {
		User u = new User();
		u.setEmail("mail");
		u.setPassword("pass");
		u.setConfirm("passss");
		
		Mockito.when(bs.findUserByEmail("mail")).thenReturn(new User());
		Mockito.when(captchaService.verifyCaptcha("","")).thenReturn(false);
		uic.validate(u, br);
		
		Mockito.verify(br,Mockito.times(1)).rejectValue("confirm", "password.confirm", "Les mots de passe ne correspondent pas.");
		Mockito.verify(br,Mockito.times(1)).rejectValue("email", "email.exists", "L'adresse mail est déjà utilisée.");
		Mockito.verify(br,Mockito.times(1)).rejectValue("captcha", "verify.error", "La vérification Captcha a échoué. Veuillez réessayer.");
	}
	
	@Test
	public void testSetCaptchaIP() {
		assertEquals(uic,uic.setCaptchaIP("ff"));
	}
	
	@Test
	public void testSetCaptchaResponse() {
		assertEquals(uic,uic.setCaptchaResponse("ff"));
	}

}

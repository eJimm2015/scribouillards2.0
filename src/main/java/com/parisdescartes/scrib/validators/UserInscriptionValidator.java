package com.parisdescartes.scrib.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.service.CaptchaService;


@Component
public class UserInscriptionValidator implements Validator {
	
	private String captchaIP;
	private String captchaResponse;
	
	@Autowired 
	BlogService blogService;
	
	@Autowired
	CaptchaService captchaService;
	
	@Override
	public boolean supports(Class<?> c) {
		return User.class.equals(c);
	}

	@Override
	public void validate(Object obj, Errors err) {
		User user = (User) obj;
		if(!user.getPassword().equals(user.getConfirm())) {
			err.rejectValue("confirm", "password.confirm", "Les mots de passe ne correspondent pas.");
		}
			
		if(blogService.findUserByEmail(user.getEmail())!= null) {
			err.rejectValue("email", "email.exists", "L'adresse mail est déjà utilisée.");
		}
		if(!captchaService.verifyCaptcha(captchaIP, captchaResponse)) {
			err.rejectValue("captcha", "verify.error", "La vérification Captcha a échoué. Veuillez réessayer.");			
		}
	}
	
	public UserInscriptionValidator setCaptchaIP(String captchaIP) {
		this.captchaIP = captchaIP;
		return this;
	}
	
	public UserInscriptionValidator setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
		return this;
	}
	
}

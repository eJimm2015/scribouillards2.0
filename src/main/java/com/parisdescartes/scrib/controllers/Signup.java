package com.parisdescartes.scrib.controllers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.tools.Constante;
import com.parisdescartes.scrib.validators.UserInscriptionValidator;

@Controller
public class Signup {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	@Qualifier("afterSignup")
	AuthenticationProvider authManager;
	
	@Autowired
	BlogService blogService;
	
	
	@Autowired
	private UserInscriptionValidator userInscriptionValidator;
	
	@GetMapping("/signup")
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		model.addObject(Constante.USER, new User());
		model.setViewName(Constante.INSCRIPTION);
		return model;
	}
	
	@PostMapping(value="/signup")
	public String signUpProceed(@Valid User user, BindingResult result, @RequestParam("g-recaptcha-response") String captchaResponse, HttpServletRequest request) {		
		userInscriptionValidator.setCaptchaIP(request.getRemoteAddr());
		userInscriptionValidator.setCaptchaResponse(captchaResponse);
		userInscriptionValidator.validate(user, result);
		if(result.hasErrors()) return Constante.INSCRIPTION;
		user = blogService.addUser(user);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
	            (user.getEmail(), user.getPassword(), Collections.emptyList());
		token.setDetails(user);
		Authentication authentication = authManager.authenticate(token);
		session.setAttribute(Constante.USER, user);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		return Constante.ACCUEIL;
	}

}

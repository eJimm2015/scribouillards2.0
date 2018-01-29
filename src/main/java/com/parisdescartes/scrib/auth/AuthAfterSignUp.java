package com.parisdescartes.scrib.auth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.parisdescartes.scrib.entities.User;

@Component
@Qualifier("afterSignup")
public class AuthAfterSignUp implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		 
		User user = (User) auth.getDetails();
		if (user == null) {
			throw new AuthenticationServiceException("Une erreur est survenue lors de l'authentification ! Veuillez réessayer.");
		}
		return new UsernamePasswordAuthenticationToken
	            (user.getEmail(), user.getPassword(), Collections.emptyList());
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}

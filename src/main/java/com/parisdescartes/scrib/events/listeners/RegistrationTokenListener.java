package com.parisdescartes.scrib.events.listeners;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.parisdescartes.scrib.entities.User;
import com.parisdescartes.scrib.entities.VerificationToken;
import com.parisdescartes.scrib.events.OnRegistrationCompleteEvent;
import com.parisdescartes.scrib.service.BlogService;
import com.parisdescartes.scrib.service.EmailService;


@Component
public class RegistrationTokenListener {

	@Autowired
	EmailService emailService;
	
	@Autowired
	BlogService blogService;
	
	@EventListener
	public void onRegistrationEventListener(OnRegistrationCompleteEvent event) {
		
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		blogService.addVerificationToken(new VerificationToken(user, token));
		StringBuffer msg = new StringBuffer("Bonjour, veuillez confirmer votre email : \n");
		msg.append("http://localhost:8080/signupConfirm?token="+token);
		emailService.sendSimpleMail(user.getEmail(), "Confirmation d'inscription", msg.toString());
	}

}

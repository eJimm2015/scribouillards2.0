package com.parisdescartes.scrib.service;

public interface EmailService {
	void sendSimpleMail (String to, String subject, String txt);
}

package com.parisdescartes.scrib.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parisdescartes.scrib.service.CaptchaService;

@Service
public class CaptchaServiceImp implements CaptchaService {

	 @Value("${google.recaptcha.key.secret}") 
	 private String recaptchaSecret;
	 
	 @Value("${captcha.verify.url}")
	 private String captchaVerifyUrl;
	 
	 @Autowired
	 RestTemplateBuilder restTemplateBuilder;
	
	@Override
	public boolean verifyCaptcha(String ip, String captchaResponse) {
	    Map<String, String> body = new HashMap<>();
	    body.put("secret", recaptchaSecret);
	    body.put("response", captchaResponse);
	    body.put("remoteip", ip);
	    ResponseEntity<Map> recaptchaResponseEntity = 
	    	      restTemplateBuilder.build()
	    	        .postForEntity(captchaVerifyUrl+
	    	          "?secret={secret}&response={response}&remoteip={remoteip}", 
	    	          body, Map.class, body);
	    return (Boolean)recaptchaResponseEntity.getBody().get("success");
	}

}

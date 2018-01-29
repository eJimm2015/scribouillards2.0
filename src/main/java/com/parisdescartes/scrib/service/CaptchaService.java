package com.parisdescartes.scrib.service;

public interface CaptchaService {
	boolean verifyCaptcha(String ip, String captchaResponse);
}

package com.parisdescartes.scrib.tools;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("dateformat.properties")
public class UtilBeans {

	@Value("${date.format}")
	private String dateFormat;
	
	@Bean
	public SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat(dateFormat);
	}
	
}

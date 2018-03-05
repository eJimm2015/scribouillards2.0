package com.parisdescartes.scrib.entities;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class VerificationToken {
	
	private static final long EXPIRY_TIME_IN_SECONDS = 60;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	private User owner;
	
	private String token;
	
	private Date expiryDate;
	
	
	public VerificationToken() {
		this(null, null);
	}

	public VerificationToken(User owner, String token) {
		this.owner = owner;
		this.token = token;
		expiryDate = calculateExpiryDate(EXPIRY_TIME_IN_SECONDS);
	}

	private Date calculateExpiryDate(long expiryTimeInSecondes) {
		Instant instant = Instant.now().plusSeconds(expiryTimeInSecondes);
		return new Date(instant.toEpochMilli());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	

}

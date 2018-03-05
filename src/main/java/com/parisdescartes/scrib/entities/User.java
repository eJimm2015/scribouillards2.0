package com.parisdescartes.scrib.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private boolean enabled = false;
	
	@NotEmpty(message="Votre nom est requis.")
	private String nom;
	
	@NotEmpty(message="Votre prénom est requis.")
	private String prenom;
	
	@NotEmpty(message="Un email est requis")
	private String email;
	
	@NotEmpty(message="Un mot de passe est requis.")
	private String password;
	
	@Transient
	@NotEmpty(message="La confirmation du mot de passe est requise.")
	private String confirm;
	private String dateInscription;
	
	@Transient
	private String captcha;
	
	public int getId() {
		return id;
	}
	public User setId(int id) {
		this.id = id;
		return this;
	}
	public String getNom() {
		return nom;
	}

	public User setNom(String nom) {
		this.nom = nom;
		return this;
	}

	public String getPrenom() {
		return prenom;
	}

	public User setPrenom(String prenom) {
		this.prenom = prenom;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public String getConfirm() {
		return confirm;
	}
	
	public User setConfirm(String confirm) {
		this.confirm = confirm;
		return this;
	}
	public String getDateInscription() {
		return dateInscription;
	}
	public User setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof User) && ((User)obj).id == id); 
	}
	
	@Override
	public int hashCode() {
		return ((Integer)id).hashCode();
	}
	
	public User setCaptcha(String captcha) {
		this.captcha = captcha;
		return this;
	}
	
	public String getCaptcha() {
		return captcha;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}

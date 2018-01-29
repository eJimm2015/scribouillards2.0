package com.parisdescartes.scrib.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Theme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Un titre est requis.")
	private String titre;
	
	@Lob
	@Column(columnDefinition="TEXT")
	@NotEmpty(message="La description est requise.")
	private String description;
	
	@NotEmpty(message="Une catégorie est requise.")
	private String categorie;
	private String categorieColor;
	
	private String dateCreation;
	
	
	@OneToMany(mappedBy="theme")
	private List<Article> articles = new ArrayList<>();
	
	@ManyToOne
	private User auteur;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public Theme setTitre(String titre) {
		this.titre = titre;
		return this;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public Theme setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
		return this;
	}
	
	public User getAuteur() {
		return auteur;
	}
	public Theme setAuteur(User auteur) {
		this.auteur = auteur;
		return this;
	}
	
	
	public Theme setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public Theme setCategorie(String categorie) {
		this.categorie = categorie;
		return this;
	}

	public Theme setArticles(List<Article> articles) {
		this.articles = articles;
		return this;
	}
	public List<Article> getArticles() {
		return articles;
	}
	
	public String getCategorieColor() {
		return categorieColor;
	}
	public Theme setCategorieColor(String categorieColor) {
		this.categorieColor = categorieColor;
		return this;
	}
}

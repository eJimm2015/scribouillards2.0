package com.parisdescartes.scrib.entities;



public final class Categorie {
	
	private Categorie() {
	}
	
	private static final String ENVIRONNEMENT = "Environnement";
	private static final String  HUMANITAIRE = "Humanitaire";
	private static final String   POLITIQUE  = "Politique";
	private static final String  DIVERTISSEMENT = "Divertissement";
	private static final String   ARTISTIQUE = "Artistique";
	private static final String  AUTRE = "Autre";
	
	public static String getCategoryColor(String c) {
		switch (c) {
		case ENVIRONNEMENT : 
				return "green";
		case HUMANITAIRE :
				return "blue";
		case POLITIQUE : 
				return "red";
	
		case DIVERTISSEMENT : 
				return "violet";

		case ARTISTIQUE : 
				return "yellow";

		case AUTRE : default : 
				return "grey";
		}
	}

}

package fr.unilim.iut.spaceinvaders;

public class Dimension {
	
	//ATTRIBUTS
	int longueur;
	int hauteur;

	// CONSTRUCTEUR
	public Dimension(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	//GETTERS
	public int longueur() {
		return this.longueur;
	}

	public int hauteur() {
		return this.hauteur;
	}

}

package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {

	// CONSTRUCTEURS
	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}

	// GETTERS
	public int hauteur() {
		return this.dimension.hauteur();
	}

	public int longueur() {
		return this.dimension.longueur();
	}


}
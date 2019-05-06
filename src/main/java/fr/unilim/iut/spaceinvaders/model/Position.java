package fr.unilim.iut.spaceinvaders.model;

public class Position {
	
	//ATTRIBUTS
	int x;
	int y;

	//CONSTRUCTEUR
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//GETTERS
	public int abscisse() {
		return this.x;
	}

	public int ordonnee() {
		return this.y;
	}

	
	//METHODES
	public void changerAbscisse(int nouvelleAbscisse) {
		this.x = nouvelleAbscisse;
	}

	public void changerOrdonnee(int nouvelleOrdonnee) {
		this.y = nouvelleOrdonnee;
	}

}
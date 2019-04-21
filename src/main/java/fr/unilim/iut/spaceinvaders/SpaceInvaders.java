package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	// ATTRIBUTS
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;

	// CONSTRUCTEUR
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = Constante.ESPACEJEU_LONGUEUR;
		this.hauteur = Constante.ESPACEJEU_HAUTEUR;
	}

	// GETTERS

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public Missile recupererMissile() {
		return this.missile;
	}

	// METHODES
	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}

	boolean aUnMissile() {
		return missile != null;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	boolean aUnVaisseau() {
		return vaisseau != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < Constante.ESPACEJEU_LONGUEUR)) && ((y >= 0) && (y < Constante.ESPACEJEU_HAUTEUR));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (Constante.ESPACEJEU_LONGUEUR - 1)) {
			vaisseau.seDeplacerVersLaDroite();
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(Constante.ESPACEJEU_LONGUEUR - Constante.VAISSEAU_LONGUEUR,
						vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.seDeplacerVersLaGauche();
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + Constante.VAISSEAU_LONGUEUR - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - Constante.VAISSEAU_HAUTEUR + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, Constante.VAISSEAU_VITESSE);
	}

	/*
	 * public void initialiserJeu() { Position positionVaisseau = new
	 * Position(this.longueur / 2, this.hauteur - 1); Dimension dimensionVaisseau =
	 * new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
	 * positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau,
	 * Constante.VAISSEAU_VITESSE); }
	 */

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau);
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((Constante.VAISSEAU_HAUTEUR + dimensionMissile.hauteur()) > Constante.ESPACEJEU_HAUTEUR)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
	}

	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir && !this.aUnMissile())
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
	}

	public boolean etreFini() {
		return false;
	}

	// TOSTRING

	@Override
	public String toString() {
		return recupererEspaceJeuDansChaineASCII();
	}

	String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < Constante.ESPACEJEU_HAUTEUR; y++) {
			for (int x = 0; x < Constante.ESPACEJEU_LONGUEUR; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

}

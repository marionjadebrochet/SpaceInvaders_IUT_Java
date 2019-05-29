package fr.unilim.iut.spaceinvaders.model;

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
	Envahisseur envahisseur;
	boolean arrivéAuBordDeGauche = false;
	boolean fini;

	// CONSTRUCTEURS
	public SpaceInvaders(int longueur, int hauteur) {

		this.longueur = longueur;
		this.hauteur = hauteur;
		this.fini = false;
	}

	// METHODES

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	public void initialiserJeu() {
		this.fini=false;
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
		Position positionEnvahisseur = new Position(this.longueur / 2, 20);
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);

	}

	public void evoluer(Commande commandeUser) {
		if (null != commandeUser) {
			if (commandeUser.gauche) {
				deplacerVaisseauVersLaGauche();
			}

			if (commandeUser.droite) {
				deplacerVaisseauVersLaDroite();
			}

			if (commandeUser.tir && !this.aUnMissile()) {
				tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
						Constante.MISSILE_VITESSE);

			}
		}
		if (this.aUnMissile()) {
			deplacerMissile();
		}
		deplacerEnvahisseur();
		
		//Si collision, envahisseur disparait
		if (this.aUnMissile() && this.aUnEnvahisseur() && Collision.detecterCollision(this.envahisseur, this.missile)) {
			this.envahisseur = null;
		}
		//Et si envahisseur disparait, missile aussi.
		if (this.envahisseur == null) {
			this.missile = null;
		}
		//Et si l'envahisseur et le missile sont plus là, alors le vaisseau disparait pour déclencher la fin de partie.
		if (this.envahisseur == null && this.missile == null) {
			this.vaisseau=null;
			this.fini = true;
		}
	}
	


	public boolean etreFini() {
		return this.fini;
	}

	// METHODE CONCERNANT UNIQUEMENT LE VAISSEAU
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.seDeplacerVersLaDroite();
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.dimension.longueur(), vaisseau.ordonneeLaPlusHaute());
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

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();
		vaisseau = new Vaisseau(dimension, position, vitesse);
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + vaisseau.dimension.longueur() - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - vaisseau.dimension.hauteur() + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	// METHODE CONCERNANT UNIQUEMENT LE MISSILE
	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);

	}

	boolean aUnMissile() {
		return missile != null;
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

	}

	public Missile recupererMissile() {
		return this.missile;
	}

	public void deplacerMissile() {
		this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		if (this.missile.ordonneeLaPlusHaute() <= 0) {
			this.missile = null;
		}

	}

	// METHODE CONCERNANT UNIQUEMENT L'ENVAHISSEUR
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	boolean aUnEnvahisseur() {
		return envahisseur != null;
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();
		envahisseur = new Envahisseur(dimension, position, vitesse);
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du l'envahisseur est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + envahisseur.dimension.longueur() - 1, y))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - envahisseur.dimension.hauteur() + 1))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");
	}

	public void deplacerEnvahisseur() {
		if (this.aUnEnvahisseur()) {
			if (arrivéAuBordDeGauche) {
				if (envahisseur.abscisseLaPlusAGauche() <= 0) {
					arrivéAuBordDeGauche = false;
				} else {
					deplacerEnvahisseurVersLaGauche();
				}
			}
			if (!arrivéAuBordDeGauche) {
				if (envahisseur.abscisseLaPlusADroite() >= Constante.ESPACEJEU_LONGUEUR - 1) {
					arrivéAuBordDeGauche = true;
				} else {
					deplacerEnvahisseurVersLaDroite();
				}
			}
		}
	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (envahisseur.abscisseLaPlusADroite() < (longueur - 1)) {
			envahisseur.seDeplacerVersLaDroite();
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.dimension.longueur(), envahisseur.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (0 < envahisseur.abscisseLaPlusAGauche())
			envahisseur.seDeplacerVersLaGauche();
		if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
			envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
		}
	}

	// TO STRING
	@Override
	public String toString() {
		return recupererEspaceJeuDansChaineASCII();
	}

}

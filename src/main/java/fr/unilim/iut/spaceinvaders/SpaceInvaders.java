package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;

	public SpaceInvaders(int longueur, int hauteur) {

		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	@Override
	public String toString() {
		return recupererEspaceJeuDansChaineASCII();
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
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}


	 public void initialiserJeu() {
	 Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
	 Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR,
			 Constante.VAISSEAU_HAUTEUR); positionnerUnNouveauVaisseau(dimensionVaisseau,
	 positionVaisseau, Constante.VAISSEAU_VITESSE); 
	 }


	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);

	}

	boolean aUnVaisseau() {
		return vaisseau != null;
	}

	boolean aUnMissile() {
		return missile != null;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.seDeplacerVersLaDroite();
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.dimension.longueur(),
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

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();
		vaisseau = new Vaisseau(dimension, position,vitesse);
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		if (!estDansEspaceJeu(x + vaisseau.dimension.longueur() - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - vaisseau.dimension.hauteur() + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
		if ((vaisseau.hauteur() + dimensionMissile.hauteur() ) > hauteur) 
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");


	}
	
	

	public void evoluer(Commande commandeUser) {

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
		if (this.aUnMissile()){
			deplacerMissile();
		}

	}

	public boolean etreFini() {
		return false;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
	public Missile recupererMissile() {
		return this.missile;
	}

	public void deplacerMissile() {
		this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		if(this.missile.ordonneeLaPlusHaute()<=0) {
			this.missile=null;
		}
		
	}


}

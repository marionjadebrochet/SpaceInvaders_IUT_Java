package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {

	// ATTRIBUTS
	private SpaceInvaders jeu;

	// CONSTRUCTEURS
	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.jeu = spaceInvaders;
	}

	// METHODES
	public void dessiner(BufferedImage im) {
		if (this.jeu.aUnVaisseau()) {
			Vaisseau vaisseau = this.jeu.recupererVaisseau();
			this.dessinerUnVaisseau(vaisseau, im);
		}
		if (this.jeu.aUnMissile()) {
			Missile missile = this.jeu.recupererMissile();
			this.dessinerUnMissile(missile, im);
		}
		if (this.jeu.aUnEnvahisseur()) {
			Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
			this.dessinerUnEnvahisseur(envahisseur, im);
		}
	}

	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage imVaisseau) {
		Graphics2D crayon = (Graphics2D) imVaisseau.getGraphics();

		crayon.setColor(Color.cyan);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), Constante.VAISSEAU_LONGUEUR,
				Constante.VAISSEAU_HAUTEUR);

	}

	private void dessinerUnMissile(Missile missile, BufferedImage imMissile) {
		Graphics2D crayon = (Graphics2D) imMissile.getGraphics();

		crayon.setColor(Color.red);
		crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), Constante.MISSILE_LONGUEUR,
				Constante.MISSILE_HAUTEUR);

	}
	
	private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage imEnvahisseur) {
		Graphics2D crayon = (Graphics2D) imEnvahisseur.getGraphics();

		crayon.setColor(Color.orange);
		crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), Constante.ENVAHISSEUR_LONGUEUR,
				Constante.ENVAHISSEUR_HAUTEUR);

	}
}
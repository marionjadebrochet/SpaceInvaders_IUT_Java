package fr.unilim.iut.spaceinvaders;

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
	}

	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.pink);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), Constante.VAISSEAU_LONGUEUR,
				Constante.VAISSEAU_HAUTEUR);

	}

	private void dessinerUnMissile(Missile missile, BufferedImage imMissile) {
		Graphics2D crayon = (Graphics2D) imMissile.getGraphics();

		crayon.setColor(Color.cyan);
		crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), Constante.MISSILE_LONGUEUR,
				Constante.MISSILE_HAUTEUR);

	}
}
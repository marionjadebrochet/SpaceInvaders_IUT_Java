package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	// METHODES

	public Collision(Missile missile, Envahisseur envahisseur) {
		// TODO Auto-generated constructor stub
	}

	public static boolean detecterCollision(Sprite sprite1, Sprite sprite2) {
		//Le sprite 1 est l'envahisseur et le sprite 2 est le missile
		
		if (sprite1.occupeLaPosition(sprite2.abscisseLaPlusAGauche(), sprite2.ordonneeLaPlusHaute())) {
			return true;
		}
		if (sprite1.occupeLaPosition(sprite2.abscisseLaPlusADroite(), sprite2.ordonneeLaPlusHaute())) {
			return true;
		}
		if (sprite1.occupeLaPosition(sprite2.abscisseLaPlusAGauche(), sprite2.ordonneeLaPlusBasse())) {
			return true;
		}
		if (sprite1.occupeLaPosition(sprite2.abscisseLaPlusADroite(), sprite2.ordonneeLaPlusBasse())) {
			return true;
		}
		if (sprite2.occupeLaPosition(sprite1.abscisseLaPlusAGauche(), sprite1.ordonneeLaPlusBasse())) {
			return true;
		}
		if (sprite2.occupeLaPosition(sprite1.abscisseLaPlusADroite(), sprite1.ordonneeLaPlusBasse())) {
			return true;
		}
		if (sprite2.occupeLaPosition(sprite1.abscisseLaPlusAGauche(), sprite1.ordonneeLaPlusHaute())) {
			return true;
		}
		if (sprite2.occupeLaPosition(sprite1.abscisseLaPlusADroite(), sprite1.ordonneeLaPlusHaute())) {
			return true;
		}
		return false;
	}
}

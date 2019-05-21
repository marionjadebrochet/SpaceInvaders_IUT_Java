package fr.unilim.iut.spaceinvaders;

import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;

import static org.junit.Assert.assertTrue;

public class CollisionTest {

	@Test
    public void test_CollisionEnvahisseurMissileAGauche() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(4, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }


	@Test
    public void test_CollisionEnvahisseurMissileADroite() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(7, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }

	@Test
    public void test_CollisionEnvahisseurMissileMillieuDroite() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(6, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
	
	@Test
    public void test_CollisionEnvahisseurMissileMillieuGauche() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(5, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }

	@Test
    public void test_CollisionEnvahisseurMissileCoteHautGauche() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(5, 11), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
	
	@Test
    public void test_CollisionEnvahisseurMissileCoteHautDroite() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(7, 11), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
}

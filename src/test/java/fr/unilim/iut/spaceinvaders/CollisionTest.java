package fr.unilim.iut.spaceinvaders;

import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Envahisseur;
import fr.unilim.iut.spaceinvaders.model.Missile;
import fr.unilim.iut.spaceinvaders.model.Position;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionTest {

	@SuppressWarnings("static-access")
	@Test
    public void test_CollisionMissileEnHautAGaucheDeLEnvhisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(4, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
	

	@SuppressWarnings("static-access")
	@Test
    public void test_CollisionMissileEnHautADroiteDeLEvahisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(7, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }

	@SuppressWarnings("static-access")
	@Test
    public void test_CollisionMissileEnHautADroiteDansEnvahisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(6, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
	
	@SuppressWarnings("static-access")
	@Test
    public void test_CollisionMissileEnHautAGaucheDansEnvahisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(5, 10), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }

	@SuppressWarnings("static-access")
	@Test
    public void test_CollisionMissileEnHautAGaucheDepasseEnvahisseurMaisMOitieDedansEnvahisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(5, 11), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
	
	@SuppressWarnings("static-access")
	@Test
    public void test_CollisionMissileEnBasMillieuDansLEnvahisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(6, 8), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertTrue((collision.detecterCollision(sprite1, sprite2)));
    }
	
	@SuppressWarnings("static-access")
	@Test
    public void test_NonCollisionMissileEnvahisseur() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(11, 7), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertFalse((collision.detecterCollision(sprite1, sprite2)));
    }
	
	@SuppressWarnings("static-access")
	@Test
    public void test_NonCollisionMissileEnvahisseurAutreCas() {
		Envahisseur sprite2 = new Envahisseur(new Dimension(3,3), new Position(5, 10), 1);
		Missile sprite1 = new Missile(new Dimension(2, 2), new Position(2, 7), 1);
		Collision collision = new Collision(sprite1, sprite2);
 	   
        assertFalse((collision.detecterCollision(sprite1, sprite2)));
    }
}

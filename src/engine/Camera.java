package engine;

/**
 * Defines a 'camera' for the engine that defines what part of the screen you're looking at. 
 * 
 * @author Maarten Lauwers
 *
 */
public class Camera {
	
	public Camera() {		
	}
	
	public Vector2D calculateAimVector(Vector2D baseVector, Vector2D positionVector, double angle) {
		// Create a guidance vector as a base for our new vector 
		double aX = baseVector.getX();
		double aY = baseVector.getY();
				
		double bX = positionVector.getX();
		double bY = positionVector.getY();
		
		// Move our guidance vector and position vector to the base (0,0)
		double oX = aX - bX;
		double oY = aY - bY;
					
		// Perform the rotation over the angle.
		double pX = Math.cos((int)-angle * Math.PI/180)*oX + (Math.sin((int)-angle * Math.PI/180)*oY);
		double pY = -Math.sin((int)-angle * Math.PI/180)*oX + Math.cos((int)-angle * Math.PI/180)*oY;					
		 		
		return new Vector2D(pX, pY);						
	}
		
}

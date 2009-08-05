package game;

import org.newdawn.slick.Image;

import engine.Camera;
import engine.Vector2D;
import org.newdawn.slick.geom.Vector2f;

public class Zombie {

	private String name;
	private int health;
	
	private Image texture;
	private double x;
	private double y;
	private float angle;	
	
	private Vector2D lastAimVector;	
	
	public Zombie(String name, Image texture, double x, double y, float angle) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		texture.setCenterOfRotation(5, 7);			
		this.lastAimVector = new Vector2D(x, y);
	}
	
	public String getName() {
		return name;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setLastAimVector(Vector2D vec) {
		this.lastAimVector = vec;
	}
	
	public float getAngle() {
		return texture.getRotation();
	}
	
	public void setRotation(float angle) {
		texture.setRotation(angle);
		this.angle = angle;
	}
	
	public void draw() {
		texture.draw((float)x, (float)y);
		
		// draw the healthbar
		
	}

	public void facePlayer(Player player) {		
		
		
		double normalisedPlayerX = player.getX() - x;
		double normalisedPlayerY = player.getY() - y;
		
		double baseVectorX = 0;
		double baseVectorY = -1;
		
		double dotproduct = normalisedPlayerX * baseVectorX + normalisedPlayerY * baseVectorY;
		
		double normPlayerLength = Math.sqrt(Math.pow(normalisedPlayerX, 2) + Math.pow(normalisedPlayerY, 2));
		double normBaseVecLength = Math.sqrt(Math.pow(baseVectorX, 2) + Math.pow(baseVectorY, 2));
		
		double cosAlpha = dotproduct/(normPlayerLength*normBaseVecLength);
		double alpha = Math.acos(cosAlpha) * 180/Math.PI;
		
		if(player.getX() < x) {
			alpha = -alpha;
		}
				
		setRotation((float)alpha);		
	}
	
	public void move() {
		
		Camera camera = new Camera();
		
		Vector2D baseVector = new Vector2D(x, y - 1);
		Vector2D posVector = new Vector2D(x, y);
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, this.angle);					
			
		// Add the result vector to the player vector to calculate the new player coordinates
		double newX = x + aimVector.getX();
		double newY = y + aimVector.getY();
		
		setX(newX);
		setY(newY);
	}
	
}

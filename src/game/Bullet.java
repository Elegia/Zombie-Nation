package game;

import org.newdawn.slick.Image;

import engine.Vector2D;

public class Bullet {

	private final int speed = 10;
	private final int range = 1000;
	
	private double x;
	private double y;
	private double angle;
	private double distanceTravelled;
	private Image texture;
	private Vector2D lastAimVector;
	private boolean isActive;
	
	public Bullet(Image texture, float x, float y, float angle, Vector2D lastAimVector) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.distanceTravelled = 0;
		this.lastAimVector = lastAimVector;
		this.isActive = true;
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setRotation(float angle) {
		texture.setRotation(angle);
		this.angle = angle;
	}	
	
	public void draw() {
		texture.draw((int)x,(int)y);
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}
	
	public void update() {		
		if(isActive) {
			this.x += lastAimVector.getX()*2;
			this.y += lastAimVector.getY()*2;
			distanceTravelled += speed;
			if(distanceTravelled > range) {
				isActive = false;
			}
		}
	
	}
	
	
}

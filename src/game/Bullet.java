package game;

import org.newdawn.slick.Image;

public class Bullet {

	private final int speed = 2;
	private final int range = 1000;
	
	private float x;
	private float y;
	private float angle;
	private float distanceTravelled;
	private Image texture;
	
	public Bullet(Image texture, float x, float y, float angle) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.distanceTravelled = 0;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setRotation(float angle) {
		texture.setRotation(angle);
		this.angle = angle;
	}
	
	public void moveLeft() {
		setRotation(270);
		x -= speed;
		distanceTravelled += speed;
		
		if(distanceTravelled >= range) {
			
		}
	}
	
	public void moveRight() {
		setRotation(90);
		x += speed;
		distanceTravelled += speed;
	}
	
	public void moveDown() {
		setRotation(180);
		y += speed;
		distanceTravelled += speed;
	}
	
	public void moveUp() {
		setRotation(0);
		y -= speed;
		distanceTravelled += speed;
	}
	
	public void draw() {
		texture.draw(x,y);
	}
	
	
}

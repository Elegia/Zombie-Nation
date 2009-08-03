package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;

import engine.TextureManager;

public class Player {

	private String name;
	private Image texture;
	private double x;
	private double y;
	private float angle;
	
	private List<Bullet> bulletList;
	
	public Player(String name, Image texture, double x, double y, float angle) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		texture.setCenterOfRotation(5, 7);		
		
		bulletList = new ArrayList<Bullet>();
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
	
	public float getAngle() {
		return texture.getRotation();
	}
	
	public void setRotation(float angle) {
		texture.setRotation(angle);
		this.angle = angle;
	}
	
	public void shoot() {
		bulletList.add(new Bullet(TextureManager.getTextureByKey("bullet1"), (int)x, (int)y, angle));
	}
	
	public void draw() {
		texture.draw((float)x, (float)y);
	}

}

package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;

import engine.TextureManager;
import engine.Vector2D;

public class Player {

	private String name;
	private Image texture;
	private double x;
	private double y;
	private float angle;	
	
	private Vector2D lastAimVector;	
	
	private List<Bullet> bulletList;
	
	public Player(String name, Image texture, double x, double y, float angle) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		texture.setCenterOfRotation(5, 7);		
		this.lastAimVector = new Vector2D(x, y);
		
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
	
	public void shoot() {
		bulletList.add(new Bullet(TextureManager.getTextureByKey("bullet1"), (int)x+5, (int)y+6, angle, lastAimVector));
	}
	
	public void updateBullets() {
		
		for(int i=bulletList.size() -1; i >= 0; i--) {
			Bullet b = (Bullet)bulletList.get(i);
			if(b.isActive() == false) {
				bulletList.remove(i);	
			}
			
		}
		for(Bullet b : bulletList) {
			if(b.isActive()) {
				b.update();	
			}									
		}
	}
	
	public void draw() {
		texture.draw((float)x, (float)y);
		for(Bullet b : bulletList) {
			b.draw();
		}
	}

}

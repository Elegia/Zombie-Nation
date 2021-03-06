package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;

import engine.ResourceManager;
import engine.Vector2D;

public class Player {

	private String name;
	private Image texture;
	private double x;
	private double y;
	private float angle;	
	
	private Vector2D lastAimVector;	
	
	private List<Bullet> bulletList;
	private boolean isShootingAllowed;
	
	public Player(String name, Image texture, double x, double y, float angle) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		texture.setCenterOfRotation(5, 7);		
		this.lastAimVector = new Vector2D(x, y);
		
		bulletList = new ArrayList<Bullet>();
		
		int delay = 0;   // delay for 5 sec.
	    int period = 500;  // repeat every sec.
	    Timer timer = new Timer();
	    
	    timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	                isShootingAllowed = true;
	            }
	        }, delay, period);	  
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
		if (isShootingAllowed) {
			isShootingAllowed = false;
						
			bulletList.add(new Bullet(ResourceManager.getTextureByKey("bullet1"), (int)x+16, (int)y+14, angle, lastAimVector));	
			
		}
		
	}
	
	public Object getBulletList() {
		return bulletList;
	}
	
	public void updateBullets(List<Zombie> zombieList) {
		
		// Check for bullets outside their range and remove them
		for(int i=bulletList.size() -1; i >= 0; i--) {
			Bullet b = (Bullet)bulletList.get(i);
			if(b.isActive() == false) {
				bulletList.remove(i);				
			}			
		}
		
		// Check for bullets that hit a zombie and remove the bullet and damage the zombie				
		for(Bullet b : bulletList) {
			if(b.isActive()) {
				
				b.update();	
				
				for(Zombie zombie : zombieList) {		
					if(zombie.isHit(b)) {
						zombie.hit();
						b.setActive(false);
					}			
				}
				
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

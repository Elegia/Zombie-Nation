package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import engine.Camera;
import engine.Config;
import engine.Vector2D;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Zombie {

	private String name;
	private int health;
	
	private Image texture;
	private double x;
	private double y;
	private double width;
	private double height;
	private float angle;		
	
	
	private Vector2D lastAimVector;	
	
	public Zombie(String name, Image texture, double x, double y, float angle) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
		this.angle = angle;
		texture.setCenterOfRotation(16, 16);			
		this.lastAimVector = new Vector2D(x, y);
		this.health = 100;
				
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
	
	public void draw(Graphics g) {
		texture.draw((float)x, (float)y);
		
		/*
		// draw the healthbar
		g.setColor(Color.white);
		g.drawRect((float)x - 10, (float)y - 10, 20, 8);
		g.setColor(Color.green);
		g.fillRect((float)x - 9, (float)y - 9, 18, 6);
		g.setColor(Color.black);
		
		TrueTypeFont ttf = Config.getCurrentFont();
		ttf.drawString((float)x - 5, (float)y - 8, "Bob");
		*/
		
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

	public int getHealth() {
		return health;
	}
	
	public boolean isHit(Bullet b) {
	
		if(b.getX() > x && b.getX() < x + width) {
			if(b.getY() > y && b.getY() < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public void hit() {
		health -= 100;					
	}

	
	
}

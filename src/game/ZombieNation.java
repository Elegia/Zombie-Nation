package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.lwjgl.util.vector.Vector;

import engine.Config;
import engine.Core;
import engine.TextureManager;
import engine.Vector2D;

public class ZombieNation {
	
	private Core engine;	
	private TextureManager textureManager;
	
	private Player player;
	private Map currentMap;
	
	public ZombieNation(Core engine) {
		this.engine = engine;		
		this.textureManager = Config.getTextureManager();
		
		loadMap();
		
		this.player = new Player("Elegia", textureManager.getTextureByKey("player"), 400, 300, 0);
	}
	
	public void loadMap() {
		currentMap = new Map(textureManager.getTextureByKey("map3"), -250, -250);
				
		currentMap.addForbiddenZone(new Rectangle(-250, -250, 512, 512));
		currentMap.addForbiddenZone(new Rectangle(256 - 250, 512 + 256 - 250, 512, 512));
		
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void walkRight(long delta) {		
		
		player.setRotation(player.getAngle() + 3f);				
		player.setLastAimVector(getAimVector());
	}
	
	public void walkLeft(long delta) {
	
		player.setRotation(player.getAngle() - 3f);		
		player.setLastAimVector(getAimVector());
	}
	
	public void walkDown(long delta) {					
		
			
			double angle = player.getAngle();			
			
			// Create a guidance vector as a base for our new vector 
			double aX = currentMap.getX();
			double aY = currentMap.getY() - 5;
			
			// Get the player coordinates
			double bX = currentMap.getX();
			double bY = currentMap.getY();
			
			// Move our guidance vector and player vector to the base (0,0)
			double oX = aX - bX;
			double oY = aY - bY;
						
			// Perform the rotation over our angle.
			double pX = Math.cos((int)-angle * Math.PI/180)*oX + (Math.sin((int)-angle * Math.PI/180)*oY);
			double pY = -Math.sin((int)-angle * Math.PI/180)*oX + Math.cos((int)-angle * Math.PI/180)*oY;					
			
			// Add the result vector to the player vector to calculate the new player coordinates
			double playerX = player.getX() + pX;
			double playerY = player.getY() + pY;
			
			// Add the result vector to the player vector to calculate the new player coordinates
			double cX = bX - pX;
			double cY = bY - pY;					
			
			if (currentMap.isWalkable((int)playerX, (int)playerY)) {
				//Update the map position based on the new coordinates
				currentMap.setPosition(pX, pY);
				Vector2D lastAimVector = new Vector2D(pX, pY);
				player.setLastAimVector(lastAimVector);
			}							
		
	}
	
	public void walkUp(long delta) {		
			
			double angle = player.getAngle();			
			
			// Create a guidance vector as a base for our new vector 
			double aX = currentMap.getX();
			double aY = currentMap.getY() - 5;
			
			// Get the map coordinates
			double bX = currentMap.getX();
			double bY = currentMap.getY();
			
			// Move our guidance vector and player vector to the base (0,0)
			double oX = aX - bX;
			double oY = aY - bY;
						
			// Perform the rotation over our angle.
			double pX = Math.cos((int)-angle * Math.PI/180)*oX + (Math.sin((int)-angle * Math.PI/180)*oY);
			double pY = -Math.sin((int)-angle * Math.PI/180)*oX + Math.cos((int)-angle * Math.PI/180)*oY;					
			
			// Add the result vector to the player vector to calculate the new player coordinates
			double playerX = player.getX() + pX;
			double playerY = player.getY() + pY;
			
			double cX = bX + pX;
			double cY = bY + pY;					
			
			if (currentMap.isWalkable((int)playerX, (int)playerY)) {
				// Update the map position based on the new coordinates
				currentMap.setPosition(-pX, -pY);	
				
				Vector2D lastAimVector = new Vector2D(pX, pY);
				player.setLastAimVector(lastAimVector);
			}
		
	}
	
	public Vector2D getAimVector() {
		double angle = player.getAngle();			
		
		// Create a guidance vector as a base for our new vector 
		double aX = currentMap.getX();
		double aY = currentMap.getY() - 5;
		
		// Get the map coordinates
		double bX = currentMap.getX();
		double bY = currentMap.getY();
		
		// Move our guidance vector and player vector to the base (0,0)
		double oX = aX - bX;
		double oY = aY - bY;
					
		// Perform the rotation over our angle.
		double pX = Math.cos((int)-angle * Math.PI/180)*oX + (Math.sin((int)-angle * Math.PI/180)*oY);
		double pY = -Math.sin((int)-angle * Math.PI/180)*oX + Math.cos((int)-angle * Math.PI/180)*oY;					
		 
		
		return new Vector2D(pX, pY);						
	}
	
	public void draw(Graphics g) {
		
		player.updateBullets();
		
		currentMap.draw(g);
		player.draw();
	}
	
}

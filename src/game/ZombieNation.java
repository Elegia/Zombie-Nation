package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.lwjgl.util.vector.Vector;

import engine.Config;
import engine.Core;
import engine.TextureManager;

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
		currentMap = new Map();
		
		currentMap.addMapBlock(new MapBlock(textureManager.getTextureByKey("map2"), -250, -250));
		currentMap.addForbiddenZone(new Rectangle(-250, -250, 512, 512));
		currentMap.addForbiddenZone(new Rectangle(256 - 250, 512 + 256 - 250, 512, 512));
		
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void walkRight() {
		//if (currentMap.isWalkable(player.getX() + 1, player.getY())) {
			//currentMap.moveLeft(1);
			//player.setRotation(90);
			player.setRotation(player.getAngle() + 0.2f);
		//}
		
	}
	
	public void walkLeft() {
		//if (currentMap.isWalkable(player.getX() - 1, player.getY())) {
			//currentMap.moveRight(1);
			//player.setRotation(270);
			player.setRotation(player.getAngle() - 0.2f);
		//}
		
	}
	
	public void walkDown() {
		if (currentMap.isWalkable((int)player.getX(), (int)player.getY() + 1)) {					
		
			
			double angle = player.getAngle();			
			
			// Create a guidance vector as a base for our new vector 
			double aX = player.getX();
			double aY = player.getY() - 1;
			
			// Get the player coordinates
			double bX = player.getX();
			double bY = player.getY();
			
			// Move our guidance vector and player vector to the base (0,0)
			double oX = aX - bX;
			double oY = aY - bY;
						
			// Perform the rotation over our angle.
			double pX = Math.cos((int)-angle * Math.PI/180)*oX + (Math.sin((int)-angle * Math.PI/180)*oY);
			double pY = -Math.sin((int)-angle * Math.PI/180)*oX + Math.cos((int)-angle * Math.PI/180)*oY;					
			
			// Add the result vector to the player vector to calculate the new player coordinates
			double cX = bX - pX;
			double cY = bY - pY;					
			
			if (currentMap.isWalkable((int)cX, (int)cY)) {
				// Update the player's position with the new coordinates
				player.setX(cX);
				player.setY(cY);
			}
						
		}
		
	}
	
	public void walkUp() {		
			
			double angle = player.getAngle();			
			
			// Create a guidance vector as a base for our new vector 
			double aX = player.getX();
			double aY = player.getY() - 0.1;
			
			// Get the player coordinates
			double bX = player.getX();
			double bY = player.getY();
			
			// Move our guidance vector and player vector to the base (0,0)
			double oX = aX - bX;
			double oY = aY - bY;
						
			// Perform the rotation over our angle.
			double pX = Math.cos((int)-angle * Math.PI/180)*oX + (Math.sin((int)-angle * Math.PI/180)*oY);
			double pY = -Math.sin((int)-angle * Math.PI/180)*oX + Math.cos((int)-angle * Math.PI/180)*oY;					
			
			// Add the result vector to the player vector to calculate the new player coordinates
			double cX = bX + pX;
			double cY = bY + pY;					
			
			if (currentMap.isWalkable((int)cX, (int)cY)) {
				// Update the player's position with the new coordinates
				//currentMap.setPosition(cX, cY);
				player.setX(cX);
				player.setY(cY);
			}
		
	}
	
	public void draw(Graphics g) {		
		currentMap.draw(g);
		player.draw();
	}
	
}

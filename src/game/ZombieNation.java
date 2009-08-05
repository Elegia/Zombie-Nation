package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.lwjgl.util.vector.Vector;

import engine.Camera;
import engine.Config;
import engine.Core;
import engine.ResourceManager;
import engine.Vector2D;

public class ZombieNation {
	
	private Core engine;	
	private ResourceManager resourceManager;
	private Camera camera;
	
	private Music music;
	
	private Player player;
	private List<Zombie> zombieList;
	private Map currentMap;
	
	public ZombieNation(Core engine) {
		this.engine = engine;		
		this.resourceManager = Config.getResourceManager();
		this.camera = new Camera();
		
		loadMap();
		
		this.player = new Player("Elegia", resourceManager.getTextureByKey("player"), 400, 300, 0);
		
		this.zombieList = new ArrayList<Zombie>();
		for(int i=0; i<1; i++) {
			zombieList.add(new Zombie("Zombie", resourceManager.getTextureByKey("zombie1"), 380, 320, 0));
		}
		//music = ResourceManager.getSoundByKey("music1");
    	//music.play();
	}
	
	public void loadMap() {
		currentMap = new Map(resourceManager.getTextureByKey("map3"), -250, -250);
				
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
		
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());
		
		player.setLastAimVector(aimVector);
	}
	
	public void walkLeft(long delta) {
	
		player.setRotation(player.getAngle() - 3f);	
		
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());
		
		player.setLastAimVector(aimVector);
	}
	
	public void walkDown(long delta) {											
			
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());					
			
		// Add the result vector to the player vector to calculate the new player coordinates
		double playerX = player.getX() - aimVector.getX();
		double playerY = player.getY() - aimVector.getY();
						
		
		if (currentMap.isWalkable((int)playerX, (int)playerY)) {
			//Update the map position based on the new coordinates
			//currentMap.setPosition(aimVector.getX(), aimVector.getY());
			
			Vector2D updateVector = new Vector2D(aimVector.getX(), aimVector.getY());
			updateEntityPositions(updateVector);
			player.setLastAimVector(aimVector);
		}							
			
	}
	
	public void walkUp(long delta) {		
			
		
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());					
		
		// Add the result vector to the player vector to calculate the new player coordinates
		double playerX = player.getX() + aimVector.getX();
		double playerY = player.getY() + aimVector.getY();
					
		
		if (currentMap.isWalkable((int)playerX, (int)playerY)) {
			//Update the map position based on the new coordinates
			//currentMap.setPosition(-aimVector.getX(), -aimVector.getY());
			
			Vector2D updateVector = new Vector2D(-aimVector.getX(), -aimVector.getY());
			updateEntityPositions(updateVector);
			player.setLastAimVector(aimVector);
		}	
		
	}
	
	public void updateEntityPositions(Vector2D updateVec) {
		currentMap.setPosition(updateVec.getX(), updateVec.getY());
		for(Zombie zombie : zombieList) {
			zombie.setX(zombie.getX() + (int)updateVec.getX());
			zombie.setY(zombie.getY() + (int)updateVec.getY());
			zombie.setLastAimVector(updateVec);
		}
	}
	
	public void gameUpdate() {
		// Let all zombies within 500 meters run towards you
		for(Zombie zombie : zombieList) {
			
			double distanceX = Math.abs(player.getX() - zombie.getX());
			double distanceY = Math.abs(player.getY() - zombie.getY());			
			
			if(distanceX <= 500 && distanceY <= 500) {				
				zombie.facePlayer(player);	
				zombie.move();
			}
		}
		
	}
	public void draw(Graphics g) {
		
		player.updateBullets();
		
		currentMap.draw(g);
		
		for (Zombie zombie : zombieList) {
			zombie.draw();
		}
		player.draw();
				
	}
	
}

package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		int baseX = 380;
		int baseY = 320;
		Random rand = new Random();
		for(int i=0; i<10; i++) {
						
			zombieList.add(new Zombie("Zombie", resourceManager.getTextureByKey("zombie1"), baseX + rand.nextInt(100), baseY + rand.nextInt(100), 0));
		}
		//music = ResourceManager.getSoundByKey("music1");
    	//music.play();
	}
	
	public void loadMap() {
		
		int mapX = -500;
		int mapY = -500;
		
		currentMap = new Map(resourceManager.getTextureByKey("map3"), mapX, mapY);
				
		/*
		currentMap.addForbiddenZone(new Rectangle(-mapX, -250, 512, 512));
		currentMap.addForbiddenZone(new Rectangle(256 - 250, 512 + 256 - 250, 512, 512));
		*/
		
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
		
		// Update the zombies
		for(int i=zombieList.size() -1; i>=0; i--) {
			Zombie zombie = (Zombie)zombieList.get(i);
					
			
			if(zombie.getHealth() <= 0) {
				
				// Remove all zombies that have a health <= 0
				zombieList.remove(i);
			} else {
				
				// Let the zombies within 500 meters run towards you
				
				double distanceX = Math.abs(player.getX() - zombie.getX());
				double distanceY = Math.abs(player.getY() - zombie.getY());			
				
				if(distanceX <= 500 && distanceY <= 500) {				
					zombie.facePlayer(player);	
					zombie.move();
				}
			}
			
		}		
		
		// Update all flying bullets (by the player). This will also check whether zombies are hit or if bullets 
		// are out of range and update everything accordingly.
		player.updateBullets(zombieList);
		
	}
	public void draw(Graphics g) {			
		
		currentMap.draw(g);
		
		for (Zombie zombie : zombieList) {
			zombie.draw();
		}
		player.draw();
				
	}

	/**
	 * Handles any mouse movement
	 * 
	 * @param mouseX
	 * @param mouseY
	 */
	public void mouseMoved(int mouseX, int mouseY) {
		
		// Make the player face the mouse position, so calculate the required angle and rotate the player accordingly
		double normalisedMouseX = mouseX - player.getX();
		double normalisedMouseY = mouseY - player.getY();
		
		double baseVectorX = 0;
		double baseVectorY = -1;
		
		double dotproduct = normalisedMouseX * baseVectorX + normalisedMouseY * baseVectorY;
		
		double normPlayerLength = Math.sqrt(Math.pow(normalisedMouseX, 2) + Math.pow(normalisedMouseY, 2));
		double normBaseVecLength = Math.sqrt(Math.pow(baseVectorX, 2) + Math.pow(baseVectorY, 2));
		
		double cosAlpha = dotproduct/(normPlayerLength*normBaseVecLength);
		double alpha = Math.acos(cosAlpha) * 180/Math.PI;
		
		if(player.getX() < mouseX) {
			alpha = -alpha;
		}
		
		player.setRotation(-(float)alpha);
		
		// Create the aim vector that would make the player walk in the right direction (this is done when pressing the 
		// up or down key, but is once again needed here in case the player would fire bullets after rotating, as the 
		// bullets need the aim vector to move in the right direction).
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());
		
		player.setLastAimVector(aimVector);		
		//setRotation((float)alpha);		
	}
	
}

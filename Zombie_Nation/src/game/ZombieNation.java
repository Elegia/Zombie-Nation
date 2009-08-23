package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
import gui.GuiFrame;
import gui.GuiItem;
import gui.GuiWeapon;

public class ZombieNation {
	
	private Core engine;	
	private ResourceManager resourceManager;
	private Camera camera;
	
	// GUI Stuff
	private List<GuiItem> gui;
	
	
	private Music music;
	
	// Our hero
	private Player player;
	private final double playerMoveSpeed = 3;
	
	// Hellspawn management
	private List<Zombie> zombieList;
	private boolean spreadAllowed;
	
	// Real estate stuff
	private List<Map> currentMap;
	
	public ZombieNation(Core engine) {
		this.engine = engine;		
		this.resourceManager = Config.getResourceManager();
		this.camera = new Camera();
		
		currentMap = new ArrayList<Map>();
		
		loadMap();
		
		this.player = new Player("Elegia", resourceManager.getTextureByKey("player"), 400, 300, 0);
		
		this.zombieList = new ArrayList<Zombie>();
		int baseX = 380;
		int baseY = 320;
		Random rand = new Random();
		for(int i=0; i<10; i++) {
			
			int directionRandInt = rand.nextInt(4);
			Zombie zombie = null;
			
			if (directionRandInt == 1) {
				zombie = new Zombie("Zombie", resourceManager.getTextureByKey("zombie1"), baseX + rand.nextInt(500), baseY + rand.nextInt(500), 0);				
			} else if (directionRandInt == 2) {
				zombie = new Zombie("Zombie", resourceManager.getTextureByKey("zombie1"), baseX - rand.nextInt(500), baseY - rand.nextInt(500), 0);	
			} else if (directionRandInt == 3) {
				zombie = new Zombie("Zombie", resourceManager.getTextureByKey("zombie1"), baseX + rand.nextInt(500), baseY - rand.nextInt(500), 0);
			} else {
				zombie = new Zombie("Zombie", resourceManager.getTextureByKey("zombie1"), baseX - rand.nextInt(500), baseY + rand.nextInt(500), 0);
			}
			
			zombie.facePlayer(player);
			zombieList.add(zombie);								
		}
		//music = ResourceManager.getSoundByKey("music1");
    	//music.play();
		
		// GUI
		gui = new ArrayList<GuiItem>();		
		gui.add(new GuiFrame(resourceManager.getTextureByKey("gui_gun_frame"), 620, 10));
		gui.add(new GuiWeapon(resourceManager.getTextureByKey("gun"), 630, 20));
		
		int delay = 2000;   // delay for 5 sec.
	    int period = 25;  // repeat every sec.
	    Timer timer = new Timer();
	    
	    timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	                spreadAllowed = true;
	            }
	        }, delay, period);	    
	}
	
	public void loadMap() {
		
		int mapX = -256;
		int mapY = -256;
		
		Image floor = resourceManager.getTextureByKey("floor");
		currentMap.add(new Map(floor, -1024, -1024));
		currentMap.add(new Map(floor, 0, -1024));
		currentMap.add(new Map(floor, -1024, 0));
		currentMap.add(new Map(floor, 0, 1024));
		currentMap.add(new Map(floor, 0, 0));
		
		//currentMap.add(new Map(resourceManager.getTextureByKey("map3"), mapX, mapY));
		
		/*
		currentMap.add
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part1"), mapX, mapY));
		mapY -= 1024;
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part2"), mapX + 512, mapY));
		mapY -= 1024;
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part3"), mapX + 512, -1024 - 512));
		mapY = 0;		
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part4"), mapX + 1024, 768));
		mapY -= 1024;
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part5"), mapX + 2048 - 512, mapY));
		mapY -= 1024;
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part6"), mapX + 2048 - 512, -1024 - 512));
		mapY = 0;		
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part7"), mapX + 2048, mapY));
		mapY -= 1024;
		
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part8"), mapX + 2048, mapY));
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part9"), mapX + 2048 + 256, 0));
		currentMap.add(new Map(resourceManager.getTextureByKey("map1part10"), mapX + 2048 + 768, -1024));
		*/
		
		/*
		currentMap.addForbiddenZone(new Rectangle(-mapX, -250, 512, 512));
		currentMap.addForbiddenZone(new Rectangle(256 - 250, 512 + 256 - 250, 512, 512));
		*/
		
	}
	
	public List<Map> getCurrentMap() {
		return currentMap;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void walkRight(long delta) {		
		
		/*
		player.setX(player.getX() + playerMoveSpeed);
		updateEntityPositions(new Vector2D(-playerMoveSpeed, 0));
		*/
		
		/*
		player.setRotation(player.getAngle() + 3f);		
		
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());
		
		player.setLastAimVector(aimVector);
		*/
		
	}
	
	public void walkLeft(long delta) {
	
		/*
		player.setX(player.getX() - playerMoveSpeed);
		updateEntityPositions(new Vector2D(playerMoveSpeed, 0));
		
		*/
		
		/*
		player.setRotation(player.getAngle() - 3f);	
		
		Vector2D baseVector = new Vector2D(currentMap.getX(), currentMap.getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.getX(), currentMap.getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());
		
		player.setLastAimVector(aimVector);
		*/
	}
	
	public void walkDown(long delta) {											
			
		Vector2D baseVector = new Vector2D(currentMap.get(0).getX(), currentMap.get(0).getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.get(0).getX(), currentMap.get(0).getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());								
				
		// Add the result vector to the player vector to calculate the new player coordinates
		double playerX = player.getX() - aimVector.getX();
		double playerY = player.getY() - aimVector.getY();
						
		
		boolean isWalkable = true;
		for(Map map : currentMap) {
			if (! map.isWalkable((int)playerX, (int)playerY)) {
				isWalkable = false;
				break;
			}
		}
		
		
		if (isWalkable) {
			//Update the map position based on the new coordinates
			//currentMap.setPosition(aimVector.getX(), aimVector.getY());			
			Vector2D updateVector = new Vector2D(aimVector.getX(), aimVector.getY());
			updateEntityPositions(updateVector);
			player.setLastAimVector(aimVector);
		}
		
		
			
	}
	
	public void walkUp(long delta) {		
			
		
		Vector2D baseVector = new Vector2D(currentMap.get(0).getX(), currentMap.get(0).getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.get(0).getX(), currentMap.get(0).getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());					
		
	
		// Add the result vector to the player vector to calculate the new player coordinates
		double playerX = player.getX() + aimVector.getX();
		double playerY = player.getY() + aimVector.getY();
					
		boolean isWalkable = true;
		for(Map map : currentMap) {
			if (! map.isWalkable((int)playerX, (int)playerY)) {
				isWalkable = false;
				break;
			}
		}
		
		if (isWalkable) {
			//Update the map position based on the new coordinates
			//currentMap.setPosition(-aimVector.getX(), -aimVector.getY());
			
			Vector2D updateVector = new Vector2D(-aimVector.getX(), -aimVector.getY());
			updateEntityPositions(updateVector);
			player.setLastAimVector(aimVector);
		}	
		
	}
	
	public void updateEntityPositions(Vector2D updateVec) {
		for(Map map : currentMap) {
			map.setPosition(updateVec.getX(), updateVec.getY());
		}
		
		for(Zombie zombie : zombieList) {			
			zombie.setX(zombie.getX() + (int)updateVec.getX());
			zombie.setY(zombie.getY() + (int)updateVec.getY());			
			
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
					
					if(spreadAllowed) {
											
						// Let the zombies within 600 meters run towards you								
						double distanceX = Math.abs(player.getX() - zombie.getX());
						double distanceY = Math.abs(player.getY() - zombie.getY());			
							
						if(distanceX <= 800 && distanceY <= 800) {				
							zombie.facePlayer(player);	
							zombie.move();
						}
					}
				}
			}
			
			spreadAllowed = false;			
				
	
		// Update all flying bullets (by the player). This will also check whether zombies are hit or if bullets 
		// are out of range and update everything accordingly.
		player.updateBullets(zombieList);
		
	}
	public void draw(Graphics g) {			
		
		for(Map map : currentMap) {
			map.draw(g);
		}		
		
		for (Zombie zombie : zombieList) {
			zombie.draw(g);
		}
		
		player.draw();
		
		for (GuiItem gi : gui) {
			gi.draw(g);
		}
				
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
		Vector2D baseVector = new Vector2D(currentMap.get(0).getX(), currentMap.get(0).getY() - 5);
		Vector2D posVector = new Vector2D(currentMap.get(0).getX(), currentMap.get(0).getY());
		Vector2D aimVector = camera.calculateAimVector(baseVector, posVector, player.getAngle());
		
		player.setLastAimVector(aimVector);		
		//setRotation((float)alpha);		
	}
	
}

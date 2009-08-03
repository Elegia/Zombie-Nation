package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Map {

	private Image mapTexture;
	private double x;
	private double y;
	
	private List<Rectangle> forbiddenZoneList;	
	
	public Map(Image mapTexture, double x, double y) {
		this.mapTexture = mapTexture;
		this.x = x;
		this. y = y;
		
		forbiddenZoneList = new ArrayList<Rectangle>();		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void addForbiddenZone(Rectangle b) {
		forbiddenZoneList.add(b);
	}
	
	public boolean isWalkable(float x, float y) {
	
		for (Rectangle zone : forbiddenZoneList) {
					
			if(zone.contains(x, y)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void draw(Graphics g) {
		mapTexture.draw((int)x, (int)y);
		
		/*
		for(Rectangle zone : forbiddenZoneList) {
			g.drawRect(zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
		}
		*/
	}

	/*
	public void moveRight(int i) {
		for (MapBlock mapBlock : mapBlocks) {
			mapBlock.moveRight(i);
		}
		
		for(Rectangle zone : forbiddenZoneList) {
			zone.setX(zone.getX() + i);	
		}
		
	}
	
	public void moveLeft(int i) {
		for (MapBlock mapBlock : mapBlocks) {
			mapBlock.moveLeft(i);
		}
		
		for(Rectangle zone : forbiddenZoneList) {
			zone.setX(zone.getX() - i);						
		}
		
	}
	
	public void moveDown(int i) {
		for (MapBlock mapBlock : mapBlocks) {
			mapBlock.moveDown(i);
		}
		
		for(Rectangle zone : forbiddenZoneList) {
			zone.setY(zone.getY() + i);			
		}
		
	}
	
	public void moveUp(int i) {
		for (MapBlock mapBlock : mapBlocks) {
			mapBlock.moveUp(i);
		}
		
		for(Rectangle zone : forbiddenZoneList) {
			zone.setY(zone.getY() - i);	
		}
		
	}
	*/

	public void setPosition(double cX, double cY) {
		this.x = this.x + (int)cX;
		this.y = this.y + (int)cY;		
		
		for(Rectangle zone : forbiddenZoneList) {
			zone.setX(zone.getX() + (int)cX);
			zone.setY(zone.getY() + (int)cY);
		}
	}


}

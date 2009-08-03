package game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Map {

	private List<Rectangle> forbiddenZoneList;
	private List<MapBlock> mapBlocks;
	
	public Map() {
		forbiddenZoneList = new ArrayList<Rectangle>();
		mapBlocks = new ArrayList<MapBlock>();
	}
	
	public void addMapBlock(MapBlock mb) {
		mapBlocks.add(mb);		
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
		for (MapBlock mapBlock : mapBlocks) {
			mapBlock.draw();
		}
		
		for(Rectangle zone : forbiddenZoneList) {
			g.drawRect(zone.getX(), zone.getY(), zone.getWidth(), zone.getHeight());
		}
	}

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

	public void setPosition(double cX, double cY) {
		for (MapBlock mapBlock : mapBlocks) {
			mapBlock.setPosition(mapBlock.getX() + cX, mapBlock.getY() + cY);
		}	
		
		for(Rectangle zone : forbiddenZoneList) {
			zone.setY(zone.getY() + (float)cY);
			zone.setX(zone.getY() + (float)cX);
		}
	}
}

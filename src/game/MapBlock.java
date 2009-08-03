package game;

import org.newdawn.slick.Image;

public class MapBlock {

	private Image texture;
	private double x;
	private double y;
	
	public MapBlock(Image texture, double x, double y) {
		this.texture = texture;
		this.x = x;
		this.y = y;		
	}
	
	public Image getTexture() {
		return texture;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void draw() {
		texture.draw((float)x,(float)y);
	}

	public void moveRight(int i) {
		x = x + i;		
	}
	
	public void moveLeft(int i) {
		x = x - i;		
	}
	
	public void moveUp(int i) {
		y = y - i;	
	}
	
	public void moveDown(int i) {
		y = y + i;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;		
	}
}

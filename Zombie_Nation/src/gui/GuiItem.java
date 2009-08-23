package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class GuiItem {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private Image texture;
	
	public GuiItem(Image texture, int x, int y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = texture.getWidth();
		this.height = texture.getHeight();
	}
	
	public GuiItem(Image texture, int x, int y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		texture.draw(x, y, width, height);
	}
}

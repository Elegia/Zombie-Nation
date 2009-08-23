package gui;

import org.newdawn.slick.Image;

public class GuiFrame extends GuiItem {

	public GuiFrame(Image texture, int x, int y) {
		super(texture, x, y);
	}
	
	public GuiFrame(Image texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);
	}
}

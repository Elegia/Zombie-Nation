package engine;

import java.io.InputStream;

import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Config {

	// SINGLETON? 
	
	private static int screenWidth;
	private static int screenHeight;
	private static TrueTypeFont ttf;	
	
	//private Core instance;
	
	private static ResourceManager resourceManager;	
	
	public Config(int screenWidth, int screenHeight, ResourceManager resourceManager) {
		Config.screenWidth = screenWidth;
		Config.screenHeight = screenHeight; 
		Config.resourceManager = resourceManager;
		
		try {
           // InputStream oi = ResourceLoader
            //                .getResourceAsStream("black.ttf");
            //java.awt.Font f = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, oi);
            
            java.awt.Font f = new java.awt.Font("Arial", java.awt.Font.PLAIN, 10);
            ttf = new TrueTypeFont(f.deriveFont(10f), true);
        } catch (Exception e) {
            System.out.println("Failed to load font");
        } 
		
	}
	
	public static int getScreenWidth() {
		return screenWidth;
	}
	
	public static int getScreenHeight() {
		return screenHeight;
	}
	
	public static ResourceManager getResourceManager() {
		return resourceManager;
	}
	
	public static TrueTypeFont getCurrentFont() {
		return ttf;
	}
}

package engine;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureManager {

	private static HashMap textureMap;
	
	public TextureManager() {
		textureMap = new HashMap();
			
		try {
			
			Image player = new Image("res/sprites/player.png");
			Image map1 = new Image("res/sprites/map1.png");
			Image map2 = new Image("res/sprites/map2.png");
			Image map3 = new Image("res/sprites/map3.png");		
			Image zombie1 = new Image("res/sprites/zombie1.png");
			Image bullet1 = new Image("res/sprites/bullet1.png");
			
			addTextureByKey("player", player);
	    	addTextureByKey("map1", map1);
	    	addTextureByKey("map2", map2);
	    	addTextureByKey("map3", map3);
	    	addTextureByKey("zombie1", zombie1);
	    	addTextureByKey("bullet1", bullet1);
	    	
		} catch (SlickException e) {			
			e.printStackTrace();
		}    	    	    

	}
	
	public static Image getTextureByKey(String key) {
		return (Image)textureMap.get(key);
	}
	
	public static void addTextureByKey(String key, Image texture) {
		textureMap.put(key, texture);
	}
}

package engine;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ResourceManager {

	private static HashMap textureMap;
	private static HashMap musicMap;
	
	public ResourceManager() {
		textureMap = new HashMap();
			
		try {
			//Music music1 = new Music("res/music/01.ogg");
			//music1.loop();
//			addMusicByKey("music1", music1);
			
			
			Image player = new Image("res/sprites/player3.png");
			
			//GUI
			Image gui_gun_frame = new Image("res/sprites/gui_gun_frame.png");
			Image gun = new Image("res/sprites/gun.png");
			
			//Image map1 = new Image("res/sprites/map1.png");
			Image map3 = new Image("res/sprites/map3.png");
			Image floor = new Image("res/sprites/tilesfloor.png");
			/*
			Image map1part1 = new Image("res/sprites/map1part1.png");
			Image map1part2 = new Image("res/sprites/map1part2.png");
			Image map1part3 = new Image("res/sprites/map1part3.png");
			Image map1part4 = new Image("res/sprites/map1part4.png");
			Image map1part5 = new Image("res/sprites/map1part5.png");
			Image map1part6 = new Image("res/sprites/map1part6.png");
			Image map1part7 = new Image("res/sprites/map1part7.png");
			
			Image map1part8 = new Image("res/sprites/map1part8.png");
			Image map1part9 = new Image("res/sprites/map1part9.png");
			Image map1part10 = new Image("res/sprites/map1part10.png");
			Image map1part11 = new Image("res/sprites/map1part11.png");
			*/
			
			Image zombie1 = new Image("res/sprites/zombie2.png");
			Image bullet1 = new Image("res/sprites/bullet2.png");
			
			addTextureByKey("floor", floor);
			addTextureByKey("player", player);
			
			//GUI
			addTextureByKey("gui_gun_frame", gui_gun_frame);
			addTextureByKey("gun", gun);
			
	    	//addTextureByKey("map1", map1);
	    	//addTextureByKey("map2", map2);
	    	
			/*
	    	addTextureByKey("map1part1", map1part1);
	    	addTextureByKey("map1part2", map1part2);
	    	addTextureByKey("map1part3", map1part3);
	    	addTextureByKey("map1part4", map1part4);
	    	addTextureByKey("map1part5", map1part5);
	    	addTextureByKey("map1part6", map1part6);
	    	addTextureByKey("map1part7", map1part7);
	    	
	    	addTextureByKey("map1part8", map1part8);
	    	addTextureByKey("map1part9", map1part9);
	    	addTextureByKey("map1part10", map1part10);
	    	addTextureByKey("map1part11", map1part11);
	    	*/
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
	
	public static Music getSoundByKey(String key) {
		return (Music)musicMap.get(key);
	}
	
	public static void addTextureByKey(String key, Image texture) {
		textureMap.put(key, texture);
	}
	
	public static void addMusicByKey(String key, Music music) {
		musicMap.put(key, music);
	}
}

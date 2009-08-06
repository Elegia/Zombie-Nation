package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.lwjgl.opengl.GL11;

import game.ZombieNation;

import javax.swing.Timer;
 
public class Core extends BasicGame {
 		
			
	private boolean isLeftMouseDown;
	private boolean isRightMouseDown;
	private boolean isKeyLeftDown;
	private boolean isKeyRightDown;
	private boolean isKeyUpDown;
	private boolean isKeyDownDown;
		
	private int oldMouseX;
	private int oldMouseY;
	
	private long delta2;
	private long lastLoopTime;
	
	private ZombieNation game;	
	
    public Core()
    {
        super("Kings & Castles");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException {
     	
    	ResourceManager textureManager = new ResourceManager();
    	
    	new Config(800, 800, textureManager);   
    	      	    	  
    	game = new ZombieNation(this);    	    	  
    	
    	gc.setShowFPS(true);    
    	gc.setClearEachFrame(true);
    	gc.setVSync(true);
    	//gc.setMaximumLogicUpdateInterval(10);
    	gc.setTargetFrameRate(60);
    	
    }
 
    /**
	 * @see org.newdawn.slick.InputListener#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		/*
		if (key == Input.KEY_LEFT) {			
			game.getCurrentMap().moveRight(5);							
		}		
		
		if (key == Input.KEY_RIGHT) {		
			game.getCurrentMap().moveLeft(5);							
		}	

		if (key == Input.KEY_DOWN) {			
			game.getCurrentMap().moveUp(5);							
		}		
		
		if (key == Input.KEY_UP) {			
			game.getCurrentMap().moveDown(5);							
		}
		*/		
	}

	/**
	 * @see org.newdawn.slick.InputListener#keyReleased(int, char)
	 */
	public void keyReleased(int key, char c) {
		/*
		if(key == Input.KEY_LEFT) {
			isKeyLeftDown = false;
		}
		
		if(key == Input.KEY_RIGHT) {
			isKeyRightDown = false;
		}
		
		if(key == Input.KEY_DOWN) {
			isKeyDownDown = false;
		}
		
		if(key == Input.KEY_UP) {
			isKeyUpDown = false;
		}*/
	}

	/**
	 * @see org.newdawn.slick.InputListener#mouseMoved(int, int, int, int)
	 */
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
	}

	/**
	 * @see org.newdawn.slick.InputListener#mouseClicked(int, int, int, int)
	 */
	public void mouseClicked(int button, int x, int y, int clickCount) {
	}
	
	/**
	 * @see org.newdawn.slick.InputListener#mousePressed(int, int, int)
	 */
	public void mousePressed(int button, int x, int y) {

	}
	
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	
    	Input input = gc.getInput();
    	 
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();    	 
    	
    	game.mouseMoved(mouseX, mouseY);
    	    	    	
    	if (input.isKeyDown(input.KEY_Q)) {    		
    		game.walkLeft(delta);
    	}		
    		
    	if (input.isKeyDown(input.KEY_D)) {		
    		game.walkRight(delta);
    	}	

    	if (input.isKeyDown(input.KEY_S)) {			
    		game.walkDown(delta);
    	}		
    		
    	if (input.isKeyDown(input.KEY_Z)) {			
    		game.walkUp(delta);
    	}    	
    	
    	if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)) {
    		game.getPlayer().shoot();
    	}
    	
    	/*
    	if (input.isKeyDown(input.KEY_SPACE)){
    		
    	}
		*/
    	
    	game.gameUpdate();
		
    
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	    	
    	game.draw(g);    	
    	
    }           
       
    
    public static void main(String[] args) 
			throws SlickException
    {    	
         AppGameContainer app = 
			new AppGameContainer(new Core());
 
         app.setDisplayMode(800, 600, false);
         app.start();
        
    }
}
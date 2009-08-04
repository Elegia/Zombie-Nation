package game;

import org.newdawn.slick.Image;

import engine.Vector2D;
import org.newdawn.slick.geom.Vector2f;

public class Zombie {

	private String name;
	private int health;
	
	private Image texture;
	private double x;
	private double y;
	private float angle;	
	
	private Vector2D lastAimVector;	
	
	public Zombie(String name, Image texture, double x, double y, float angle) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.angle = angle;
		texture.setCenterOfRotation(5, 7);		
		this.lastAimVector = new Vector2D(x, y);
	}
	
	public String getName() {
		return name;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setLastAimVector(Vector2D vec) {
		this.lastAimVector = vec;
	}
	
	public float getAngle() {
		return texture.getRotation();
	}
	
	public void setRotation(float angle) {
		texture.setRotation(angle);
		this.angle = angle;
	}
	
	public void draw() {
		texture.draw((float)x, (float)y);
		
		// draw the healthbar
		
	}

	public void facePlayer(Player player) {		
		
		/*
		// Establish a base vector
		double bX = x;
		double bY = y - 1;		
		//Vector2D b = new Vector2D(x, y-1);
		Vector2f b = new Vector2f((float)x, (float)y-1);
		b = b.normalise();
		
		// Calculate the dot product of the player vector and the base vector
		double pX = player.getX();
		double pY = player.getY();
		
		Vector2f p = new Vector2f((float)player.getX(), (float)player.getY());
		p = p.normalise();
		//Vector2D p = new Vector2D(player.getX(), player.getY());

		
		double dotProduct = p.getX()*b.getX() + p.getY()*b.getY();
		
		System.out.println("dotproduct: " + dotProduct);
		//double angle = Math.acos(dotProduct) * (180/Math.PI);
		
		
		
		double vecPLength = Math.sqrt(pX*pX + pX*pX);
		double vecBLength = Math.sqrt(bY*bY + bY*bY);
		
		double angle = Math.acos(dotProduct/(vecPLength * vecBLength)) * (180/Math.PI);
		
		
		setRotation((float)(int)angle);
		
		//cos^-1(a.b/|a||b|)*(180/pi) 
		
		*/
		
		Vector2f o = new Vector2f(0, 0);
		Vector2D a = new Vector2D(player.getX(), player.getY());
		Vector2D b = new Vector2D(x, y);
		
		double aXb = a.getX() * b.getX() + a.getY() * b.getY();
		double aLength = a.getX()*a.getX() + a.getY()*a.getY();
		double bLength = b.getX()*b.getX() + b.getY()*b.getY();
		
		double cos = aXb/Math.sqrt(aLength*bLength);
		double sin = a.getX()*b.getY() - a.getY()*b.getX();			
		
		double result = Math.acos(cos);
		
		System.out.println("angle: " + result);
		setRotation((float)(result * 180/Math.PI));
		/*
		double x1 = player.getX();
		double y1 = player.getY();
		
		double x2 = x;
		double y2 = y;
		double result = Math.atan2(Math.abs(x1*y2-y1*x2), Math.abs(x1*x2+y1*y2));
		*/
		
		
		/*
		 x1 = 3; y1 = 5;
		 x2 = 5; y2 = 6;
		 ang = atan2(abs(x1*y2-y1*x2),x1*x2+y1*y2);
		 */
		
		/*
		Vector2f t1 = new Vector2f(a.getX() - o.getX(), a.getY() - o.getY());
		Vector2f t2 = new Vector2f(b.getX() - o.getX(), b.getY() - o.getY());
			*/	
		
		/*
		double x1 = player.getX();
		double y1 = player.getY();
		
		double x2 = x;
		double y2 = y;
		
		double alen = Math.sqrt(x1 * x1 + y1 * y1);
		double blen = Math.sqrt(x2 * x2 + y2 * y2);
		double piadj = 180/3.14159265;
		double dotproduct = x1 * x2 + y1 * y2;
		double acosth = (blen > 0) ? dotproduct/blen : 0;
		double costh = (alen > 0) ? acosth/alen : 0;
		
		//costh = dotproduct/blen*alen;
		double theta = Math.acos(costh)*piadj;
		*/
			
		/*
			1 - Dot product, divided by norms, gives cosine.
			2 - Cross product, divided by norms, gives sine.
			3 - Give sine and cosine to a 2-argument arctangent function.
		*/
	}
	
}

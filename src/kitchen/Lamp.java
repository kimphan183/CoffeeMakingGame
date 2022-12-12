package kitchen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;


public class Lamp  {
	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img, img2;
	private boolean lightOn = false;


	public Lamp(double x, double y,  double s) {
		xPos = x;
		yPos = y;
		scale = s;
		img = ImageLoader.loadImage("assets/lamp-on-transparent.png");
	}
 
	
	public void draw(Graphics2D g2) {

		AffineTransform transform = g2.getTransform(); 
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		
		if (!lightOn){ 

			img2 = ImageLoader.loadImage("assets/lamp-off-transparent.png");
			g2.drawImage(img2, -img2.getWidth()/2, -img2.getHeight()/2, null);
			
		}
		g2.setTransform(transform);
	}
	
	public void setLightOn(boolean on){
		lightOn = on;
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
}

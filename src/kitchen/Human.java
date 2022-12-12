package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class Human {
	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img;
	
	public Human(double x, double y, double s) {
		xPos = x;
		yPos = y;
		scale = s;
		img = ImageLoader.loadImage("assets/girl-asking.png");
	}
	
	public void draw(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if (x > (xPos - ((double) img.getWidth()) / 2 * scale) && x < (xPos + ((double) img.getWidth())/2*scale) && 
				y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	public void setHumanImg(int humanState) {

	    if (humanState == 0)

	        img = ImageLoader.loadImage("assets/girl-asking.png");  //human asking for coffee

	    else if (humanState == 1)

	        img = ImageLoader.loadImage("assets/girl-drinking.png");   //human drinking coffee
	    


	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}

}

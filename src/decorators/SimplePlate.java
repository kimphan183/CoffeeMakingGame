package decorators;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import kitchen.Human;
import util.ImageLoader;

public class SimplePlate implements Plate {
	protected double xPos;
	protected double yPos;
	protected double scale;

	protected BufferedImage img;
	
	public SimplePlate(double x, double y, double sca) {
		xPos = x;
		yPos = y;
		scale = sca;
		img = loadImage("assets/plate.png");

	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if(x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	public boolean hitHuman(Human human) {
		double x  = human.getX();
		double y = human.getY();
		if (Math.abs(xPos - x) < 50 && Math.abs(yPos - y) < 30)
			return true;
		return false;
	}
	
	public void setPlateImg(int plateState) {

	    if (plateState == 0)

	        img = ImageLoader.loadImage("assets/plate.png");  //empty plate

	    else if (plateState == 1)

	        img = ImageLoader.loadImage("assets/plate-w-coffee.png");  //plate with black coffee cup
	    
	    else if (plateState == 2)

	        img = ImageLoader.loadImage("assets/plate-w-milk.png");
	    
	    else if (plateState == 3)

	        img = ImageLoader.loadImage("assets/plate-w-whip.png");
	    
	    else if (plateState == 4)

	        img = ImageLoader.loadImage("assets/plate-w-cin.png");

	}
	
	public void draw(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public void setPos(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	
	public void setX(double x){
		xPos = x;
	}
	
	public void setY(double y){
		yPos = y;
	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
}
	
	

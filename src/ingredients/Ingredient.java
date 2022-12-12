package ingredients;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import decorators.Plate;

public abstract class Ingredient {
	protected double xPos;
	protected double yPos;
	protected double scale;

	protected BufferedImage img;
	
	public Ingredient(double x, double y, double sca) {
		xPos = x;
		yPos = y;
		scale = sca;
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if(x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	public boolean hitPlate(Plate plate) {
		double x  = plate.getX();
		double y = plate.getY();
		if (Math.abs(xPos - x) < 50 && Math.abs(yPos - y) < 30)
			return true;
		return false;
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

}

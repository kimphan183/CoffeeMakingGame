package kitchen;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import decorators.Plate;
import util.ImageLoader;

public class Cup {
	protected double xPos;
	protected double yPos;
	protected double scale;

	protected BufferedImage img;
	
	public Cup(double x, double y, double sca) {
		xPos = x;
		yPos = y;
		scale = sca;
		img = loadImage("assets/cup-transparent.png");

	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if(x > (xPos - ((double) img.getWidth())/2*scale) && x < (xPos + ((double) img.getWidth())/2*scale) && y > (yPos - ((double) img.getHeight())/2*scale) && y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
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
	
	public boolean hitMachine(Machine machine) {
		double x  = machine.getX();
		double y = machine.getY();
		if (Math.abs(xPos - x) < 50 && Math.abs(yPos - y) < 30)
			return true;
		return false;
	}
	
	public boolean hitPlate(Plate plate) {
		double x  = plate.getX();
		double y = plate.getY();
		if (Math.abs(xPos - x) < 50 && Math.abs(yPos - y) < 30)
			return true;
		return false;
	}
	

	
	
	public void setX(double x){
		xPos = x;
	}
	
	public void setY(double y){
		yPos = y;
	}
	
	public void setCupImg(int cupState) {
		if (cupState == 0)
			img = ImageLoader.loadImage("assets/cup-transparent.png");
		else if (cupState == 1)
			img = ImageLoader.loadImage("assets/cup-coffee-transparent.png");
		else if (cupState == 2)
			img = ImageLoader.loadImage("assets/cup-full.png");
	}
}
	
	
package kitchen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class Machine  {
	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img;
	
	public Machine(double x, double y, double s) {
		xPos = x;
		yPos = y;
		scale = s;
		img = ImageLoader.loadImage("assets/machine-transparent.png");
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
	
	public void setMachineImg(int machineState) {

	    if (machineState == 0)

	        img = ImageLoader.loadImage("assets/machine-transparent.png");  //coffee machine

	    else if (machineState == 1)

	        img = ImageLoader.loadImage("assets/machine-green-transparent.png");   //hit the red button to turn on the machine

	    else if (machineState == 2)

	        img = ImageLoader.loadImage("assets/machine-cup-transparent.png"); //machine with a cup
	    
	    else if (machineState == 3)

	        img = ImageLoader.loadImage("assets/machine-dripping-transparent.png"); //machine is dripping coffee
	    
	    else if (machineState == 4)

	        img = ImageLoader.loadImage("assets/machine-coffee-transparent.png"); //machine with a cup of coffee

	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}

}
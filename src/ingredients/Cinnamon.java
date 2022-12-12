package ingredients;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import util.ImageLoader;

public class Cinnamon extends Ingredient{
	
	public Cinnamon(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/cinnamon-transparent.png");

	}
	
}
	
	

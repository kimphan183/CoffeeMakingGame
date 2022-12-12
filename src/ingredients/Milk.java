package ingredients;

import static util.ImageLoader.loadImage;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import decorators.Plate;
import util.ImageLoader;

public class Milk extends Ingredient{

	
	public Milk(double x, double y, double sca) {
		super(x,y,sca);
		img = loadImage("assets/milk-transparent.png");
	}	
}
	
	

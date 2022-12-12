package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class BubbleText {
	private double xPos;
	private double yPos;
	private double scale;
	private BufferedImage img;
	
	public BubbleText(double x, double y, double s) {
		xPos = x;
		yPos = y;
		scale = s;
		img = ImageLoader.loadImage("assets/girl-text.png");
	}
	
	public void drawText(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
}

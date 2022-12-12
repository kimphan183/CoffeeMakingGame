package kitchen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import processing.core.PApplet;
import util.Util;

public class DrinkingFX {
	private float xPos, yPos;
	private int width, height;

	private float xstart;
	private float xnoise;
	private float ynoise;
	private PApplet pa;
	
	public DrinkingFX(float x , float y, int w, int h) {
		xPos = x;
		yPos = y;
		setWidth(w);
		setHeight(h);
		xstart = Util.random(6);
		xnoise = xstart;
		ynoise = Util.random(6);
		pa = new PApplet();
	}
	
	public void drawDrinkingFX(Graphics2D g2) {
		float noiseFactor;
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);

		for(int y=0; y <=getHeight(); y += 5) {
			ynoise += 0.1;
			xnoise = xstart;
			for(int x= 0; x<=getWidth(); x+=5) {
				xnoise+= 0.1;
				noiseFactor = pa.noise(xnoise,ynoise);

				AffineTransform at1 = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor*Util.radians(630));
				float edgeSize = noiseFactor * 30;
				int grey = (int) (20 + (noiseFactor*99));
				int alph = (int) (40 +(noiseFactor*99));
				g2.setColor(new Color(grey,grey,grey,alph));
				g2.fill(new Ellipse2D.Float(-edgeSize/2, -edgeSize/2, edgeSize, edgeSize/2*noiseFactor));
				g2.setTransform(at1);
			}

		}
		g2.setTransform(at);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}

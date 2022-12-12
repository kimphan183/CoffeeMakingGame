package kitchen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import processing.core.PApplet;
import util.Util;
import java.awt.geom.Ellipse2D;

public class Steam {
	private float xPos, yPos;
	private int width, height;

	private float xstart;
	private float xnoise;
	private float ynoise;
	private PApplet pa;

	public Steam(float x , float y, int w, int h) {
		xPos = x;
		yPos = y;
		setWidth(w);
		setHeight(h);
		xstart = Util.random(5);
		xnoise = xstart;
		ynoise = Util.random(5);
		pa = new PApplet();
	}
	
	public void drawSteam(Graphics2D g2) {
		float noiseFactor;
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);

		for(int y=0; y <=getHeight(); y += 4) {
			ynoise += 0.1;
			xnoise = xstart;
			for(int x= 0; x<=getWidth(); x+=4) {
				xnoise+= 0.1;
				noiseFactor = pa.noise(xnoise,ynoise);

				AffineTransform at1 = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor*Util.radians(230));
				float edgeSize = noiseFactor * 15;
				int grey = (int) (10 + (noiseFactor*95));
				int alph = (int) (30 +(noiseFactor*95));
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

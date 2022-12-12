package kitchen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.CoffeePanel;
import util.ImageLoader;

public class Kitchen {
	private BufferedImage img;

	public Kitchen(String file) {
		img = ImageLoader.loadImage(file);
	}

	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(-90, 0);
		g2.scale(1.25, 1.25);
		g2.drawImage(img, 0, 0, CoffeePanel.W_WIDTH, CoffeePanel.W_HEIGHT, null);
		
		g2.setTransform(at);
	}
	
	public void drawIntro(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("This is a simple game that will help you learn how to make a basic latte.", 200, 400);
		  g2.drawString("There are only two main interactions: Click & Drag.", 280, 450);
		  g2.drawString("Enjoy the game!", 440, 500);
		  g2.setTransform(at);
		}
	
	public void drawStepOne(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Turn on the light by clicking on the", 650, 200);
		  g2.drawString("LAMP BASE", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepTwo(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Turn on the coffee machine by clicking", 650, 200);
		  g2.drawString("on the RED button", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepThree(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Drag the cup into the MIDDLE of", 650, 200);
		  g2.drawString("the coffee machine", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepFour(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Press the GREEN BUTTON on the coffee", 650, 200);
		  g2.drawString("machine to make espresso", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepFive(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Please wait for a few seconds...", 650, 200);
		  g2.setTransform(at);
		}
	
	public void drawStepSix(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Drag the espresso cup ONTO the", 650, 200);
		  g2.drawString("plate", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepSeven(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Drag the milk to the cup to add milk", 650, 200);
		  g2.setTransform(at);
		}
	
	public void drawStepEight(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Drag the whipping cream to the cup to add", 650, 200);
		  g2.drawString("whipping cream", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepNine(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Drag the cinnamon bottle to the cup to", 650, 200);
		  g2.drawString("add cinnamon powder", 650, 250);
		  g2.setTransform(at);
		}
	
	public void drawStepTen(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Drag the coffee cup to the girl", 650, 200);
		  g2.setTransform(at);
		}
	
	public void drawStepEleven(Graphics2D g2) {
		  AffineTransform at = g2.getTransform();
		  g2.setColor(Color.BLACK);
		  Font f = new Font("Arial", Font.PLAIN, 20);

		  g2.setFont(f);
		  g2.drawString("Please wait...", 650, 200);
		  g2.setTransform(at);
		}
	
//	public void drawEnding(Graphics2D g2) {
//		  AffineTransform at = g2.getTransform();
//		  g2.setColor(Color.BLACK);
//		  Font f = new Font("Arial", Font.PLAIN, 20);
//
//		  g2.setFont(f);
//		  g2.drawString("Congrats", 200, 400);
//		  g2.setTransform(at);
//		}
	
	public void setKitImg(int kitState) {
		
		if (kitState == -1)

	        img = ImageLoader.loadImage("assets/start-screen.png");   //start screen

		else if (kitState == 0)

	        img = ImageLoader.loadImage("assets/kitchen-night.png");  //night bg

	    else if (kitState == 1)

	        img = ImageLoader.loadImage("assets/kitchen-day.png");   //day bg
	    
	    else if (kitState == 2)

	        img = ImageLoader.loadImage("assets/end-screen.png");   //end screen



	}

}

/* Use decorator pattern for decorating coffee cup stage
 * Use factory pattern for Milk, Whip, and Cinnamon objects
 * Follow strict encapsulation, and have 2 inclusion polymorphism
 * Draw everything in the game
 * FSM states
 * Shifting between day & night as the light turning on/off
 * Use 2 Perlin noise animation
 * 
 */


package main;

import static util.ImageLoader.loadImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import decorators.CinDecorator;
import decorators.MilkDecorator;
import decorators.Plate;
import decorators.SimplePlate;
import decorators.WhipDecorator;
import ingredients.Cinnamon;
import ingredients.Ingredient;
import ingredients.IngredientConcreteFactory;
import ingredients.Milk;
import ingredients.Whip;
import kitchen.Kitchen;
import kitchen.Lamp;
import kitchen.Machine;
import kitchen.PlayButton;
import kitchen.ReplayButton;
import kitchen.Steam;
import kitchen.BubbleText;
import kitchen.Cup;
import kitchen.DrinkingFX;
import kitchen.Human;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;

public class CoffeePanel extends JPanel implements ActionListener {
	public static int W_WIDTH = 1050;
	public static int W_HEIGHT = 750;

	
	private double mouseX;
	private double mouseY;

	
	private int state; //state starts from -1
	private Kitchen kitchen;
	private Lamp lamp;
	private Machine machine;
	private Cup cup, cupCoffee;
	private Human human;
	private BubbleText bubbleText;
	
	private Milk milk;
	private Whip whip;
	private Cinnamon cinnamon;
	IngredientConcreteFactory ingredientMaker;
	
	private ArrayList<Ingredient> ingre;
	Plate plate;

	private Timer timer;
	
	//animation for coffee dripping
	private Steam steam;
	private int drippingTimer;
	private boolean nearlyDoneDripping;
	
	
	//animation for drinking coffee
	private DrinkingFX drinkFX;
	private int drinkingTimer;
	private boolean nearlyDoneDrinking;
	
	private PlayButton playButton;
	private ReplayButton replayButton;
	
	
	private Minim minim;
	private AudioPlayer music, turn_on_lamp, turn_on_machine, dripping;
	private MyMouseListener ml;
	private MyMouseMotionListener mml;

	private JFrame frame;
	
	CoffeePanel(JFrame frame) {
		this.frame = frame;
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
		
		state = -1;
		
		drippingTimer = 0;
		nearlyDoneDripping = false;
		
		drinkingTimer = 0;
		nearlyDoneDrinking = false;
		
		kitchen = new Kitchen("assets/kitchen-night.png");
		lamp = new Lamp(W_WIDTH / 2 - 150, 20, 0.2);
		machine = new Machine(W_WIDTH / 2-100, W_HEIGHT / 2 + 100, 0.2);
		cup = new Cup(W_WIDTH / 2 - 300, W_HEIGHT / 2 + 200, 0.2);
		
		
		steam = new Steam((float) machine.getX() - 30, (float) machine.getY()+ 20, 20, 30);
		
		human = new Human (W_WIDTH / 2-430, W_HEIGHT / 2 + 280, 0.2);
		bubbleText = new BubbleText(W_WIDTH / 2-250, W_HEIGHT / 2 + 200, 0.2);
		drinkFX= new DrinkingFX ((float) human.getX(), (float) human.getY()-150, 50, 50);
		
		plate = new SimplePlate (W_WIDTH / 2 + 250, W_HEIGHT / 2 + 300, 0.1);
		
		
		ingredientMaker = new IngredientConcreteFactory();
		ingre = new ArrayList<Ingredient>();
		milk = (Milk) ingredientMaker.createIngredient("milk");
		whip = (Whip) ingredientMaker.createIngredient("whip");
		cinnamon = (Cinnamon) ingredientMaker.createIngredient("cinnamon");
		
		ingre.add(milk);
		ingre.add(whip);
		ingre.add(cinnamon);
		
		playButton = new PlayButton(W_WIDTH / 2 - 10, W_HEIGHT / 2 +50 + W_HEIGHT / 3.5, 0.1);
		replayButton = new ReplayButton(W_WIDTH / 2 - 10, W_HEIGHT / 2 +50 + W_HEIGHT / 3.5, 0.1);
		
		minim = new Minim(new MinimHelper());

		music = minim.loadFile("music-loop.mp3");
		turn_on_lamp = minim.loadFile("tap.mp3");
		turn_on_machine = minim.loadFile("turn-on-machine.mp3");
		dripping = minim.loadFile("dripping.mp3");
		
		
		
		ml = new MyMouseListener();
		addMouseListener(ml);
		
		mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);

		timer = new Timer(30, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (state == -1) {
			lamp.setLightOn(false);
			
			kitchen.setKitImg(-1); 
			kitchen.draw(g2);	
			kitchen.drawIntro(g2);
			playButton.draw(g2);
			
		} else if (state == 0) {
			lamp.setLightOn(false);
			
			kitchen.setKitImg(0); //night window
			kitchen.draw(g2);
			kitchen.drawStepOne(g2);
			
			
			lamp.draw(g2);
			

			g2.setColor(new Color(0, 0, 0, 150)); //create darkness
			g2.fill(new Rectangle2D.Double(0, 0, W_WIDTH, W_HEIGHT));
			
			
		} else if (state == 1) {
			lamp.setLightOn(true);
			
			kitchen.setKitImg(1); //morning window
			kitchen.draw(g2);
			kitchen.drawStepTwo(g2);
		
			lamp.draw(g2);
			machine.draw(g2);

			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			
			cup.draw(g2);
			plate.draw(g2);
			
		} else if (state == 2) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepThree(g2);
			
			lamp.draw(g2);
			machine.draw(g2);
			
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			cup.draw(g2);
			plate.draw(g2);
			
		} else if (state == 3) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepFour(g2);
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			plate.draw(g2);
			
		} else if (state == 4) { //dripping animation
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepFive(g2);
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			steam.drawSteam(g2);
			plate.draw(g2);
			
		} else if (state == 5) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepSix(g2);
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			plate.draw(g2);
			
			if (cupCoffee != null) {
				cupCoffee.draw(g2);
			}
			
		} 

		else if (state == 6) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepSeven(g2);
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			plate.draw(g2);
		}
		else if (state == 7) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepEight(g2);
			
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			plate.draw(g2);
		}
		else if (state == 8) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepNine(g2);
			
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			plate.draw(g2);
		}
		else if (state == 9) {
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepTen(g2);
			
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			plate.draw(g2);
			
			//human.setHumanImg(0);
			human.draw(g2);
			bubbleText.drawText(g2);
		}
		else if (state == 10) { //drinking animation
			lamp.setLightOn(true);

			kitchen.setKitImg(1);
			kitchen.draw(g2);
			kitchen.drawStepEleven(g2);
			
			
			lamp.draw(g2);
			machine.draw(g2);
			for (Ingredient i: ingre) {
				i.draw(g2);
			}
			//plate.draw(g2);
			human.setHumanImg(1);
			human.draw(g2);
			
			drinkFX.drawDrinkingFX(g2);
		}
		else if (state == 11) { 
			kitchen.setKitImg(2); 
			kitchen.draw(g2);	
			replayButton.draw(g2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (state == 4) {
			steam.setWidth(drippingTimer/2);
			steam.setHeight(drippingTimer/2);
			if (!nearlyDoneDripping) {
				drippingTimer++;
				if (drippingTimer >= 80) { //correct:80
					nearlyDoneDripping = true;
					drippingTimer = 80;
				}
			} else {
				drippingTimer--;
				if (drippingTimer <= 0 ) {
					state = 5;
					machine.setMachineImg(4);

				}
			}
		}
		
		if (state == 10) {
			drinkFX.setWidth(drinkingTimer/2);
			drinkFX.setHeight(drinkingTimer/2);
			if (!nearlyDoneDrinking) {
				drinkingTimer++;
				if (drinkingTimer >= 50) { //correct:80
					nearlyDoneDrinking = true;
					drinkingTimer = 50;
				}
			} else {
				drinkingTimer--;
				if (drinkingTimer <= 0 ) {
					state = 11;
					machine.setMachineImg(4);

				}
			}
		}
		
		repaint();
	}
	
	

	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			if(state == -1 && playButton.clicked(mouseX, mouseY)) {
				state = 0;
			}

			if (state == 0 && lamp.clicked(mouseX, mouseY)) {
				turn_on_lamp.play(0);
				music.loop();

				state = 1;
			} else if(state == 1 && lamp.clicked(mouseX, mouseY)) {
				turn_on_lamp.play(0);
				state = 0;
				
			}
			if (state == 1 && machine.clicked(mouseX, mouseY)) {
				turn_on_machine.play(0);
				machine.setMachineImg(1);
				state = 2;
				
			}
			if (state == 3 && machine.clicked(mouseX, mouseY)) {
				turn_on_machine.play(0);
				dripping.play(0);
				machine.setMachineImg(3);
				state = 4;
			}
			
			if (state == 5 && machine.clicked(mouseX, mouseY)) {
				state = 6;
			}
			
			if(state == 11 && replayButton.clicked(mouseX, mouseY)) {
				music.mute();
				removeMouseListener(ml);
				removeMouseMotionListener(mml);
				frame.dispose();
				frame = new CoffeeApp("coffeeApp");
			}
			
		}
	}
	
	public class MyMouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if (state == 2 && cup.clicked(e.getX(), e.getY())) {
				cup.setCupImg(0);
				cup.setPos(e.getX(), e.getY());
			
				if (cup.hitMachine(machine)) {
					state = 3;
					machine.setMachineImg(2);
				}
			}
			
			if (state == 5 && machine.clicked(e.getX(), e.getY())) {
				
				machine.setMachineImg(0);
				//cupCoffee.setPos(e.getX(), e.getY());
				cupCoffee = new Cup (e.getX(), e.getY(), 0.1);
				cupCoffee.setCupImg(1);
				
			}
			
			if (state == 5 && cupCoffee != null) {
				cupCoffee.setPos(e.getX(), e.getY());
				
				if (cupCoffee.hitPlate(plate)) {
					state = 6;
					plate.setPlateImg(1);
				}
			}
			
			if (state == 6 && milk.clicked(e.getX(), e.getY())) {
				milk.setPos(e.getX(), e.getY());
			
				if (milk.hitPlate(plate)) {
					state = 7;
					plate = new MilkDecorator(plate);
					milk.setPos(W_WIDTH / 2 + 150, W_HEIGHT / 2 + 120);
				}
			}
			
			if (state == 7 && whip.clicked(e.getX(), e.getY())) {
				whip.setPos(e.getX(), e.getY());
			
				if (whip.hitPlate(plate)) {
					state = 8;
					plate = new WhipDecorator(plate);
					whip.setPos(W_WIDTH / 2 + 290, W_HEIGHT / 2 + 105);
				}
			}
			
			if (state == 8 && cinnamon.clicked(e.getX(), e.getY())) {
				cinnamon.setPos(e.getX(), e.getY());
			
				if (cinnamon.hitPlate(plate)) {
					state = 9;
					plate = new CinDecorator(plate);
					cinnamon.setPos(W_WIDTH / 2 + 400, W_HEIGHT / 2 + 150);
					
				}
			}
			
			if (state == 9 && plate.clicked(e.getX(), e.getY())) {
				plate.setPos(e.getX(), e.getY());
				
				if (plate.hitHuman(human)) {
					state=10;
					human.setHumanImg(1);
				}
			}
			
			
		}
	}

}

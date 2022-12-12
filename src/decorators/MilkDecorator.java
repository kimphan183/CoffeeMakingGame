package decorators;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MilkDecorator extends PlateDecorator{
	
	
	public MilkDecorator (Plate basePlate) {
		super(basePlate);
	}
	
	public void draw(Graphics2D g2) {
		super.draw(g2);
		addMilk();
	}
	
	private void addMilk() {
		super.setPlateImg(2);
		
	}

}

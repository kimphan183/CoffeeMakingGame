package decorators;

import java.awt.Graphics2D;

public class WhipDecorator extends PlateDecorator{
	
	public WhipDecorator (Plate basePlate) {
		super(basePlate);
	}
	
	public void draw(Graphics2D g2) {
		super.draw(g2);
		addWhip();
	}
	
	private void addWhip() {
		super.setPlateImg(3);
		
	}

}

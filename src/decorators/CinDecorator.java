package decorators;

import java.awt.Graphics2D;

public class CinDecorator extends PlateDecorator{
	
	public CinDecorator (Plate basePlate) {
		super(basePlate);
	}
	
	public void draw(Graphics2D g2) {
		super.draw(g2);
		addCin();
	}
	
	private void addCin() {
		super.setPlateImg(4);
		
	}

}

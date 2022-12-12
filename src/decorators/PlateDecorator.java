package decorators;

import java.awt.Graphics2D;

import kitchen.Human;


public class PlateDecorator implements Plate{
	protected Plate basePlate;
	
	public PlateDecorator(Plate basePlate) {
		this.basePlate = basePlate;
	}

	public void draw(Graphics2D g2) {
		basePlate.draw(g2);
	}
	
	public void setPlateImg(int plateState) {
		basePlate.setPlateImg(plateState);
	}
	
	public double getX() {
		return basePlate.getX();
	}
	
	public double getY() {
		return basePlate.getY();
	}

	@Override
	public boolean clicked(double x, double y) {
		return basePlate.clicked(x,y);
	}

	@Override
	public boolean hitHuman(Human human) {
		return basePlate.hitHuman(human);
	}

	@Override
	public void setX(double x) {
		basePlate.setX(x);
		
	}

	@Override
	public void setY(double y) {
		basePlate.setY(y);
		
	}

	@Override
	public void setPos(int x, int y) {
		basePlate.setPos(x,y);
		
	}
}

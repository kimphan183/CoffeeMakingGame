package decorators;

import java.awt.Graphics2D;

import kitchen.Human;

public interface Plate {
	public void draw(Graphics2D g2);
	public void setPlateImg(int plateState);
	
	public boolean clicked(double x, double y);
	public boolean hitHuman(Human human);
	
	public void setX(double x);
	public void setY(double y);
	public void setPos(int x, int y);
	
	public double getX();
	public double getY();
}

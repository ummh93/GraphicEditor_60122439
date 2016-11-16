package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.GConstants.EDrawingType;
import shapes.Anchors.EAnchors;

public class GRectangle extends GShape {
	//private int x, y, w, h;
	public Rectangle rectangle;
	
	public GRectangle() {
		super(EDrawingType.TP, new Rectangle(0, 0, 0, 0));
		this.rectangle = (Rectangle)this.getShape();
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.rectangle.setLocation(x, y);
		this.draw(g2D);	// shape�� �ִ� draw���, �׸��� �κ�
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// �׸��� ����� �κ�
		this.draw(g2D); 
		this.rectangle.setSize(new Dimension((x - this.rectangle.x), (y - this.rectangle.y)));
		// �ٽ� �׸��� �κ�
		this.draw(g2D);	 
	}
	public void continueDrawing(int x, int y, Graphics2D g2D) {
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}
	@Override
	public void draw(Graphics2D g2D) {
		g2D.draw(this.rectangle);
		if(cursorState ==EAnchors.MM)
			this.getAnchors().draw(g2D, this.rectangle.getBounds());	
	}
}

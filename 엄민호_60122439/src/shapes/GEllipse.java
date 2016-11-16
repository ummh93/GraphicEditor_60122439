package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import constants.GConstants.EDrawingType;
import shapes.Anchors.EAnchors;

public class GEllipse extends GShape {
	// private int x, y, w, h;
	private Ellipse2D.Double ellipse;

	public GEllipse() {
		super(EDrawingType.TP, new Ellipse2D.Double(0, 0, 0, 0));
		this.ellipse = (Ellipse2D.Double)getShape();
	}

	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.ellipse.setFrame(x, y, 0, 0);
	}

	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		ellipse.width = x - ellipse.x;
		ellipse.height = y - ellipse.y;
		this.draw(g2D);		
	}

	public void continueDrawing(int x, int y, Graphics2D g2D) {
	}

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}

	@Override
	public void draw(Graphics2D g2D) {
		g2D.draw(this.ellipse);
		if(cursorState == EAnchors.MM)
			this.getAnchors().draw(g2D, this.ellipse.getBounds());
	}

}

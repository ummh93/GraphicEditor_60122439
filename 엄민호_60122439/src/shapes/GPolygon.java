package shapes;

import java.awt.Graphics2D;
import java.awt.Polygon;

import constants.GConstants.EDrawingType;
import shapes.Anchors.EAnchors;

public class GPolygon extends GShape {
	private Polygon polygon;
	public GPolygon() {
		super(EDrawingType.NP, new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
		this.draw(g2D);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
		this.draw(g2D);
	}
	public void continueDrawing(int x, int y, Graphics2D g2D) {
		g2D.draw(this.polygon);
		this.polygon.addPoint(x, y);
		g2D.draw(this.polygon);
		polygon.addPoint(x, y);
		
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
		g2D.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		draw(g2D);
	}
	@Override
	public void draw(Graphics2D g2D) {
		g2D.draw(this.polygon);
		if(cursorState ==EAnchors.MM)
			this.getAnchors().draw(g2D, this.polygon.getBounds());
	}
}

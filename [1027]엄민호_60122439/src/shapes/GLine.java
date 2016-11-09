package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import constants.GConstants.EDrawingType;

public class GLine extends GShape {
	private Line2D line;
	//private int x1, y1, x2, y2;
	public GLine() {
		super(EDrawingType.TP);
		this.line = new Line2D.Double(0, 0, 0, 0);
		this.shape = this.line;
	}
	@Override
	public void initDrawing(int x, int y, Graphics2D g2D) {
		line.setLine(x, y, x, y);
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		this.draw(g2D);
		this.line.setLine(this.line.getX1(), this.line.getY1(), x, y);
		this.draw(g2D);
	}
	public void continueDrawing(int x, int y, Graphics2D g2D) {
	}
	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}
	@Override
	public void draw(Graphics2D g2D) {
		g2D.draw(line);
	}
}

package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

abstract public class GShape {
	private EDrawingType eDrawingType;
	private Shape shape;
	private Anchors anchors;	
	// working variables
	private Point p0, p1;	// p0�� ���� p1�� �� ����
	protected boolean selected;
	private EAnchors currentEAnchor;
	
	public EDrawingType geteDrawingType() { return eDrawingType; }
	public void seteDrawingType(EDrawingType eDrawingType) { this.eDrawingType = eDrawingType; }
	public boolean isSelected(){ return selected; }
	public void setSelected(boolean selected, Graphics2D g2D){ this.selected = selected; }
	
	public Shape getShape() { return this.shape; }
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	
	public Point getP0() {return p0;}
	public void setP0(int x, int y) {this.p0.x = x; this.p0.y = y;}
	public Point getP1() {return p1;}
	public void setP1(int x, int y) {this.p1.x = x; this.p1.y = y;}
	
	public EAnchors getCurrentEAnchor() {return currentEAnchor;}
	
	public Rectangle getBounds() {
		return this.shape.getBounds();
	}
	
	public EAnchors cursorState;
	public EAnchors getCursorState(){ return cursorState;}
	public void setCursorState(EAnchors cursorState){ this.cursorState = cursorState;}
	
	
	public Cursor getCursor(GShape shape) {
		switch (shape.getCursorState()) {
		case MM: return (new Cursor(Cursor.MOVE_CURSOR));
		default:
			break;
		}
		return (new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public GShape(EDrawingType eDrawingType, Shape shape) {
		this.eDrawingType = eDrawingType;
		this.selected = false;
		this.shape = shape;
		this.anchors = new Anchors();
		this.p0 = new Point(0, 0);
		this.p1 = new Point(0, 0);
	}
	
	public void draw(Graphics2D g2D){
		g2D.draw(this.shape);
		// ���õ� ��츸 anchor�� �׷��ش�.
		if(this.selected){
			this.anchors.draw(g2D, this.shape.getBounds());
		}
	}
	
	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GConstants.EAnchors contains(int x, int y) {
		this.currentEAnchor = null;
		if (this.selected) {
			this.currentEAnchor = this.anchors.contains(x, y);
			if (this.currentEAnchor != null)
				return this.currentEAnchor;
		}
		if (this.shape.getBounds().contains(x, y)) {
			
			this.currentEAnchor = EAnchors.MM;
		}
		return this.currentEAnchor;
	}

	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);
	
	abstract public void initTransforming(int x, int y, Graphics2D g2d);
	abstract public void keepTransforming(int x, int y, Graphics2D g2d);
	abstract public void finishTransforming(int x, int y, Graphics2D g2d);

	abstract public void initResizing(int x, int y, Graphics2D g2d);
	abstract public void keepResizing(int x, int y, Graphics2D g2d);
	abstract public void finishResizing(int x, int y, Graphics2D g2d);
	
}

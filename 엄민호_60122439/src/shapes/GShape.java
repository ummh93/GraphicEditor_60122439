package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import constants.GConstants.EDrawingType;
import shapes.Anchors.EAnchors;

abstract public class GShape {
	private EDrawingType eDrawingType;

	private Shape shape;
	private Anchors anchors;	
	protected boolean selected;
	public EDrawingType geteDrawingType() { return eDrawingType; }
	public void seteDrawingType(EDrawingType eDrawingType) { this.eDrawingType = eDrawingType; }
	public boolean isSelected(){ return selected; }
	public void setSelected(boolean selected, Graphics2D g2D){ this.selected = selected; }
	
	public Shape getShape() { return this.shape; }
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}

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
	}
	
	public void draw(Graphics2D g2D){
		g2D.draw(this.shape);
		// 선택된 경우만 anchor를 그려준다.
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
	
	public GShape contains(int x, int y){
		if(selected){
			cursorState = anchors.contains(x, y);
			if(cursorState != null){
				return this;
			}
		}
		if(shape.intersects(x, y, 10, 10)){
			cursorState = EAnchors.MM;
			return this;
		}
		return null;
	}

	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);

	
}

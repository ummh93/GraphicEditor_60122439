package shapes;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;

import constants.GConstants.EDrawingType;
import shapes.Anchors.EAnchors;

abstract public class GShape {
	protected Shape shape;
	protected boolean selected = false;
	private EDrawingType eDrawingType;

	public EDrawingType geteDrawingType() { return eDrawingType; }
	
	public void seteDrawingType(EDrawingType eDrawingType) {
		this.eDrawingType = eDrawingType;
	}
	
	private Anchors anchors;	
	public Anchors getAnchors() {return anchors;}
	public void setAnchors(Anchors anchors) {this.anchors = anchors;}
	
	public EAnchors cursorState;
	public EAnchors getCursorState(){ return cursorState;}
	public void setCursorState(EAnchors cursorState){ this.cursorState = cursorState;}
	
	public GShape(EDrawingType eDrawingType) {
		this.eDrawingType = eDrawingType;
		this.anchors = new Anchors();
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
		if(this.selected){
			anchors = new Anchors();
		}
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

	abstract public void draw(Graphics2D g2D);
	abstract public void initDrawing(int x, int y, Graphics2D g2D);
	abstract public void keepDrawing(int x, int y, Graphics2D g2D);
	abstract public void continueDrawing(int x, int y, Graphics2D g2D);
	abstract public void finishDrawing(int x, int y, Graphics2D g2D);

	public GShape clone() {
		try {
			return this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Cursor getCursor(GShape shape) {
		switch (shape.getCursorState()) {
		case MM: return (new Cursor(Cursor.MOVE_CURSOR));
		default:
			break;
		}
		return (new Cursor(Cursor.DEFAULT_CURSOR));
	}
}

package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;

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
		this.draw(g2D);	// shape에 있는 draw사용, 그리는 부분
	}
	@Override
	public void keepDrawing(int x, int y, Graphics2D g2D) {
		// 그린걸 지우는 부분
		this.draw(g2D); 
		this.rectangle.setSize(new Dimension((x - this.rectangle.x), (y - this.rectangle.y)));
		// 다시 그리는 부분
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
	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.setP1(x, y);
		this.draw(g2d);		
	}
	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		this.rectangle.x += x - this.getP1().x;
		this.rectangle.y += y - this.getP1().y;
		this.draw(g2d);
		this.setP1(x, y);		
	}
	@Override
	public void finishTransforming(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initResizing(int x, int y, Graphics2D g2d) {
		this.setP1(x, y);
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		default:
			break;
		}		
	}
	@Override
	public void keepResizing(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		switch (this.getCurrentEAnchor()) {
		case NN:
			
			this.rectangle.y = this.getP1().y;
			this.rectangle.height += this.getP1().y-y;			
			break;
		case NE:
			this.rectangle.y = this.getP1().y;
			this.rectangle.height += this.getP1().y - y;	
			this.rectangle.width += x - this.getP1().x;
			break;
		case NW:
			this.rectangle.x = this.getP1().x;
			this.rectangle.y = this.getP1().y;
			this.rectangle.width += this.getP1().x -x;
			this.rectangle.height += this.getP1().y - y;
			break;
		case SS:
			this.rectangle.height += y - this.getP1().y;	
			break;
		case SE:
			this.rectangle.width += x - this.getP1().x;
			this.rectangle.height += y - this.getP1().y;				
			break;
		case SW:
			this.rectangle.x = this.getP1().x;
			this.rectangle.width += this.getP1().x -x;
			this.rectangle.height += y - this.getP1().y;	
			break;
		case EE:
			this.rectangle.width += x - this.getP1().x;
			break;
		case WW:
			this.rectangle.x = this.getP1().x;
			this.rectangle.width += this.getP1().x -x;
			break;
		default:
			break;
		}
		this.draw(g2d);
		this.setP1(x, y);		
	}
	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		switch (this.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			break;
		case NW:
			break;
		case SS:
			break;
		case SE:
			
			break;
		case SW:
			break;
		case EE:
			break;
		case WW:
			break;
		default:
			break;
		}		
	}
}

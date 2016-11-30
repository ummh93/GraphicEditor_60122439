package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import constants.GConstants.EDrawingType;

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

	@Override
	public void finishDrawing(int x, int y, Graphics2D g2D) {
	}

	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.setP1(x, y);
		this.draw(g2d);		
	}

	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		this.ellipse.setFrame(x-this.ellipse.getWidth()/2,y-this.ellipse.getHeight()/2, this.ellipse.getWidth(), this.ellipse.getHeight());
		this.draw(g2d);
		this.setP1(x,y);		
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
			this.ellipse.setFrame(this.ellipse.getX(),y,this.ellipse.getWidth(),this.ellipse.getY()-y+this.ellipse.getHeight());
			break;
		case NE:
			this.ellipse.setFrame(this.ellipse.getX(), y, x-this.ellipse.getX(), this.ellipse.getY()-y+this.ellipse.getHeight());
			break;
		case NW:
			this.ellipse.setFrame(x, y, this.ellipse.getX()-x+this.ellipse.getWidth(),this.ellipse.getY()-y+this.ellipse.getHeight());
			break;
		case SS:
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), this.ellipse.getWidth(), y-this.ellipse.getY());
			break;
		case SE:
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), y-this.ellipse.getY());
			break;
		case SW:
			this.ellipse.setFrame(x, this.ellipse.getY(), this.ellipse.getX()-x+this.ellipse.getWidth(), y-this.ellipse.getY());
			break;
		case EE:
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), this.ellipse.getHeight());
			break;
		case WW:
			this.ellipse.setFrame(x, this.ellipse.getY(), this.ellipse.getX()-x+this.ellipse.getWidth(), this.ellipse.getHeight());
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

	@Override
	public void continueDrawing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}

}

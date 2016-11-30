package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import constants.GConstants.EDrawingType;

public class GLine extends GShape {
	private Line2D line;
	//private int x1, y1, x2, y2;
	// ��ǥ�� ����ְ� draw�� shape���� �÷��ش�.
	public GLine() {
		super(EDrawingType.TP, new Line2D.Double(0, 0, 0, 0));
		this.line = (Line2D.Double)this.getShape();
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
	@Override
	public void initTransforming(int x, int y, Graphics2D g2d) {
		this.setP1(x, y);
		this.draw(g2d);		
	}
	@Override
	public void keepTransforming(int x, int y, Graphics2D g2d) {
		this.draw(g2d);
		this.line.setLine(this.line.getX1()+x - this.getP1().x, this.line.getY1()+y - this.getP1().y, this.line.getX2()+ x - this.getP1().x, this.line.getY2()+y - this.getP1().y);
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
			this.line.setLine(this.line.getX1(),y,this.line.getX2(),this.line.getY2());
			break;
		case NE:
			this.line.setLine(this.line.getX1(),y,this.line.getX2(),this.line.getY2());
			break;
		case NW:
			this.line.setLine(x,y,this.line.getX2(),this.line.getY2());
			break;
		case SS:
			this.line.setLine(this.line.getX1(),this.line.getY1(),this.line.getX2(),y);
			break;
		case SE:
			this.line.setLine(this.line.getX1(),this.line.getY1(),x,y);
			break;
		case SW:
			this.line.setLine(this.line.getX1(),this.line.getY1(),this.line.getX2(),y);
			break;
		case EE:
			this.line.setLine(this.line.getX1(),this.line.getY1(),x,this.line.getY2());
			break;
		case WW:
			this.line.setLine(x,this.line.getY1(),this.line.getX2(),this.line.getY2());
			break;
		default:
			break;
		}
		this.draw(g2d);
		this.setP1(x, y);		
	}
	@Override
	public void finishResizing(int x, int y, Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
}

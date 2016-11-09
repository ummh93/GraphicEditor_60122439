package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

public class Anchors extends Vector<Ellipse2D.Double>{
	private static final long serialVersionUID = 1L;
	public final static int ANCHORWIDTH = 4;
	public final static int ANCHORHEIGHT = 4;
	enum EAnchors { NN, NE, NW, SS, SW, SE, EE, WW, RR, MM }
	
	public Anchors() {
		for (int i = 0; i < EAnchors.values().length; i++){
			this.add(new Ellipse2D.Double(0, 0, ANCHORWIDTH, ANCHORHEIGHT));
		}
	}
	private void computeCoordinates(Rectangle R){
		for (int i = 0; i < EAnchors.values().length; i++){
			switch (EAnchors.values()[i]) {
				case NN:
					this.get(i).x = R.x + R.width / 2 - ANCHORWIDTH / 2;
					this.get(i).y = R.y - ANCHORHEIGHT / 2;
				break;
				case NE:
					this.get(i).x = R.x + R.width - ANCHORWIDTH / 2;
					this.get(i).y = R.y - ANCHORHEIGHT / 2;
					break;
				case NW:
					this.get(i).x = R.x - ANCHORWIDTH / 2;
					this.get(i).y = R.y - ANCHORHEIGHT / 2;
					break;
				case SS:
					this.get(i).x = R.x + R.width / 2 - ANCHORWIDTH / 2;
					this.get(i).y = R.y + R.height - ANCHORHEIGHT / 2;
					break;
				case SW:
					this.get(i).x = R.x - ANCHORWIDTH / 2;
					this.get(i).y = R.y + R.height - ANCHORHEIGHT / 2;
					break;
				case SE:
					this.get(i).x = R.x + R.width - ANCHORWIDTH / 2;
					this.get(i).y = R.y + R.height - ANCHORHEIGHT / 2;
					break;
				case EE:
					this.get(i).x = R.x + R.width - ANCHORWIDTH / 2;
					this.get(i).y = R.y + R.height / 2- ANCHORWIDTH / 2;
					break;
				case WW:
					this.get(i).x = R.x - ANCHORWIDTH / 2;
					this.get(i).y = R.y + R.height / 2 - ANCHORWIDTH / 2;
					break;
				case RR:
					this.get(i).x = R.x + R.width / 2 - ANCHORWIDTH / 2;
					this.get(i).y = R.y + R.height / 4 - ANCHORWIDTH / 2;
					break;
			default:
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2D, Rectangle boundRectangle) {	// 둘러싸고 있는 네모를 의미한다.
		this.computeCoordinates(boundRectangle);
		for (int i = 0; i < EAnchors.values().length-1; i++) {
			g2D.draw(this.get(i));
		}
	}
	
	public EAnchors contains(int x, int y) {
		return null;
	}
}

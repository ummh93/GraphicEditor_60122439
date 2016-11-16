package frames;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants.EDrawingType;
import shapes.GShape;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;

	// object states
	private enum EState {
		idleTP, idleNP,  drawingTP, drawingNP
	};
	private EState eState = EState.idleTP;
	// components
	private Vector<GShape> shapeList;
	// associative attributes
	private GShape selectedShape;
	public void setSelectedShape(GShape selectedShape) {
		this.selectedShape = selectedShape;
		switch (this.selectedShape.geteDrawingType()) {
		case TP: eState = EState.idleTP; break;
		case NP: eState = EState.idleNP; break;
	}
	}

	// working objects;
	private GShape currentShape;

	public GDrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		this.shapeList = new Vector<GShape>();
	}

	public void initialize() {
	}

	public void paint(Graphics g) {
		for (GShape shape : this.shapeList) {
			shape.draw((Graphics2D) g);
		}
	}
	//	어떤 도형이 선택됬는지 모르기 때문에 모든 도형을 false로 했다.
	public void resetSelected() {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		for (GShape shape : this.shapeList){
			shape.setSelected(false, g2D);
		}
		this.repaint();
		this.setOpaque(false);
	}
	
	private void initDrawing(int x, int y) {
		this.resetSelected();
		this.currentShape = this.selectedShape.clone();
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.initDrawing(x, y, g2D);
	}
	
	private void keepDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.keepDrawing(x, y, g2D);
	}

	private void continueDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.continueDrawing(x, y, g2D);
	}

	private void finishDrawing(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.currentShape.setSelected(true, g2D);
		this.currentShape.finishDrawing(x, y, g2D);
		this.shapeList.add(this.currentShape);
	}
	
	public GShape includes(int x, int y) {
		for(GShape shape: this.shapeList) {
			if(shape.contains(x, y) != null) {
				return shape;
			}
		}
		return null;
	}
	
	private void changePointShape(int x, int y) {
		for (GShape shape: this.shapeList) {
			if (shape.contains(x, y) != null) {		// 마우스 객체에 올리면 손가락 모양으로 바뀜
				Cursor cursor = Cursor.getDefaultCursor();
			    //change cursor appearance to HAND_CURSOR when the mouse pointed on images
			    cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR); 
			    setCursor(cursor);
			} else {		// 마우스 떼면 다시 원래 화살표 모양으로 돌아감
				Cursor cursor = Cursor.getDefaultCursor();
			    //change cursor appearance to HAND_CURSOR when the mouse pointed on images
			    cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR); 
			    setCursor(cursor);
			}
		}
}
	
	/*private void changePointShape(GShape shape) {
		if(shape != null){
			setCursor(currentShape.getCursor(shape));
			return;
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}*/

	class MouseEventHandler implements MouseInputListener, MouseMotionListener {

		private Graphics2D g2D;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		private void mouse1Clicked(MouseEvent e) {
			if (eState == EState.idleNP) {
					initDrawing(e.getX(), e.getY());
					eState = EState.drawingNP;
			} else if (eState == EState.drawingNP) {
				continueDrawing(e.getX(), e.getY());
			}
		}

		private void mouse2Clicked(MouseEvent e) {
			if (eState == EState.drawingNP) {
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleNP;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eState == EState.idleTP) {
				currentShape = includes(e.getX(), e.getY());
				if(currentShape != null){
					currentShape.setSelected(true, g2D);
				}else{	
					eState = EState.drawingTP;
					initDrawing(e.getX(), e.getY());
				}
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP && selectedShape.geteDrawingType() == EDrawingType.TP) {
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleTP;
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eState == EState.drawingNP) {
				keepDrawing(e.getX(), e.getY());
			} else if (eState == EState.idleTP || eState == EState.idleNP) {
				currentShape = includes(e.getX(), e.getY());
				changePointShape(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eState == EState.drawingTP && selectedShape.geteDrawingType() == EDrawingType.TP) {
				keepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}

}

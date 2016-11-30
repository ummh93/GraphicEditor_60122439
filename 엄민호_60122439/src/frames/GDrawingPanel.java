package frames;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import constants.GConstants;
import constants.GConstants.EAnchors;
import constants.GConstants.EDrawingType;
import shapes.GShape;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;

	// object states
	private enum EState {
		idleTP, idleNP,  drawingTP, drawingNP, TPtransforming
	};
	private ENPtrans eNPtrans = ENPtrans.not;
	private EState eState = EState.idleTP;
	private enum ENPtrans {NPtransforming, not};
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
		super.paint(g);
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
	
	private void initTransforming(int x, int y) {
		this.resetSelected();
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		switch (this.currentShape.getCurrentEAnchor()) {
		case NN:
			break;
		case NE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case NW:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SS:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case SW:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case EE:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case WW:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case RR:
			this.currentShape.initResizing(x, y, g2D);
			break;
		case MM:
			this.currentShape.initTransforming(x, y, g2D);
			break;
		}
	}
	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		
		switch (this.currentShape.getCurrentEAnchor()) {
		case NN:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case NE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case NW:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SS:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case SW:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case EE:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case WW:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case RR:
			this.currentShape.keepResizing(x, y, g2D);
			break;
		case MM:
			this.currentShape.keepTransforming(x, y, g2D);
			break;
		}
	}
	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D)this.getGraphics();
		g2D.setXORMode(this.getBackground());
		switch (this.currentShape.getCurrentEAnchor()) {
		case NN:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case NE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case NW:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SS:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case SW:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case EE:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case WW:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case RR:
			this.currentShape.finishResizing(x, y, g2D);
			break;
		case MM:
			this.currentShape.finishTransforming(x, y, g2D);
			break;
		}
		this.currentShape.setSelected(true, g2D);
		this.repaint();
	}
	
	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapeList) {
			GConstants.EAnchors eAnchor = shape.contains(x, y);
			if (eAnchor != null)
				return shape;
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
	private void changeCursor(int x, int y) {
		for (GShape shape: this.shapeList) {
			EAnchors eAnchor = shape.contains(x, y);
			if (eAnchor != null) {
				switch (eAnchor) {
				case NN:
					this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
					return;
				case NE:
					this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
					return;
				case NW:
					this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
					return;
				case SS:
					this.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
					return;
				case SE:
					this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
					return;
				case SW:
					this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
					return;
				case EE:
					this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
					return;
				case WW:
					this.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
					return;
				case RR:
					this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					return;
				case MM:
					this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
					return;
				}
			}
		}
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	class MouseEventHandler implements MouseInputListener, MouseMotionListener {
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
				
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					initDrawing(e.getX(), e.getY());
					eState = EState.drawingTP;
				} else {
					initTransforming(e.getX(), e.getY());
					eState = EState.TPtransforming;
				}
			} else if(eState == EState.idleNP && eNPtrans == ENPtrans.NPtransforming){
				initTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eState == EState.drawingTP && selectedShape.geteDrawingType() == EDrawingType.TP) {
				finishDrawing(e.getX(), e.getY());
				eState = EState.idleTP;
			} else if (eState == EState.TPtransforming || eNPtrans == ENPtrans.NPtransforming) {
				// 이상하대 나중에 고친대 idle은 원래 하나여야 하는데 두개로 나뉘어져 있어서 그렇대
				eState = EState.idleTP;
				eNPtrans = ENPtrans.not;
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
			} else if (eState == EState.TPtransforming || eNPtrans == ENPtrans.NPtransforming){
				keepTransforming(e.getX(), e.getY());
			}
		}

		private void keepTransforming(int x, int y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}
	}

}

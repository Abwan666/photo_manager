package cn.edu.scau.sec.se.backstage;

import cn.edu.scau.sec.se.controllers.PbUIcontroller;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import cn.edu.scau.sec.se.models.ImageND;

public class DragMT {
	Node node;
	PbUIcontroller pbUIcontroller;
	private Rectangle rectangle;
	private boolean isDragged;
	
	public DragMT(Node node,PbUIcontroller pbUIcontroller) {
		rectangle = new Rectangle();
		this.node = node;
		this.pbUIcontroller = pbUIcontroller;
		addListener();
	}
	private void addListener() {
		node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			isDragged = false;
			double nowX = e.getX();
			double nowY = e.getY();
			rectangle.setX(nowX);
			rectangle.setY(nowY);
			rectangle.setHeight(0);
			rectangle.setWidth(0);
		});
		
		node.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
			this.isDragged = true;
		});
		
		node.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
			double nowX = e.getX();
			double nowY = e.getY();
			double baseX = rectangle.getX();
			double baseY = rectangle.getY();
			
			rectangle.setX(Math.min(baseX, nowX));
			rectangle.setY(Math.min(baseY, nowY));
			
			rectangle.setWidth(Math.abs(baseX - nowX));
			rectangle.setHeight(Math.abs(baseY - nowY));
			
//			System.out.println(selectRectangle);
			
			if(this.isDragged) {				
				ImageND.clSelected();
				for(Node cImageND:  pbUIcontroller.getFlowPaneChildren()) {
					if(cImageND instanceof ImageND) {
						if(isRectOverlap((ImageND)cImageND))
							((ImageND)cImageND).setSelected(true);
					}
					else	((ImageND)cImageND).setSelected(false);
				}
			}
			
		});
	}
	private boolean isRectOverlap(ImageND  imageND) {
		double rCenterX = rectangle.getX() + rectangle.getWidth()/2.0;
		double rCenterY = rectangle.getY() + rectangle.getHeight()/2.0;
		double centerX = imageND.getLayoutX() + imageND.getWidth()/2.0;
		double centerY = imageND.getLayoutY() + imageND.getHeight()/2.0;
		return Math.abs(centerX - rCenterX) <= (imageND.getWidth()/2.0 + rectangle.getWidth()/2.0) &&
				Math.abs(centerY - rCenterY) <= (imageND.getHeight()/2.0 + rectangle.getHeight()/2.0);
	}

}

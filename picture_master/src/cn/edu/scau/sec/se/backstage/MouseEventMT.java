package cn.edu.scau.sec.se.backstage;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import cn.edu.scau.sec.se.models.ImageFL;
import cn.edu.scau.sec.se.models.ImageND;

public class MouseEventMT implements EventHandler<MouseEvent>{
	ImageFL imageFL;
	private Node node;
	public MouseEventMT(Node node,ImageFL imageFL) {
		this.node = node;
		this.imageFL = imageFL;
	}
	@Override
	public void handle(MouseEvent event) {
		if(node instanceof ImageND) {
			if(event.isControlDown() == false) {
				if(event.getButton()!=MouseButton.SECONDARY || !((ImageND)node).selected.getValue()){
				ImageND.clSelected();
				((ImageND)node).setSelected(true);
				}
				if(event.getClickCount()>=2 && event.getButton() == MouseButton.PRIMARY){
					ImageND.clSelected();
					((ImageND)node).setSelected(true);
					((ImageND) node).openAction();
				}				
			}
			if(event.isControlDown() && event.getButton() == MouseButton.PRIMARY) {
				((ImageND) node).setSelected(!((ImageND)node).selected.get());
			}
		}
	}
}

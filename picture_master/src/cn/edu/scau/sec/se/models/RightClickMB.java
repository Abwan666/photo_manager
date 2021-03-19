package cn.edu.scau.sec.se.models;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import cn.edu.scau.sec.se.controllers.DdUIcontroller;
import cn.edu.scau.sec.se.controllers.PbUIcontroller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cn.edu.scau.sec.se.operations.CopyOP;
import cn.edu.scau.sec.se.operations.CutOP;
import cn.edu.scau.sec.se.operations.PdUIOP;
import cn.edu.scau.sec.se.operations.ReNameOP;
import cn.edu.scau.sec.se.operations.StickOP;

public class RightClickMB {
	PbUIcontroller mainUI;
	 ContextMenu contextMenu;
	 private double x=0;
	 private double y=0;
	 public RightClickMB(Node node,PbUIcontroller mainUI,boolean choice) {
		 this.mainUI = mainUI;
		 if(choice) {
			 PictureMenu(node);
		 }
		 nullMenu(node);
	}
	 
	public void PictureMenu(Node node) {
		contextMenu = new ContextMenu();
		MenuItem open = new MenuItem("Open");
		MenuItem copy = new MenuItem("Copy");
		MenuItem cut = new MenuItem("Cut");
		MenuItem rename = new MenuItem("Rename");
		MenuItem delete = new MenuItem("Delete");
		
		contextMenu.getItems().addAll(open,cut,copy,rename,delete);
		
		open.setOnAction(e->{
			new PdUIOP();
		});
		copy.setOnAction(e->{
			new CopyOP();
		});
		cut.setOnAction(e->{
			new CutOP();
		});
		rename.setOnAction(e->{
			new ReNameOP(mainUI);
		});
		delete.setOnAction(e->{
			try {
			DdUIcontroller.setMainUI(mainUI);
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/cn/edu/scau/sec/se/fxml/DdUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
			scene.setFill(Paint.valueOf("#00000000"));
			URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	    	String path = url.toExternalForm();
	        scene.setCursor(Cursor.cursor(path));
		    Stage stage =new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setX(UIandImageDT.stage.getX()+400);
			stage.setY(UIandImageDT.stage.getY()+200);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Warning!!!");
			stage.getIcons().add(new Image("cn/edu/scau/sec/se/icon/注意事项图标.png"));
			stage.show();
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
					x = event.getScreenX()-stage.getX();
					y = event.getScreenY()-stage.getY();
					}
				}
			});
			
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
						if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())stage.setX(event.getScreenX()-x);
						if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())stage.setY(event.getScreenY()-y);
					}
				}
			});
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}
		});
		
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY){
				contextMenu.hide();
				contextMenu.show(node, e.getScreenX(), e.getScreenY());
				URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
		    	String path = url.toExternalForm();
		    	contextMenu.getScene().setCursor(Cursor.cursor(path));
			}
			else {
				if (contextMenu.isShowing())
					contextMenu.hide();
			}
		});
	}
	
	 public void nullMenu(Node node) {
		ContextMenu mouseRightClickMenu = new ContextMenu();
		MenuItem paste = new MenuItem("Paste");
		MenuItem all = new MenuItem("Select all");
		mouseRightClickMenu.getItems().add(paste);
		mouseRightClickMenu.getItems().add(all);
		
		paste.setOnAction(e ->{
			new StickOP(mainUI);
		});
		all.setOnAction(e->{
			for (Node childrenNode :  mainUI.getFlowPane().getChildren()) {
				if (childrenNode instanceof ImageND) {
					((ImageND) childrenNode).setSelected(true);
				}
			}
		});
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			Node clickNode = e.getPickResult().getIntersectedNode();
			// System.out.println(clickNode.toString());
			if (clickNode instanceof FlowPane && !(clickNode instanceof ImageND) && !(clickNode instanceof Text)) {// 鼠标点击非图片节点
				ImageND.clSelected();// 清空已选

				if (e.getButton() == MouseButton.SECONDARY) {// 鼠标右键
					Clipboard clipboard = Clipboard.getSystemClipboard();
					List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
					if (files.size() <= 0) {
						paste.setDisable(true);
					} 
					else {
						paste.setDisable(false);
					}
					mouseRightClickMenu.hide();
					mouseRightClickMenu.show(node, e.getScreenX(), e.getScreenY());
					URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
			    	String path = url.toExternalForm();
					mouseRightClickMenu.getScene().setCursor(Cursor.cursor(path));
				} 
				else {
					if (mouseRightClickMenu.isShowing()) {
						mouseRightClickMenu.hide();
					}
				}
			} else {
				if (mouseRightClickMenu.isShowing()) {
					mouseRightClickMenu.hide();
				}
			}
		});
	}

}

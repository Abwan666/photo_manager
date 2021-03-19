package cn.edu.scau.sec.se.operations;

import java.net.URL;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;

public class PfUIOP {
	double x=0;
	double y=0;
	public PfUIOP() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/cn/edu/scau/sec/se/fxml/PfUI.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
			scene.setFill(Paint.valueOf("#00000000"));
			URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	    	String path = url.toExternalForm();
	        scene.setCursor(Cursor.cursor(path));
			UIandImageDT.stage.setScene(scene);
			UIandImageDT.stage.show();
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
					x = event.getScreenX()-UIandImageDT.stage.getX();
					y = event.getScreenY()-UIandImageDT.stage.getY();
					}
				}
			});
			
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
						if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())UIandImageDT.stage.setX(event.getScreenX()-x);
						if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())UIandImageDT.stage.setY(event.getScreenY()-y);
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package cn.edu.scau.sec.se.operations;

import java.net.URL;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import cn.edu.scau.sec.se.models.ImageND;

public class PbUIOP {
	double x=0;
	double y=0;
	public PbUIOP() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/cn/edu/scau/sec/se/fxml/PbUI.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
			URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	    	String path = url.toExternalForm();
	        scene.setCursor(Cursor.cursor(path));
			UIandImageDT.stage.setScene(scene);
			UIandImageDT.stage.setResizable(false);
			scene.setFill(Paint.valueOf("#00000000"));
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					x = event.getScreenX()-UIandImageDT.stage.getX();
					y = event.getScreenY()-UIandImageDT.stage.getY();
				}
			});
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())UIandImageDT.stage.setX(event.getScreenX()-x);
					if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())UIandImageDT.stage.setY(event.getScreenY()-y);
				}
			});
			UIandImageDT.stage.show();
			ImageND.clSelected();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

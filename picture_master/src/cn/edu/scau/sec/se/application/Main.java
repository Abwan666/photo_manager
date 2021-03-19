package cn.edu.scau.sec.se.application;

import java.net.URL;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


public class Main extends Application {
	double x=0;
	double y=0;
	public static Window mainStage;
	//public  static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		UIandImageDT.stage = primaryStage;
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/cn/edu/scau/sec/se/fxml/PbUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
			scene.setFill(Paint.valueOf("#00000000"));
			URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	    	String path = url.toExternalForm();
	        scene.setCursor(Cursor.cursor(path));
			UIandImageDT.stage.setScene(scene);
			UIandImageDT.stage.setTitle("PhotoManager");
			UIandImageDT.stage.initStyle(StageStyle.TRANSPARENT);
			UIandImageDT.stage.show();
			UIandImageDT.stage.getIcons().add(new Image("cn/edu/scau/sec/se/icon/主界面图标.png"));
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
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())&&(event.getScreenX()<=
							Screen.getPrimary().getVisualBounds().getMaxX()||event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())){
						if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())UIandImageDT.stage.setX(event.getScreenX()-x);
						if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())UIandImageDT.stage.setY(event.getScreenY()-y);
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
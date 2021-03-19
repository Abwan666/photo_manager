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
import cn.edu.scau.sec.se.models.ImageND;

public class PdUIOP {
	double x=0;
	double y=0;
	public PdUIOP() {
			int i;
			for(i=0;i<UIandImageDT.files.size();i++){
				if(ImageND.getSlImageNDs().get(0).getImageFile()==UIandImageDT.files.get(i)) break;
//				System.out.println(PictureNode.getSelectedPictures().get(0).getImageFile()+"  "+ChangeService.files.get(i));
//				System.out.println("位置"+i);
			}
			new PageTurningOP(i);
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/cn/edu/scau/sec/se/fxml/PdUI.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
				UIandImageDT.stage.setScene(scene);
				scene.setFill(Paint.valueOf("#00000000"));
				URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
		    	String path = url.toExternalForm();
		        scene.setCursor(Cursor.cursor(path));
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
				UIandImageDT.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}

package cn.edu.scau.sec.se.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.animation.FadeTransition;


public class PcUIcontroller implements Initializable{

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane pcpane;
    
    @FXML
    private Button close;

    @FXML
    void close(MouseEvent event) {
    	if(event.getButton().name().equals(MouseButton.PRIMARY.name()))
    	close.getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
		imageView.setImage(UIandImageDT.change.getImage());
		URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
    	String path = url.toExternalForm();
        imageView.setCursor(Cursor.cursor(path));
		FadeTransition fade = new FadeTransition(); 
        fade.setDuration(Duration.seconds(0.5));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(pcpane);
        fade.play();
	}

}

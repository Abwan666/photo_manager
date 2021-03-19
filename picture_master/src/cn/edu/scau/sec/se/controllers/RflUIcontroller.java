package cn.edu.scau.sec.se.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;


public class RflUIcontroller implements Initializable{

    @FXML
    private Button close;
    
    @FXML
    private static Text text;

	public static Text getText() {
		return text;
	}

	@FXML
    private Pane Pane;


	@FXML
    void close(MouseEvent event) {    	
    	close.getScene().getWindow().hide();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
//		System.out.println("上界面动画");
		FadeTransition fade = new FadeTransition(); 
		fade.setDuration(Duration.seconds(0.1));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(5);
        fade.setAutoReverse(true);
        fade.setNode(Pane);
        fade.play();
	}

}

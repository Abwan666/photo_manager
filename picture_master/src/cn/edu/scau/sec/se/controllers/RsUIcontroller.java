package cn.edu.scau.sec.se.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class RsUIcontroller implements Initializable{

	@FXML
	private Button close;
	@FXML
	private Pane Apane;
	
	@FXML
    void close(MouseEvent event) {
    	close.getScene().getWindow().hide();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
//				System.out.println("空界面动画");
				FadeTransition fade = new FadeTransition(); 
				fade.setDuration(Duration.seconds(0.1));
		        fade.setFromValue(0);
		        fade.setToValue(1);
		        fade.setCycleCount(5);
		        fade.setAutoReverse(true);
		        fade.setNode(Apane);
		        fade.play();
	}

}

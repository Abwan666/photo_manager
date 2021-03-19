package cn.edu.scau.sec.se.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import cn.edu.scau.sec.se.operations.DeleteOP;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

public class DdUIcontroller implements Initializable{
	private static boolean isdelete=false;
	

	public static boolean isIsdelete() {
		return isdelete;
	}

	public static void setIsdelete(boolean isdelete) {
		DdUIcontroller.isdelete = isdelete;
	}

	private static PbUIcontroller mainUI;
    public static PbUIcontroller getMainUI() {
		return mainUI;
	}

	public static void setMainUI(PbUIcontroller mainUI) {
		DdUIcontroller.mainUI = mainUI;
	}

	@FXML
    private Button Setfalse;

    @FXML
    private Button Setture;
    @FXML
    private Pane Pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 自动生成的方法存根
//		System.out.println("空界面动画");
		FadeTransition fade = new FadeTransition(); 
		fade.setDuration(Duration.seconds(0.1));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(5);
        fade.setAutoReverse(true);
        fade.setNode(Pane);
        fade.play();
	}
	
	@FXML
    void setfalse(ActionEvent event) {
		Setfalse.getScene().getWindow().hide();
		}

    @FXML
    void settrue(ActionEvent event) {
    	isdelete=true;
		new DeleteOP(mainUI);
		Setfalse.getScene().getWindow().hide();
    }
}

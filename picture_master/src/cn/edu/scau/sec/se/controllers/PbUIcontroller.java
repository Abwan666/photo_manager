package cn.edu.scau.sec.se.controllers;

import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import cn.edu.scau.sec.se.models.Catalog;
import cn.edu.scau.sec.se.models.ImageFL;
import cn.edu.scau.sec.se.models.ImageND;
import cn.edu.scau.sec.se.models.RightClickMB;
import cn.edu.scau.sec.se.operations.PdUIOP;
import cn.edu.scau.sec.se.operations.SsUIOP;
import cn.edu.scau.sec.se.backstage.DragMT;
import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;

public class PbUIcontroller implements Initializable {

	private ArrayList<File> files;
	public static String flpath;
	private PbUIcontroller pbUIcontroller;
	private ArrayList<ImageND> images;
	private Catalog catalog;
	private Stage Stage;
	private Scene scene;
	private double x=0;
	private double y=0;

	public Catalog getFileTree() {
		return catalog;
	}
	@FXML
	private AnchorPane Apane;
	@FXML
	private TreeView<ImageFL> treeview;
	@FXML
	private FlowPane flowPane;
	@FXML
	private Text text;
	@FXML
	private Text textTwo;
	@FXML
	private ToolBar toolBar;
	@FXML
	private Button openBtn;
	@FXML
	private Button PPT;
	@FXML
	private Button close;
	
	public PbUIcontroller() {	
		pbUIcontroller = this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		images = new ArrayList<>();
		this.catalog=new Catalog(pbUIcontroller, treeview);
		treeview =catalog.gettreeView();
		new DragMT(flowPane,pbUIcontroller);
		new RightClickMB(flowPane, pbUIcontroller,false);
        FadeTransition fade = new FadeTransition(); 
        fade.setDuration(Duration.seconds(0.5));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(Apane);
        fade.play();
	}

		

	public FlowPane getFlowPane() {
		return flowPane;
	}

	public  ObservableList<Node> getFlowPaneChildren() {
		return flowPane.getChildren();
	}
	public Text getText() {
		return text;
	}
	public Text getTextTwo() {
		return textTwo;
	}

	public ArrayList<ImageND> getPictures() {
		return images;
	}

	public void addPictures(ImageND pNode) {
		images.add(pNode);
	}

	public void showPicture() {
//		System.out.println("show");
		flowPane.getChildren().remove(0, flowPane.getChildren().size());
		for (ImageND pNode : images) {
			pNode.setTooltip(new Tooltip(pNode.getImageFile().getName()));
			flowPane.getChildren().add(pNode);
		}
		files=new ArrayList<File>();
    	for(int i=0;i<images.size();i++) {
    		files.add(images.get(i).getImageFile());
    	}
    	UIandImageDT.files=files;
	}

	public void clearPictures() {
		images.clear();
	}

	public void removePictures(ImageND pNode) {
		for (ImageND pictureNode : images) {
			if (pictureNode.equals(pNode)) {
				images.remove(pNode);
				break;
			}
		}
	}

	@FXML
	public void openBtnAction(ActionEvent event) throws IOException {
		if (ImageND.getSlImageNDs().size()==0) {
			initRsUI();
		}
		else new PdUIOP();
	}
	private void initRsUI() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/cn/edu/scau/sec/se/fxml/RsUI.fxml"));
		Parent root = (Parent) loader.load();
		scene = new Scene(root);
		scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
		scene.setFill(Paint.valueOf("#00000000"));
		URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
    	String path = url.toExternalForm();
        scene.setCursor(Cursor.cursor(path));
		Stage = new Stage();
		Stage.setScene(scene);
		Stage.initStyle(StageStyle.TRANSPARENT);
		Stage.setTitle("Warning!!!");
		Stage.getIcons().add(new Image("cn/edu/scau/sec/se/icon/注意事项图标.png"));
		Stage.setAlwaysOnTop(true);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO 自动生成的方法存根
				if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
				x = event.getScreenX()-Stage.getX();
				y = event.getScreenY()-Stage.getY();
				}
			}
		});
		
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO 自动生成的方法存根
				if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
					if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())Stage.setX(event.getScreenX()-x);
					if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())Stage.setY(event.getScreenY()-y);
			    }
			}
		});
		Stage.setX(UIandImageDT.stage.getX()+400);
		Stage.setY(UIandImageDT.stage.getY()+200);
		Stage.show();
		UIandImageDT.stage.xProperty().addListener(new ChangeListener<Number>() {
			
						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
							// TODO 自动生成的方法存根
							Stage.setX((double)newValue+400);
						}
					});
					UIandImageDT.stage.yProperty().addListener(new ChangeListener<Number>() {

						@Override
						public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
							// TODO 自动生成的方法存根
							Stage.setY((double)newValue+200);
						}
					});
					flowPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							// TODO 自动生成的方法存根
							Stage.close();
						}
					});
	}
	@FXML
	public void PPTAction(ActionEvent event) throws IOException {
		if (ImageND.getSlImageNDs().size()==0) {
			initRsUI();
		}
		else new SsUIOP();
	}
	
	 @FXML
	    void close(MouseEvent event) {
		 if(event.getButton().name().equals(MouseButton.PRIMARY.name()))
		  Platform.exit();//关闭窗口
	    }
	 
	 @FXML
	    void minimize(MouseEvent event) {
		 if(event.getButton().name().equals(MouseButton.PRIMARY.name()))
		 UIandImageDT.stage.setIconified(true);
	    }
}

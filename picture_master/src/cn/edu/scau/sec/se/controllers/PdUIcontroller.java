package cn.edu.scau.sec.se.controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import cn.edu.scau.sec.se.models.ImageND;
import cn.edu.scau.sec.se.operations.PageTurningOP;
import cn.edu.scau.sec.se.operations.PbUIOP;
import cn.edu.scau.sec.se.operations.PfUIOP;
import cn.edu.scau.sec.se.operations.ReSetOP;
import cn.edu.scau.sec.se.operations.SizeOP;
import cn.edu.scau.sec.se.operations.SsUIOP;
import cn.edu.scau.sec.se.operations.TurnRightOP;
import cn.edu.scau.sec.se.operations.TurnleftOP;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import cn.edu.scau.sec.se.backstage.UIandImageDT;

public class PdUIcontroller implements Initializable {
	private double x=0;
	private double y=0;
	private Stage Stage=new Stage();
	private Stage Stage2=new Stage();
	@FXML
	private HBox hbox;
	@FXML
	private ImageView imageView;
	@FXML
	private Button enlargeBtn;
	@FXML
	private Button smallBtn;
	@FXML
	private Button resetBtn;
	@FXML
	private Button rotateBtn;
	@FXML
	private Button beautyBtn;
	@FXML
	private Button Turnleft;
	@FXML
	private Button pptBtn;
	@FXML
	private Menu MenuMore;
	@FXML
	private Menu MenuHelp;
	@FXML
	private AnchorPane Apane;
	@FXML
	private Button previousImageBtn;
	@FXML
	private Button nextImageBtn;
	@FXML
	private ToolBar toolbar;
	@FXML
	private Button backBtn;


	private Image image;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		image = new Image(ImageND.getSlImageNDs().get(0).getURL());
		UIandImageDT.file = ImageND.getSlImageNDs().get(0).getImageFile();

		UIandImageDT.origin = new ImageView(image);
		UIandImageDT.change = new ImageView(image);
		
		UIandImageDT.preHight = imageView.getFitHeight();
		UIandImageDT.preWidth = imageView.getFitWidth();

		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setImage(image);
//		System.out.println(UIandImageDT.file.getName());
		toolbar.setVisible(true);
		FadeTransition fade = new FadeTransition(); 
        fade.setDuration(Duration.seconds(0.5));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(Apane);
        fade.play();
        URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/拖动.png");
    	String path = url.toExternalForm();
        toolbar.setCursor(Cursor.cursor(path));
        URL url2 =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
    	String path2 = url2.toExternalForm();
    	enlargeBtn.setCursor(Cursor.cursor(path2));
    	smallBtn.setCursor(Cursor.cursor(path2));
    	resetBtn.setCursor(Cursor.cursor(path2));
    	rotateBtn.setCursor(Cursor.cursor(path2));
    	beautyBtn.setCursor(Cursor.cursor(path2));
    	Turnleft.setCursor(Cursor.cursor(path2));
    	pptBtn.setCursor(Cursor.cursor(path2));
	}
	
		

	public ImageView getImageView() {
		return imageView;
	}

	@FXML
	private void Back(ActionEvent e) {
		if(Stage.isShowing()){
			Stage.close();
			}
		if(Stage2.isShowing()){
			Stage2.close();
			}
		new PbUIOP();
		SizeOP.setChangeNum(0);
	}

	@FXML
	private void Press() {
		if (toolbar.isVisible()) {
			toolbar.setVisible(false);
			backBtn.setVisible(false);
		} else {
			toolbar.setVisible(true);
			backBtn.setVisible(true);
		}
	}

	@FXML
	private void PPTAction(ActionEvent e) {
		if(Stage.isShowing()){
			Stage.close();
			}
		if(Stage2.isShowing()){
			Stage2.close();
			}
//		SsUIcontroller.setSubscene(getSnowFlake(75,1038,636,2500));
		new SsUIOP();
	}

	@FXML
	private void turnleft(ActionEvent e) {
		new TurnleftOP(imageView);
	}

	@FXML
	public void enlargeAction(ActionEvent event) {
		SizeOP.enlarge(imageView);
	}

	@FXML
	public void smallAction(ActionEvent event) {
		SizeOP.small(imageView);
	}

	@FXML
	public void resetAction(ActionEvent event) {
 		SizeOP.setChangeNum(0);
		new ReSetOP(imageView);
	}

	@FXML
	public void rotateAction(ActionEvent event) {
		new TurnRightOP(imageView);
	}

	@FXML
	public void beautyAction(ActionEvent event) {
		if(Stage.isShowing()){
			Stage.close();
			}
		if(Stage2.isShowing()){
			Stage2.close();
			}
		UIandImageDT.change = this.imageView;
		new PfUIOP();

	}

	@FXML
	public void previousAction(ActionEvent event) throws IOException {
		if(Stage.isShowing()){
			Stage.close();
			Stage=null;
			}
		if(Stage2.isShowing()){
			Stage2.close();
			Stage2=null;
			}
		PageTurningOP.setPage(PageTurningOP.getPage()-1); 
		if (PageTurningOP.getPage()< 0) {
			PageTurningOP.setPage(PageTurningOP.getPage()+1); 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/cn/edu/scau/sec/se/fxml/RflUI.fxml"));
			Parent root;
				root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
			scene.setFill(Paint.valueOf("#00000000"));
			URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	    	String path = url.toExternalForm();
	        scene.setCursor(Cursor.cursor(path));
			Stage stage = new Stage();
			stage.setScene(scene);
			Stage = stage;
			Stage.setAlwaysOnTop(true);
			Stage.initStyle(StageStyle.TRANSPARENT);
			Stage.setTitle("Warning!!!");
			Stage.getIcons().add(new Image("cn/edu/scau/sec/se/icon/注意事项图标.png"));
			Apane.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					Stage.close();
				}
			});
			Stage.setX(UIandImageDT.stage.getX()+400);
			Stage.setY(UIandImageDT.stage.getY()+200);
			Stage.show();
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
			Apane.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					Stage.close();
				}
			});
			toolbar.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					Stage.close();
				}
			});
//			System.out.println("当前页面"+PageTurningOP.getPage());
		}
		else {PageTurningOP.changePicture(imageView, false);
		SizeOP.setChangeNum(0);
		new ReSetOP(imageView);
//		System.out.println("当前页面"+PageTurningOP.getPage());
		}
	}

	@FXML
	public void nextAction(ActionEvent event) throws IOException {
		if(Stage2.isShowing()){
			Stage2.close();
		}
		if(Stage.isShowing()){
			Stage.close();
			}
		PageTurningOP.setPage(PageTurningOP.getPage()+1); 
		if (PageTurningOP.getPage() > UIandImageDT.files.size()-1) {
			PageTurningOP.setPage(PageTurningOP.getPage()-1);
			FXMLLoader loader2 = new FXMLLoader();
			loader2.setLocation(getClass().getResource("/cn/edu/scau/sec/se/fxml/RlUI.fxml"));
			Parent root2;
			root2 = (Parent) loader2.load();
			Scene scene2 = new Scene(root2);
			scene2.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
			scene2.setFill(Paint.valueOf("#00000000"));
			URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	    	String path = url.toExternalForm();
	        scene2.setCursor(Cursor.cursor(path));
			Stage stage2 = new Stage(); 
			stage2.setScene(scene2);
			Stage2=stage2;
			Stage2.setAlwaysOnTop(true);
			Stage2.initStyle(StageStyle.TRANSPARENT);
			Stage2.setTitle("Warning!!!");
			Stage2.getIcons().add(new Image("cn/edu/scau/sec/se/icon/注意事项图标.png"));
			Apane.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					Stage2.close();
				}
			});
			Stage2.setX(UIandImageDT.stage.getX()+400);
			Stage2.setY(UIandImageDT.stage.getY()+200);
			Stage2.show();
			scene2.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
					x = event.getScreenX()-Stage2.getX();
					y = event.getScreenY()-Stage2.getY();
					}
				}
			});
			
			scene2.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
						if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())Stage.setX(event.getScreenX()-x);
						if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())Stage.setY(event.getScreenY()-y);
					}
				}
			});
			Apane.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					Stage2.close();
				}
			});
			toolbar.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					Stage2.close();
				}
			});
//			System.out.println("当前页面"+PageTurningOP.getPage());
		}
		else {PageTurningOP.changePicture(imageView, true);
		SizeOP.setChangeNum(0);
		new ReSetOP(imageView);
//		System.out.println("当前页面"+PageTurningOP.getPage());
		}
	}
	
	@FXML
    void tb_mousepressed(MouseEvent event) {
		if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
				x=event.getX();
				y=event.getY();
		}
    }

    @FXML
    void tb_mousedragged(MouseEvent event) {
				// TODO 自动生成的方法存根
    	if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
    	 if(event.getSceneX()-x>=-2&&event.getSceneX()-x+249<=1040){
    		toolbar.setLayoutX(event.getSceneX()-x);
    	 }
    	 if(event.getSceneY()-y>=-1&&event.getSceneY()-y+40<=628){
			toolbar.setLayoutY(event.getSceneY()-y);
    	 }
    	}
    }
}
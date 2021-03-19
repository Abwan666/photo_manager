package cn.edu.scau.sec.se.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import cn.edu.scau.sec.se.operations.PdUIOP;
import cn.edu.scau.sec.se.operations.ScreenShotOP;
import cn.edu.scau.sec.se.operations.TurnRightOP;
import cn.edu.scau.sec.se.operations.TurnleftOP;

public class PfUIcontroller implements Initializable {
	private double x=0;
	private double y=0;
	private static Stage stage2;
	public static Stage getStage2() {
		return stage2;
	}

	@FXML
	private ImageView imageview;
	@FXML
	private ImageView image1, image2, image3, image4, image5, image6, image7, image8;
	@FXML
	private Slider slider;
	@FXML
	private Button Submit;
	@FXML
	private Button Cancel;
	@FXML
	private AnchorPane Apane;

	@FXML
	private AnchorPane backpane;

	@FXML
	private void Back(ActionEvent event) {
		backpane.setVisible(true);
		Submit.setDisable(false);
		Cancel.setDisable(false);
	}

	@FXML
	private void Sure(ActionEvent event) {
		UIandImageDT.change=UIandImageDT.origin;
		new PdUIOP();
	}

	@FXML
	private void Cancel(ActionEvent event) {

		backpane.setVisible(false);
		Submit.setDisable(true);
		Cancel.setDisable(true);
	}

	@FXML
	Button ChangeButton;
	@FXML
	Button SaveButton;
	@FXML
	private Button test;

	@FXML
	private void Sepiatone(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(0.5);
		SepiaTone effect = new SepiaTone(0.5);
		imageview.setEffect(effect);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			effect.setLevel(new_val.doubleValue());
		});
	}

	@FXML
	private void Origin(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(false);
		imageview.setEffect(null);

	}

	@FXML
	private void Overlay(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(0.5);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		imageview.setEffect(blend);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Bloom(ActionEvent e) {

		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(0.9);
		Bloom bloom = new Bloom(0.9);
		imageview.setEffect(bloom);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			bloom.setThreshold(new_val.doubleValue());

		});
	}

	@FXML
	private void Exclusion(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(1);
		Blend blend = new Blend();
		blend.setMode(BlendMode.EXCLUSION);
		blend.setOpacity(1);
		imageview.setEffect(blend);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());

		});
	}

	@FXML
	private void Arctic(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(0.5);
		ColorAdjust color = new ColorAdjust();
		color.setHue(-0.6);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		color.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Denim(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(0);
		ColorAdjust color = new ColorAdjust();
		color.setSaturation(-1.0);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		blend.setOpacity(0);
		color.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Neo(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-background-color: #F4F4F4;-fx-border-style:  dotted;-fx-border-radius:  10px;-fx-border-color:  #FFBBFF;-fx-border-width:  3;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-background-color:  #ffbbff");
		slider.setVisible(true);
		slider.setValue(0.5);
		ColorAdjust color = new ColorAdjust();
		color.setHue(0.6);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		color.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	public void setImageViewImage(ImageView image) {
		image.setImage(UIandImageDT.change.getImage());
		image.setEffect(UIandImageDT.change.getEffect());
		image.setViewport(UIandImageDT.change.getViewport());
		image.setNodeOrientation(UIandImageDT.change.getNodeOrientation());
		image.setRotate(UIandImageDT.change.getRotate());
	}

	private void setImageViewEffect() {
		this.setImageViewImage(image1);
		this.setImageViewImage(image2);
		this.setImageViewImage(image3);
		this.setImageViewImage(image4);
		this.setImageViewImage(image5);
		this.setImageViewImage(image6);
		this.setImageViewImage(image7);
		this.setImageViewImage(image8);
		image1.setEffect(null);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		blend.setOpacity(0.5);
		image2.setEffect(blend);
		SepiaTone sep = new SepiaTone(0.5);
		image3.setEffect(sep);
		Bloom bloom = new Bloom(0.9);
		image4.setEffect(bloom);
		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.EXCLUSION);
		blend2.setOpacity(1);
		image5.setEffect(blend2);
		ColorAdjust color2 = new ColorAdjust();
		color2.setHue(-0.6);
		color2.setInput(blend);
		image6.setEffect(color2);
		ColorAdjust color3 = new ColorAdjust();
		color3.setSaturation(-1.0);
		color3.setInput(blend);
		blend.setOpacity(0);
		image7.setEffect(color3);
		ColorAdjust color4 = new ColorAdjust();
		color4.setHue(0.6);
		color4.setInput(blend);
		image8.setEffect(color4);
	}

	public void setImage() {
		imageview.setImage(UIandImageDT.change.getImage());
		imageview.setEffect(UIandImageDT.change.getEffect());
		imageview.setViewport(UIandImageDT.change.getViewport());
		imageview.setNodeOrientation(UIandImageDT.change.getNodeOrientation());
		imageview.setRotate(UIandImageDT.change.getRotate());
		setImageViewEffect(); 
	}

	@FXML
	private BorderPane borderpane;


	public class SaveTask extends Task<Integer> {
		@Override
		protected Integer call() throws Exception {
			for (int i = 0; i < 250; i++) {
				updateProgress(i, 250);
				Thread.sleep(5);
			}
			return 1;
		}
	}

	@FXML
	private void Copy(ActionEvent event) {
		File file = UIandImageDT.file;

		if (file.exists()) {
			Task<Integer> task = new SaveTask();

			new Thread(task).start();
			imageview.setEffect(null);
			imageview.setScaleX(4.0);
			imageview.setScaleY(4.0);
			WritableImage image = imageview.snapshot(new SnapshotParameters(), null);
			imageview.setScaleX(1.0);
			imageview.setScaleY(1.0);
			slider.setVisible(false);
			String copyfilepath = null;
			String filename = file.getName();
			String fileParentPath = file.getParent();
			String name1 = filename.substring(0, filename.lastIndexOf("."));
//			System.out.println(name1);
			int a = name1.lastIndexOf("(");
			int b = name1.lastIndexOf(")");
			if (a != -1 && b != -1) {
				String index = name1.substring(name1.lastIndexOf("(") + 1, name1.lastIndexOf(")"));
				//substring是指string的片段，lastIndexOf是指目标字符串在字符串出现的从后往前数的首位置，第一个位置为0
				if (index != "" && index != null) {
					int i,j,n = Integer.valueOf(index);
//					System.out.println("n的值："+n);
					n++;
					copyfilepath = fileParentPath + "\\" + name1.substring(0,name1.lastIndexOf("(")) + "(" + n + ").jpg";
					for(i=0;i<UIandImageDT.files.size();i++){
						for(j=0;j<UIandImageDT.files.size();j++){
//							System.out.println("文件路径"+copyfilepath.substring(copyfilepath.lastIndexOf("\\")+1)+" "+UIandImageDT.files.get(j).getName());
							if(copyfilepath.substring(copyfilepath.lastIndexOf("\\")+1).equals(UIandImageDT.files.get(j).getName())){
								n++;					
								copyfilepath = fileParentPath + "\\" + name1.substring(0,name1.lastIndexOf("(") - 1) + "(" + n + ").jpg";
//								System.out.println("文件名："+copyfilepath);
								break;
							}
						}
					}
				}

			} else {
				int i,j,n = 1;
				copyfilepath = fileParentPath + "\\" + name1 + "(" + 1 + ").jpg";
				for(i=0;i<UIandImageDT.files.size();i++){
					for(j=0;j<UIandImageDT.files.size();j++){
						if(copyfilepath.substring(copyfilepath.lastIndexOf("\\")+1).equals(UIandImageDT.files.get(j).getName())){
//							System.out.println("文件路径"+copyfilepath.substring(copyfilepath.lastIndexOf("\\")+1)+" "+UIandImageDT.files.get(j).getName());
							n++;					
							copyfilepath = fileParentPath + "\\" + name1 + "(" + n + ").jpg";
							break;
						}
					}
				}
			}
//			System.out.println(copyfilepath);
			File files = new File(copyfilepath);
//			System.out.println(file.getPath());
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", files);
			} catch (IOException e) {
				e.printStackTrace();
			}

			UIandImageDT.change=imageview;
		} 

	}
	
	@FXML 
	private void editchange(){
		slider.setVisible(false);
	}

	@FXML
	private void Save(ActionEvent event) {

		File file = UIandImageDT.file;

		if (file.exists()) {
			Task<Integer> task = new SaveTask();

			new Thread(task).start();
			imageview.setScaleX(4.0);
			imageview.setScaleY(4.0);
			WritableImage image = imageview.snapshot(new SnapshotParameters(), null);
			imageview.setScaleX(1.0);
			imageview.setScaleY(1.0);
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
				UIandImageDT.change=imageview;
				UIandImageDT.change=imageview;
			} catch (IOException ex) {
//				System.out.println(ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private AnchorPane picturepane;

	@FXML
	private void Scroll(ScrollEvent e) {
		if (imageview.getBoundsInParent().getWidth()>=700||imageview.getBoundsInParent().getHeight()>=560) {
			if (e.getDeltaY() < 0) {
				scrollcount += (int) (e.getDeltaY() /40);
				imageview.setScaleX(1 + scrollcount * 0.1);
				imageview.setScaleY(1 + scrollcount * 0.1);
			}
		} else if (imageview.getBoundsInParent().getWidth() <= slider.getWidth()/2) {
			if (e.getDeltaY() > 0) {
				scrollcount += (int) (e.getDeltaY() /40);
				imageview.setScaleX(1+ scrollcount * 0.1);
				imageview.setScaleY(1+ scrollcount * 0.1);
			}
		} else {
			scrollcount += (int) (e.getDeltaY() / 40);
			imageview.setScaleX(1  + scrollcount * 0.1);
			imageview.setScaleY(1  + scrollcount * 0.1);
		}
	}

	private int scrollcount = 0;
	
	
	    @FXML
	    void turnleft(ActionEvent event) {
	    	imageview.setScaleX(1);
			imageview.setScaleY(1);
	    	new TurnleftOP(imageview);
	    	scrollcount=0;
	    }

	    @FXML
	    void turnright(ActionEvent event) {
	    	imageview.setScaleX(1);
			imageview.setScaleY(1);
	    	new TurnRightOP(imageview);
	    	scrollcount=0;
	    }

	    @FXML
	    void mirror(ActionEvent event) {
	    	imageview.setScaleX(1);
			imageview.setScaleY(1);
	    	if (imageview.getNodeOrientation().name().equals("RIGHT_TO_LEFT")) {
				imageview.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			} else {
				imageview.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			}
	    	scrollcount=0;
	    }


	@FXML
	private AnchorPane leftpane, rightpane;
	@FXML
	private AnchorPane toppane;
	@FXML
	private Button quickscreenshot;
	
	 @FXML
	 void Quickscreenshot(ActionEvent event) throws IOException {
		 new ScreenShotOP(UIandImageDT.stage,imageview);
	 }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		slider.setVisible(false);
		this.setImage();
		UIandImageDT.stage.widthProperty().addListener((a) -> {
			imageview.setScaleX(1.0);
			imageview.setScaleY(1.0);
		});
		UIandImageDT.stage.heightProperty().addListener((a) -> {
			imageview.setScaleX(1.0);
			imageview.setScaleY(1.0);
		});
		imageview.fitWidthProperty().bind(UIandImageDT.stage.widthProperty().subtract(rightpane.widthProperty()).divide(4).multiply(3));
      imageview.fitHeightProperty().bind(UIandImageDT.stage.heightProperty().subtract(toppane.heightProperty()).divide(4).multiply(3));

      slider.prefWidthProperty().bind(imageview.fitWidthProperty().divide(4).multiply(3));
   
      FadeTransition fade = new FadeTransition(); 
      fade.setDuration(Duration.seconds(0.5));
      fade.setFromValue(0);
      fade.setToValue(1);
      fade.setNode(Apane);
      fade.play();
	}
	
	@FXML
	private void Change(ActionEvent event) throws IOException {
		stage2 = new Stage();
    	Parent root =FXMLLoader.load(getClass().getResource("/cn/edu/scau/sec/se/fxml/PcUI.fxml"));
		Scene scene2 = new Scene(root);
		scene2.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
		scene2.setFill(Paint.valueOf("#00000000"));
		URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
    	String path = url.toExternalForm();
        scene2.setCursor(Cursor.cursor(path));
		stage2.setTitle("KJNovaClipper");
		stage2.getIcons().add(new Image("cn/edu/scau/sec/se/icon/裁剪界面图标.png"));
		stage2.setScene(scene2);
		stage2.initStyle(StageStyle.TRANSPARENT);
		scene2.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO 自动生成的方法存根
				if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
				x = event.getScreenX()-stage2.getX();
				y = event.getScreenY()-stage2.getY();
				}
			}
		});
		
		scene2.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO 自动生成的方法存根
				if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
					if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX())stage2.setX(event.getScreenX()-x);
					if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY())stage2.setY(event.getScreenY()-y);
				}
			}
		});
		stage2.show();
		new ScreenShotOP(UIandImageDT.stage,imageview);
		KeyCombination key = KeyCombination.valueOf("ctrl+alt+d");
	    Mnemonic mc = new Mnemonic(quickscreenshot, key);
	    scene2.addMnemonic(mc);
	}
}

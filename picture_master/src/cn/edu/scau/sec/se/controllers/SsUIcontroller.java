package cn.edu.scau.sec.se.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import cn.edu.scau.sec.se.models.ImageND;
import cn.edu.scau.sec.se.operations.PbUIOP;

public class SsUIcontroller implements Initializable {
	private ArrayList<ParallelTransition> arrayList = new ArrayList<ParallelTransition>();
	private Timeline timeline;
	private int count;
	private boolean subSceneIsSet=false;
	int i;
	private boolean signal = true;
	private SubScene subScene;
	private URL url;
	private Media media;
	private MediaPlayer mediaPlayer;
	@FXML
	private ImageView imageview;
	@FXML
	private Button start, stop,backBtn;
	@FXML
	private AnchorPane Apane;
	@FXML
	private AnchorPane bosspane;
	@FXML
	private void Begin(ActionEvent e) {
		timeline.play();
		start.setVisible(false);
		stop.setVisible(true);
		signal=false;
		setAnimation();
	}

	@FXML
	private void Stop(ActionEvent e) {
		timeline.pause();
		start.setVisible(true);
		stop.setVisible(false);
		signal=true;
	}

	@FXML
	private void Press(MouseEvent e) {
		if (backBtn.isVisible()&&signal) {
			start.setVisible(false);
			backBtn.setVisible(false);
		} 
		else if(backBtn.isVisible()&&!signal){
			stop.setVisible(false);
			backBtn.setVisible(false);
		}
		else if(!backBtn.isVisible()&&signal){
			start.setVisible(true);
			backBtn.setVisible(true);
		}
		else {
			stop.setVisible(true);
			backBtn.setVisible(true);
		}
	}

	@FXML
	private void Back(ActionEvent e) {
		if(timeline!=null) timeline.stop();
		if(mediaPlayer!=null) mediaPlayer.dispose();
		new PbUIOP();
	}
	
	private void setAnimation(){
		/*动画初始化*/
		if(subSceneIsSet==false){
		subScene=getSubScene(32, 1038, 545, 2500);
		subScene.setOpacity(0);
		bosspane.getChildren().add(subScene);
		subScene.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				if (backBtn.isVisible()&&signal) {
					start.setVisible(false);
					backBtn.setVisible(false);
				} 
				else if(backBtn.isVisible()&&!signal){
					stop.setVisible(false);
					backBtn.setVisible(false);
				}
				else if(!backBtn.isVisible()&&signal){
					start.setVisible(true);
					backBtn.setVisible(true);
				}
				else {
					stop.setVisible(true);
					backBtn.setVisible(true);
				}
			}
		});
		 KeyCombination key = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
			bosspane.getScene().getAccelerators().put(key, new Runnable() {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					arrayList.forEach(new Consumer<ParallelTransition>() {
						@Override
						public void accept(ParallelTransition t) {
							// TODO 自动生成的方法存根
							if(subScene.getOpacity()==0){
							FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
							fadeTransition.setFromValue(0);
							fadeTransition.setToValue(1);
							fadeTransition.setNode(subScene);
							fadeTransition.play();
							t.play();
							}
						}
					});
					mediaPlayer.setVolume(0.2);
					mediaPlayer.play();
				}
			});
			KeyCombination key2 = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
			bosspane.getScene().getAccelerators().put(key2, new Runnable() {
				
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					arrayList.forEach(new Consumer<ParallelTransition>() {

						@Override
						public void accept(ParallelTransition t) {
							// TODO 自动生成的方法存根
							if(subScene.getOpacity()==1){
							FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
							fadeTransition.setFromValue(1);
							fadeTransition.setToValue(0);
							fadeTransition.setNode(subScene);
							fadeTransition.play();
							}
						}
					});
					mediaPlayer.pause();
				}
			});
			subSceneIsSet=true;
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UIandImageDT.change = new ImageView(ImageND.getSlImageNDs().get(0).getImage());
		for(i=0;i<UIandImageDT.files.size();i++){
			if(ImageND.getSlImageNDs().get(0).getImageFile()==UIandImageDT.files.get(i)) break;
//			System.out.println(PictureNode.getSelectedPictures().get(0).getImageFile()+"  "+ChangeService.files.get(i));
		}
//		System.out.println("位置"+i);
//		System.out.println("所在文件图片数："+UIandImageDT.files.size());
		count=i+1-(i+1)/(UIandImageDT.files.size())*(UIandImageDT.files.size());
		stop.setVisible(false);
		imageview.setImage(UIandImageDT.change.getImage());
		imageview.setEffect(UIandImageDT.change.getEffect());
		imageview.setViewport(UIandImageDT.change.getViewport());
		imageview.setNodeOrientation(UIandImageDT.change.getNodeOrientation());
		imageview.setRotate(UIandImageDT.change.getRotate());
		FadeTransition fade = new FadeTransition(); 
        fade.setDuration(Duration.seconds(0.5));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setNode(Apane);
        fade.play();
        timeline = new Timeline();

		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyValue keyValue = new KeyValue(imageview.opacityProperty(), 0);
		KeyValue keyValue2 = new KeyValue(imageview.opacityProperty(), 1);

		EventHandler<ActionEvent> onFinished = (ActionEvent t) -> {
			while(UIandImageDT.files.get(count).length()==0) {
				count=count+1-(count+1)/(UIandImageDT.files.size())*(UIandImageDT.files.size());
			}
			if (count!=i) {
//				System.out.println("文件大小："+UIandImageDT.files.get(count).length()+"文件长度："+UIandImageDT.files.size()+"文件位置"+count);
				try {
					imageview.setImage(new Image(UIandImageDT.files.get(count).toURI().toURL().toString()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			} else if (count==i) {
				try {
					imageview.setImage(new Image(UIandImageDT.files.get(count).toURI().toURL().toString()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				imageview.setOpacity(1);
				timeline.stop();
				start.setVisible(true);
				backBtn.setVisible(true);
				stop.setVisible(false);
				signal=true;
				
			}
			count=count+1-(count+1)/(UIandImageDT.files.size())*(UIandImageDT.files.size());
		};
		KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), keyValue);
		KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(1), keyValue2);
		KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(3), keyValue2);
		KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(4),onFinished, keyValue);
		KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(5), keyValue);

		timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5);
        ImageND.clSelected();
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public SubScene getSubScene(int mount,int w,int h,int z){
		ArrayList<ImageView> imgList =new ArrayList<ImageView>();
		
		Random random = new Random();
		
		int start_x;
		int start_y;
		int start_z;
	    /*设置随机图像*/
		String string;
		String string2=null;
		ImageView imageView;
		int type = random.nextInt(4);
//		System.out.println("type:"+type);
		if(type==0){
			string= "cn/edu/scau/sec/se/icon/花.png";
			url = this.getClass().getClassLoader().getResource("cn/edu/scau/sec/se/music/茶玖 - 春、恋、花以外の（Cover：匀子）.mp3");
		}
		else if(type==1){
			string= "cn/edu/scau/sec/se/icon/雪花.png";
			url = this.getClass().getClassLoader().getResource("cn/edu/scau/sec/se/music/Idina Menzel - Let It Go.mp3");
		}
		else if(type==2){
			string= "cn/edu/scau/sec/se/icon/蔡徐坤小黄鸡.png";
			string2 = "cn/edu/scau/sec/se/icon/篮球.png";
			url = this.getClass().getClassLoader().getResource("cn/edu/scau/sec/se/music/SWIN-S - 只因你太美.mp3");
		}
		else {
			string= "cn/edu/scau/sec/se/icon/星星.png";
			url = this.getClass().getClassLoader().getResource("cn/edu/scau/sec/se/music/Aimer (エメ) - TWINKLE TWINKLE LITTLE STAR.mp3");
		}
		media = new Media(url.toExternalForm());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);//洗脑无限循环
	    /*设置出现的随机位置*/
		for(int i=0;i<mount;i++){
			if(type==2){
				Boolean shape=random.nextBoolean();
				if(shape) {
					imageView=new ImageView(string);
				}
				else {
					imageView=new ImageView(string2);
				}
			}
			else {
		    imageView=new ImageView(string);
			}
			imageView.setPreserveRatio(true);
			if(random.nextInt(2)==0){
				start_x = random.nextInt(w)+random.nextInt(500)+700+random.nextInt(1)*500;
			}
			else if(random.nextInt(2)==1){
				start_x = random.nextInt(w)-random.nextInt(500)-1100-random.nextInt(1)*500;
			}
			else start_x= random.nextInt(w);
			start_y=-1000;
			start_z=random.nextInt(z)+2000;
			imageView.setTranslateX(start_x);
			imageView.setTranslateY(start_y);
			imageView.setTranslateZ(start_z);
			imageView.setOpacity(0);
			imgList.add(imageView);
		}
		/*设置透明的面板*/
		AnchorPane ap =new AnchorPane();
		ap.setStyle("-fx-background-color:#ffffff00");
		ap.getChildren().addAll(imgList);
		SubScene subScene =new SubScene(ap, w, h, true, SceneAntialiasing.BALANCED);
		subScene.setLayoutY(52);//对齐imageView
		PerspectiveCamera camera= new PerspectiveCamera();
		subScene.setCamera(camera);
		
		imgList.forEach(new Consumer<ImageView>() {

			@Override
			public void accept(ImageView t) {
				// TODO 自动生成的方法存根
				double time=random.nextDouble()*5+3;
				TranslateTransition transition =new TranslateTransition(Duration.seconds(time));
				/*位移动画*/
				transition.setFromX(t.getTranslateX());
				transition.setFromY(t.getTranslateY());
				
				transition.setByX(200);
				transition.setByY(2600);
				/*透明度动画*/
				FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(time/2));
				fadeTransition1.setFromValue(0);
				fadeTransition1.setToValue(1);
				
				FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3));
				fadeTransition2.setFromValue(1);
				fadeTransition2.setToValue(0);
				/*串行动画*/
				SequentialTransition sequentialTransition =new SequentialTransition();
				sequentialTransition.getChildren().addAll(fadeTransition1,fadeTransition2);
				
				RotateTransition rotateTransition = new RotateTransition(Duration.seconds(time));
				rotateTransition.setFromAngle(0);
				rotateTransition.setToAngle(360);
                /*并行动画*/
				ParallelTransition parallelTransition = new ParallelTransition();
				parallelTransition.setNode(t);
                parallelTransition.getChildren().addAll(transition,rotateTransition,sequentialTransition);
                parallelTransition.setCycleCount(Animation.INDEFINITE);
                arrayList.add(parallelTransition);
			}
		});
		return subScene;
	}
}

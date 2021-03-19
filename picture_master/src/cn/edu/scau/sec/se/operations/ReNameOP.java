package cn.edu.scau.sec.se.operations;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cn.edu.scau.sec.se.controllers.PbUIcontroller;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import cn.edu.scau.sec.se.models.ImageFL;
import cn.edu.scau.sec.se.models.ImageND;

public class ReNameOP {
	private double x=0;
	private double y=0;
	final private TextField name = new TextField();
	final private TextField startNum = new TextField();
	final private TextField bitNum = new TextField();
	private boolean single;
	private PbUIcontroller pbUIcontroller;
	private Stage stage;
	private Label alarm;
	private Button setTrue,setFalse;
	private GridPane gpane;
	public  ReNameOP(PbUIcontroller mainUI) {
		this.pbUIcontroller = mainUI;
		if(ImageND.getSlImageNDs().size()==1) {
			 single = true;
		}else {
			 single = false;
		}
		stage = new Stage();
		gpane = new GridPane();            
		alarm = new Label();
		alarm.setStyle("-fx-text-fill:#FF0000");
		setTrue = new Button("完成");
		setFalse = new Button("取消");
		setStage();
	}
	private void setStage() {
		gpane.setId("gpane");
		setTrue.setId("Submit");
		setFalse.setId("Cancel");
		gpane.setPadding(new Insets(10, 10, 10, 10));
		gpane.setVgap(5);
		gpane.setHgap(5);
		Label label1 = new Label("Name");
		GridPane.setConstraints(label1, 0, 0);
		label1.setStyle("-fx-text-fill:#ffffff");
		name.setPromptText("Please enter a new name.");
		name.setPrefColumnCount(20);
		name.setTooltip(new Tooltip("请输入新的名字。"));
		URL url =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/文本光标.png");
    	String path = url.toExternalForm();
        name.setCursor(Cursor.cursor(path));
		name.getText();
		GridPane.setConstraints(name, 1, 0);
		gpane.getChildren().addAll(label1,name);
		
		if(single) {	
			GridPane.setConstraints(alarm, 1, 1);
			GridPane.setConstraints(setTrue, 0, 2);
			GridPane.setConstraints(setFalse, 1, 2);
			gpane.getChildren().addAll(setTrue,alarm,setFalse);
		}else {
			Label label2 = new Label("Suffix");
			label2.setStyle("-fx-text-fill:#ffffff");
			GridPane.setConstraints(label2, 0, 1);
			startNum.setPromptText("Please enter a starting number suffix.");
			startNum.setTooltip(new Tooltip("请输入起始数字后缀。"));
			startNum.setPrefColumnCount(15);
			startNum.setCursor(Cursor.cursor(path));
			startNum.getText();
			GridPane.setConstraints(startNum, 1, 1);
			Label label3 = new Label("digit");
			label3.setStyle("-fx-text-fill:#ffffff");
			GridPane.setConstraints(label3, 0, 2);
			bitNum.setPromptText("Please enter the number of digits.");
			bitNum.setPrefColumnCount(10);
			bitNum.getText();
			bitNum.setCursor(Cursor.cursor(path));
			bitNum.setTooltip(new Tooltip("请输入后缀位数。"));
			GridPane.setConstraints(bitNum, 1, 2);
			GridPane.setConstraints(alarm, 1, 3);
			GridPane.setConstraints(setTrue, 0, 4);
			GridPane.setConstraints(setFalse, 1, 4);
			gpane.getChildren().addAll(label2,startNum,label3,bitNum,setTrue,alarm,setFalse);
		}
		
		setTrue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			    public void handle(ActionEvent e) {
					if(single) {
						if ((name.getText() != null && !name.getText().isEmpty())) {
				        	if(renameOne()) {
				        		stage.close();
				        	}else{
					              alarm.setText("The name is repeated!");
				        	}
				        } else {
				        	alarm.setText("You have no input, please input again!");
				        }
					}else {
						if ((name.getText() != null && !name.getText().isEmpty())
							&&(startNum.getText() != null && !startNum.getText().isEmpty())
							&&(bitNum.getText() != null && !bitNum.getText().isEmpty())) {
				        	if(renameMany()) {
				        		stage.close();
				        	}else {
				        		alarm.setText("Error! Try again with other parameters.");
				        	}
				        } else {
				            alarm.setText("You have not completed the infomations.");
				        }
					}
			     }
			 });
		setFalse.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				stage.close();
			}
		});
		 Scene scene=new Scene(gpane);
		 scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
		 scene.setFill(Paint.valueOf("#00000000"));
		 URL url2 =getClass().getClassLoader().getResource("cn/edu/scau/sec/se/icon/鼠标.png");
	     String path2 = url2.toExternalForm();
	     scene.setCursor(Cursor.cursor(path2));
		 gpane.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
					x = event.getScreenX()-stage.getX();
					y = event.getScreenY()-stage.getY();
					}
				}
			});
			
			gpane.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO 自动生成的方法存根
					if(event.getButton().name().equals(MouseButton.PRIMARY.name())){
						if(event.getScreenX()<=Screen.getPrimary().getVisualBounds().getMaxX()) stage.setX(event.getScreenX()-x);
						if(event.getScreenY()<=Screen.getPrimary().getVisualBounds().getMaxY()) stage.setY(event.getScreenY()-y);
					}
				}
			});
		 stage.setTitle("重命名");
		 stage.setScene(scene);
		 stage.setAlwaysOnTop(true);
		 stage.initStyle(StageStyle.TRANSPARENT);
		 stage.initModality(Modality.APPLICATION_MODAL);
		 stage.getIcons().add(new Image("cn/edu/scau/sec/se/icon/编辑图标.png"));
		 stage.setTitle("RenameInterface");
		 stage.show();
		 FadeTransition fade = new FadeTransition(); 
	     fade.setDuration(Duration.seconds(0.2));
	     fade.setFromValue(0);
	     fade.setToValue(1);
	     fade.setNode(gpane);
	     fade.play();
	}
	
	private boolean renameOne() {
		ImageND pNode = ImageND.getSlImageNDs().get(0);
		File file = pNode.getImageFile();
		String pre = file.getParent();
    	String[] strings = file.getName().split("\\.");
    	String suf = strings[strings.length-1];
    	File tmp = new File(pre+"\\"+name.getText()+"."+suf);
    	if(!file.renameTo(tmp)) {
			return false;
		}
		pNode.setSelected(false);
		pbUIcontroller.removePictures(pNode);
		ImageND aNode;
		try {
			aNode = new ImageND(new ImageFL(tmp), pbUIcontroller);
			aNode.setSelected(true);
		    pbUIcontroller.addPictures(aNode);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pbUIcontroller.showPicture();
		Clipboard.getSystemClipboard().clear();
		return true;
	}
	private boolean renameMany() {
          
		File file;
		
		int id = Integer.valueOf(startNum.getText());
		int bit = Integer.valueOf(bitNum.getText());
		if( id<0 || (id + ImageND.getSlImageNDs().size()) >= (int)Math.pow(10, bit)) 
			return false;
		ArrayList<ImageND> oldList = new ArrayList<>();
		ArrayList<ImageND> newList = new ArrayList<>();
		for (ImageND image : ImageND.getSlImageNDs()) {
			file = image.getImageFile();
			String pre = file.getParent();
	    	String[] strings = file.getName().split("\\.");
	    	String suf = strings[strings.length-1];
	    	String newName = createName(id,bit);
	    
	    	File tmp = new File(pre+"\\"+newName+"."+suf);
	    	if(!file.renameTo(tmp)){
				return false;
			}
			oldList.add(image);	
			ImageND newImage;
			try {
				newImage = new ImageND(new ImageFL(tmp), pbUIcontroller);
				newList.add(newImage);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	id++;
		}
		for(int i=0;i<oldList.size();i++) {
			oldList.get(i).setSelected(false);
			pbUIcontroller.removePictures(oldList.get(i));
			newList.get(i).setSelected(true);
			pbUIcontroller.addPictures(newList.get(i));
		}
		pbUIcontroller.showPicture();
		Clipboard.getSystemClipboard().clear();
		return true;
	}
	private String createName(int id,int bit) {
		String newName = name.getText();
		
		int tt = id;
    	int cnt=0;
    	while(tt!=0) {
    		cnt++;
    		tt/=10;
    	}
    	if(id==0)  cnt++;
		while(bit>cnt) {
			newName+=0;
			cnt++;
		}
    	newName += id;
		return newName;
	}
}

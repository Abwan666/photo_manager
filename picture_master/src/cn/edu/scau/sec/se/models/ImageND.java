package cn.edu.scau.sec.se.models;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import cn.edu.scau.sec.se.backstage.MouseEventMT;
import cn.edu.scau.sec.se.controllers.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import cn.edu.scau.sec.se.operations.PdUIOP;

public class ImageND extends Label{
	protected static ArrayList<File> slImageFLs = new  ArrayList<>();
	protected static ArrayList<ImageND> slImageNDs = new ArrayList<>();
	protected static ArrayList<ImageND> ctImageNDs = new ArrayList<>();
	public BooleanProperty selected = new SimpleBooleanProperty();
	private Text imageName;
	private ImageND imageND = this;
	private PbUIcontroller pbUIcontroller;
	private ImageFL imageFL; 
	private Image image;
	private ImageView imageView;
	
	public ImageND(ImageFL imageFL,PbUIcontroller mainUIController) throws MalformedURLException {	
		this.imageFL = imageFL;
		this.pbUIcontroller = mainUIController;
		this.setGraphicTextGap(10);
		this.setPadding(new Insets(1, 1, 1, 1));
		this.setContentDisplay(ContentDisplay.TOP);
		this.setPrefSize(110,110);
		this.image = new Image(imageFL.getImageFile().toURI().toURL().toString(),100,100,true,true);
		this.imageView = new ImageView(image);
		this.imageName = new Text(imageFL.getImageName());
		this.setText(imageName.getText());
		this.setGraphic(imageView);
		imageND.setId("imageND");
		addImageNDListener();
		new RightClickMB(this,pbUIcontroller,true);
	}
	
	public void setSelected(boolean value) {
		boolean istrue = selected.get();
		selected.set(value);
		if (selected.get() && !istrue)
			slImageNDs.add(this);
		else if (istrue && !selected.get())
			slImageNDs.remove(this);
//		System.out.println(slImageNDs.size());
		pbUIcontroller.getTextTwo().setText("Number of selected pictures: "+slImageNDs.size());
	}
	
	public static void clSelected() {
		for (ImageND pNode : slImageNDs) {
			pNode.selected.set(false);
		}
		slImageNDs.removeAll(slImageNDs);
	}
	
	private void addImageNDListener() {
		selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()) {
					imageND.setStyle("-fx-background-color:#FFBBFF;");
					pbUIcontroller.getTextTwo().setText("Number of selected pictures: 0");
				}else {
					imageND.setStyle("-fx-background-color:transparent;");
					pbUIcontroller.getTextTwo().setText("Number of selected pictures: 0");
				}
			}
		});
		this.setOnMouseEntered((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:linear-gradient(to bottom,#fffffd 1%,  #FFBBFF 98%);");
			    
		});
		this.setOnMouseExited((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:transparent;");
			    
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseEventMT(this,imageFL));
	}
	
	public void openAction() {
		new PdUIOP();
	}
	
	public Image getImage() {
		try {
			return image = new Image(imageFL.getImageFile().toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	
	public File getImageFile() {
		return this.imageFL.getImageFile();
	}
	
	public ImageFL getImageFL() {
		return imageFL;
	}
	
	public String getURL() {
		try {
			return this.imageFL.getImageFile().toURI().toURL().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ImageView getImageView() {
		return this.imageView;
	}
	
	public static ArrayList<File> getSlImageFLs() {
		return slImageFLs;
	}
	public static ArrayList<ImageND> getSlImageNDs() {
		return slImageNDs;
	}
	public static void setCtImageNDs(ArrayList<ImageND> ctImageNDs) {
		ImageND.ctImageNDs = ctImageNDs;
	}
	public static void addCtImageNDs(ImageND pNode){
		ImageND.ctImageNDs.add(pNode);
	}
	public static ArrayList<ImageND> getCtImageNDs() {
		return ctImageNDs;
	}
	public static void clCtImageNDs() {
		ctImageNDs.removeAll(ctImageNDs);
	}	
}
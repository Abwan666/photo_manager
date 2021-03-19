package cn.edu.scau.sec.se.operations;

import javafx.scene.image.ImageView;

public class TurnleftOP {
	private ImageView slImage;
	public TurnleftOP(ImageView imageView) {
		slImage = imageView;
		slImage.setRotate((slImage.getRotate() - 90) % 360);
	}
}

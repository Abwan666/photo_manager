package cn.edu.scau.sec.se.operations;

import javafx.scene.image.ImageView;

public class TurnRightOP {

	private ImageView slImage;
	public TurnRightOP(ImageView imageView) {
		slImage = imageView;
		slImage.setRotate((slImage.getRotate() + 90) % 360);
	}
}

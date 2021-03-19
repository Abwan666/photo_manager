package cn.edu.scau.sec.se.operations;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.scene.image.ImageView;

public class ReSetOP {
	public ReSetOP(ImageView imageView) {
//		imageView = ChangeService.origin;
		imageView.setFitHeight(UIandImageDT.preHight);
		imageView.setFitWidth(UIandImageDT.preWidth);
		imageView.setScaleX(1);
		imageView.setScaleY(1);
	}
}

package cn.edu.scau.sec.se.operations;

import java.io.File;
import java.net.MalformedURLException;

import cn.edu.scau.sec.se.backstage.UIandImageDT;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PageTurningOP {
	private static int page ;
	private static Image image;
	
	public PageTurningOP(int i){
		setPage(i);
	}
	
	public static void changePicture(ImageView imageView,Boolean Previous_or_next) {
		File file = UIandImageDT.files.get(page);
		while(file.length()==0) {
			if(Previous_or_next)page++;
			else page--;
			file = UIandImageDT.files.get(page);
		}
		try {
			image = new Image(file.toURI().toURL().toString());
			imageView.setImage(image);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public static int getPage() {
		return page;
	}

	public static void setPage(int page) {
		PageTurningOP.page = page;
	}
}

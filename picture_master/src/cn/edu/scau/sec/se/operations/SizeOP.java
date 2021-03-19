package cn.edu.scau.sec.se.operations;

import javafx.scene.image.ImageView;

public class SizeOP {

	private static double index = 0;

	public static void enlarge(ImageView imageView) {
		index +=1;
//		System.out.println("图片宽高："+imageView.getFitWidth()*((changeNum-1)*0.1+1)+"   "+imageView.getFitHeight()*((changeNum-1)*0.1+1));
		if(imageView.getFitHeight()*(index*0.1+1)<=637&&imageView.getFitWidth()*(index*0.1+1)<=1039){
			imageView.setScaleX(index*0.1+1);
			imageView.setScaleY(index*0.1+1);
			imageView.setPreserveRatio(true);
		}
		else index-=1;
//		System.out.println(changeNum);
	}
	public static void small(ImageView imageView) {
		if((index-1)*0.1+1>0){
		index -=1;
		imageView.setScaleX(index*0.1+1);
		imageView.setScaleY(index*0.1+1);
		imageView.setPreserveRatio(true);
//		System.out.println(changeNum);
		}	
	}
	public static double getChangeNum() {
		return index;
	}
	public static void setChangeNum(double changeNum) {
		SizeOP.index = changeNum;
	}
}

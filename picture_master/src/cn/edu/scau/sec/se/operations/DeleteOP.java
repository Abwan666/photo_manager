package cn.edu.scau.sec.se.operations;

import cn.edu.scau.sec.se.controllers.DdUIcontroller;
import cn.edu.scau.sec.se.controllers.PbUIcontroller;
import javafx.scene.input.Clipboard;
import cn.edu.scau.sec.se.models.ImageND;


public class DeleteOP {
	PbUIcontroller mainUIController;

	public DeleteOP(PbUIcontroller mainUI){
		mainUIController = mainUI;

		if(ImageND.getSlImageNDs().size()<=0) {
			return;
		}
		if(ImageND.getCtImageNDs().size() > 0) {
			for(ImageND pNode : ImageND.getCtImageNDs()) {
				pNode.getImageView().setEffect(null);
			}
			ImageND.getCtImageNDs().clear();
		}
		if(DdUIcontroller.isIsdelete()) {
			double cutedsize=0;
			int i=0;
			for(ImageND pNode : ImageND.getSlImageNDs()) {
				mainUIController.getFlowPane().getChildren().remove(pNode);
				cutedsize+=pNode.getImageFL().length();
				i++;
				for(int j=0;j<mainUI.getPictures().size();j++){
					if(pNode.equals(mainUI.getPictures().get(j))){
						mainUI.getPictures().remove(j);
					}
				}
//				System.out.println("删除："+delete);
				pNode.getImageFile().delete();
			}	
//			System.out.println("删除大小"+cutedsize);
//			System.out.println("删除长度"+i);
			mainUI.getFileTree().setSum(mainUI.getFileTree().getSum()-i);
			mainUI.getFileTree().setSize(mainUI.getFileTree().getSize()-cutedsize);
			mainUI.getText().setText("Number of pictures: "+(mainUI.getFileTree().getSum())+" (" + (double)Math.round(((mainUI.getFileTree().getSize())/1048576)*100)/100 + "Mb)");
		}
		ImageND.getSlImageFLs().clear();
		ImageND.clSelected();
		DdUIcontroller.setIsdelete(false);
		Clipboard.getSystemClipboard().clear();
	}
}

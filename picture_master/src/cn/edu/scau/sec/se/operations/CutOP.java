package cn.edu.scau.sec.se.operations;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import cn.edu.scau.sec.se.models.ImageND;

public class CutOP {

	public CutOP() {
		if(ImageND.getSlImageNDs().size()<=0) {
			return;
		}
		if(ImageND.getCtImageNDs().size() > 0) {
			for(ImageND pNode : ImageND.getCtImageNDs()) {
				pNode.getImageView().setEffect(null);
			}
			ImageND.getCtImageNDs().clear();
		}
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent clipboardContent = new ClipboardContent();
		clipboard.clear();
		for(ImageND pNode : ImageND.getSlImageNDs()) {
			ImageND.getSlImageFLs().add(pNode.getImageFile());
			pNode.getImageView().setEffect(new ColorAdjust(0, 0, 0.5, 0));//标志被剪切
			ImageND.addCtImageNDs(pNode);
		}
		clipboardContent.putFiles(ImageND.getSlImageFLs());
		clipboard.setContent(clipboardContent);
		clipboard = null;
		clipboardContent = null;
		
	}
}

package cn.edu.scau.sec.se.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class CatalogIT extends TreeItem<ImageFL> {
	private boolean isFirstChildren = true;
	private boolean isFirstLeaf = true;
	private boolean isLeaf;

	public CatalogIT(ImageFL pictureFile) {
		super(pictureFile);
	}

	private ObservableList<TreeItem<ImageFL>> buildChildren(TreeItem<ImageFL> TreeItem) {
		ImageFL pictureFile = TreeItem.getValue();

		if (pictureFile != null && pictureFile.isDirectory()) {
			ImageFL[] pictureFiles = pictureFile.listPictures();
			if (pictureFiles != null && pictureFiles.length != 0) {
				ObservableList<TreeItem<ImageFL>> children = FXCollections.observableArrayList();
				for (ImageFL childFile : pictureFiles) {
					if (childFile.isHidden() || childFile.isFile()) {
						continue;
					}
					children.add(new CatalogIT(childFile));
				}
				return children;
			}
		}
		return FXCollections.emptyObservableList();
	}
	
	@Override
	public ObservableList<TreeItem<ImageFL>> getChildren() {
		if (isFirstChildren) {
			isFirstChildren = false;
			super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	}

	@Override
	public boolean isLeaf() {
		if (isFirstLeaf) {
			isFirstLeaf = false;
			ImageFL pictureFile = getValue();
			ImageFL[] pictureFiles = pictureFile.listPictures();
			if (pictureFiles == null ||pictureFiles.length == 0 ) {
				isLeaf = true;
			} else {
				isLeaf = true;
				for (ImageFL pictureFile2 : pictureFiles) {
					if (pictureFile2.isDirectory()) {
						isLeaf = false;
					}
				}
			}

		}
		return isLeaf;
	}
}
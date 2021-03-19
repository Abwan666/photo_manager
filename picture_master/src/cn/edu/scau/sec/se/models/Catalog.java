package cn.edu.scau.sec.se.models;

import java.io.File;
import java.net.MalformedURLException;

import cn.edu.scau.sec.se.controllers.PbUIcontroller;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Duration;

public class Catalog {

	private PbUIcontroller pbUIcontroller;
	private int sum = 0;
	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getSum() {
		return sum;
	}

	public double getSize() {
		return size;
	}
	private double size = 0;
	public void setSize(double size) {
		this.size = size;
	}
	private TreeView<ImageFL> treeView;
	private TreeItem<ImageFL> root;
	private final File[] rootPath = File.listRoots();

	public Catalog(PbUIcontroller mianUI, TreeView<ImageFL> treeView) {

		this.pbUIcontroller = mianUI;
//		 System.out.println(mianUI);
		this.treeView = treeView;
		root = new TreeItem<ImageFL>(new ImageFL("This PC"));
		root.setExpanded(true);
		treeView.setRoot(root);
		buildFileTree();
	    getSelected();
		mianUI.getFlowPane().setOpacity(0);
	}

	private void buildFileTree() {
		for (int i = 0; i < rootPath.length; i++) {
			CatalogIT item = new CatalogIT(new ImageFL(rootPath[i]));
			root.getChildren().add(item);
		}
	}

	public TreeView<ImageFL> gettreeView() {
		return treeView;
	}

	private void getSelected() {
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<ImageFL>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<ImageFL>> observable, TreeItem<ImageFL> oldValue,
					TreeItem<ImageFL> newValue) {
				pbUIcontroller.getFlowPane().getChildren().remove(0, pbUIcontroller.getFlowPane().getChildren().size());
				ImageFL pFile = treeView.getSelectionModel().getSelectedItem().getValue();

				if (pFile.isDirectory()) {
					PbUIcontroller.flpath = pFile.getImageFile().getAbsolutePath();
					ImageFL[] pictureFiles = pFile.listPictures();
					pbUIcontroller.clearPictures();
					int Sum=0;
					double Size=0;
					if(pictureFiles!=null)
					for (ImageFL pictureFile : pictureFiles) {
						if (pictureFile.isPicture()) {
							Sum++;
							Size += pictureFile.getSize();
//							System.out.println(size);
							try {
								ImageND pictureNode = new ImageND(pictureFile, pbUIcontroller);
								pbUIcontroller.getFlowPane().getChildren().add(pictureNode);
								pbUIcontroller.addPictures(pictureNode);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}

						}
					}
					pbUIcontroller.showPicture();
					FadeTransition fade = new FadeTransition();
					fade.setDuration(Duration.seconds(0.5));
				    fade.setFromValue(0);
				    fade.setToValue(1);
				    fade.setNode(pbUIcontroller.getFlowPane());
				    fade.play();
					pbUIcontroller.getText().setText("Number of pictures: "+Sum+" (" + (double)Math.round((Size/1048576)*100)/100 + "Mb)");
					size=Size;
					sum=Sum;
				}
			}
		});
	}

}

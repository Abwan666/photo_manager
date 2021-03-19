package cn.edu.scau.sec.se.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import cn.edu.scau.sec.se.controllers.PbUIcontroller;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import cn.edu.scau.sec.se.models.ImageFL;
import cn.edu.scau.sec.se.models.ImageND;

public class StickOP {
	PbUIcontroller pbUIcontroller;
	public StickOP(PbUIcontroller mainUI) {
		this.pbUIcontroller = mainUI;
		double pastesize=0;
		int i=0;
		Clipboard clipboard = Clipboard.getSystemClipboard();
		List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
		if (files.size() <= 0) {
			return;
		}
		if (ImageND.getCtImageNDs().size() > 0) {
			File first = files.get(0);
			if(first.getParentFile().getAbsolutePath().compareTo(PbUIcontroller.flpath) == 0){
				for(ImageND pNode : ImageND.getCtImageNDs()) {
					pNode.getImageView().setEffect(null);
				}
				ImageND.clSelected();
				ImageND.getCtImageNDs().clear();
				ImageND.getSlImageFLs().clear();
				clipboard.clear();
				return;	
			}
		}
		for(File oldFile : files) {
			String newName = Pasterename(PbUIcontroller.flpath,oldFile.getName());
			File newFile = new File(PbUIcontroller.flpath+File.separator+newName);
			pastesize+=oldFile.length();
			i++;
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(newFile.exists()) {
				try {
					copyFile(oldFile,newFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			System.out.println("文件名："+newFile.getName());	
			}
			try {
				mainUI.getPictures().add(new ImageND(new ImageFL(newFile), pbUIcontroller));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(ImageND.getCtImageNDs().size()>0) {
				oldFile.delete();
			}
			
			pbUIcontroller.showPicture();
		}
//		System.out.println("文件数："+i+"文件大小："+pastesize);
		mainUI.getFileTree().setSum(mainUI.getFileTree().getSum()+i);
		mainUI.getFileTree().setSize(mainUI.getFileTree().getSize()+pastesize);
		mainUI.getText().setText("Number of pictures: "+(mainUI.getFileTree().getSum())+" (" + (double)Math.round(((mainUI.getFileTree().getSize())/1048576)*100)/100 + "Mb)");
		Clipboard.getSystemClipboard().clear();
		ImageND.getSlImageFLs().clear();
	}
	private void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(fromFile);
		FileOutputStream outputStream = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int byteRead;
		while ((byteRead = inputStream.read(b)) > 0) {
			outputStream.write(b, 0, byteRead);
		}
		inputStream.close();
		outputStream.close();
	}
	private String Pasterename(String theFilePath, String name) {
		String newName = name;
		File fatherPathFile = new File(theFilePath);
		File[] filesInFatherPath = fatherPathFile.listFiles();
		for(int i=0;i<filesInFatherPath.length;i++){
			for (File fileInFatherPath : filesInFatherPath) {
				String fileName = fileInFatherPath.getName();
//				System.out.println("文件长度"+filesInFatherPath.length);
				int cmp = newName.compareTo(fileName);
//				System.out.println("新名字"+newName+" 文件名字"+fileName+"\ncmp的值"+cmp);
				if (cmp == 0) {
					String str = null;
					int end = newName.lastIndexOf("."), start = newName.lastIndexOf("-cp");
//					System.out.println("开始："+start+"结束"+end);
					if (start != -1) {
						str = newName.substring(start, end);
						int num;
						try {
							num = Integer.parseInt(str.substring(str.lastIndexOf("-cp") + 3)) + 1;
//							System.out.println("num:"+num);
							int cnt = 0, d = num - 1;
//							System.out.println("d:"+d);
							while (d != 0) {
								d /= 10;
								cnt++;
							}
							newName = newName.substring(0, end - cnt) + num + newName.substring(end);
						} catch (Exception e) {
							newName = newName.substring(0, end) + "-cp1" + newName.substring(end);
						}

					} else {
						newName = newName.substring(0, end) + "-cp1" + newName.substring(end);
					}
				}
			}
		}
		return newName;
	}
	

}

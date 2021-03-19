package cn.edu.scau.sec.se.operations;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

import cn.edu.scau.sec.se.controllers.PfUIcontroller;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenShotOP {
	 static ImageView iv;
	 static Stage primary,stage;
	 static double sceneX_start;
	 static double sceneY_start;
	 static double sceneX_end;
	 static double sceneY_end;
	 static HBox hbox;
    public ScreenShotOP(Stage pstage,ImageView imageView) throws IOException {
    	primary=pstage;
    	iv=imageView;
        stage = new Stage();
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color:#B5B5B522");
        Scene scene = new Scene(an);
        scene.setFill(Paint.valueOf("#ffffff00"));
        scene.getStylesheets().add("cn/edu/scau/sec/se/css/dbcss.css");
        scene.setCursor(Cursor.CROSSHAIR);
        stage.setFullScreenExitHint("");// 设置空字符串
        stage.setScene(scene);
        stage.setFullScreen(true);// 全屏
        stage.initStyle(StageStyle.TRANSPARENT);// 透明
        stage.show();
        drag(an);//调用矩形拖拉

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent event) {
                // TODO 自动生成的方法存根
                if (event.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                    primary.setIconified(false);
                }
                
           }

        });
        
    }

    /* 矩形拖拉 */
    public void drag(AnchorPane an) {
       an.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
            public void handle(MouseEvent arg0) {

               an.getChildren().clear();

               hbox = new HBox();
              hbox.setBackground(null);
                hbox.setBorder(new Border(new BorderStroke(Paint.valueOf("#FFBBFF"), BorderStrokeStyle.SOLID, null,
                        new BorderWidths(2))));
               /* 获取坐标 */
              sceneX_start = arg0.getSceneX();
               sceneY_start = arg0.getSceneY();

                an.getChildren().add(hbox);

               AnchorPane.setLeftAnchor(hbox, sceneX_start);
                AnchorPane.setTopAnchor(hbox, sceneY_start);
            }

        });
        /* 拖拽检测 */
        an.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
           public void handle(MouseEvent event) {
               // TODO 自动生成的方法存根
               an.startFullDrag();
            }

        });
         /* 拖拽获取坐标 */
        an.setOnMouseDragOver(new EventHandler<MouseEvent>() {
            @Override
           public void handle(MouseEvent event) {

               Label label = new Label();
               label.setAlignment(Pos.CENTER);
               label.setPrefWidth(170);
               label.setPrefHeight(30);

               an.getChildren().add(label);

               AnchorPane.setLeftAnchor(label, sceneX_start);
                AnchorPane.setTopAnchor(label, sceneY_start - label.getPrefHeight());
               label.setTextFill(Paint.valueOf("#ffffff"));
               label.setStyle("-fx-background-color:#BFDADE");

                double sceneX = event.getSceneX();
                double sceneY = event.getSceneY();

                double width = sceneX - sceneX_start;
                double height = sceneY - sceneY_start;

                hbox.setPrefWidth(width);
                hbox.setPrefHeight(height);

                label.setText("宽度：" + width + "高度：" + height);

            }

        });
        /*当鼠标拖拽出矩形后，可以通过点击完成，得到截图*/
        an.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                // TODO 自动生成的方法存根
             sceneX_end = event.getSceneX();
               sceneY_end = event.getSceneY();

                Button btn_fin = new Button("完成");
                btn_fin.setId("Submit");
                hbox.getChildren().add(btn_fin);
               hbox.setAlignment(Pos.BOTTOM_RIGHT);

                btn_fin.setOnAction(new EventHandler<ActionEvent>() {
                   public void handle(ActionEvent arg0) {
                        // TODO 自动生成的方法存根
                        try {
                             getScreenImg();
                        } catch (Exception e) {
                            // TODO 自动生成的 catch 块
                           e.printStackTrace();
                        }
                    }
                });
            }
        });
 
     }
 
    public void getScreenImg() throws Exception {
         stage.close();// 关闭当前窗口
         double w = sceneX_end - sceneX_start;
        double h = sceneY_end - sceneY_start;

         /*截图*/
        Robot robot = new Robot();
         Rectangle rec = new Rectangle((int) sceneX_start, (int) sceneY_start, (int) w, (int) h);
        BufferedImage buffimg = robot.createScreenCapture(rec);
        
         /*将图片显示在面板上*/
        WritableImage wi = SwingFXUtils.toFXImage(buffimg, null);
         iv.setImage(wi);
         PfUIcontroller.getStage2().close();
    }
 }

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Painter
 * Author:   Zephon
 * Date:     2018/11/28 21:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 〈一句话功能简述〉<br> 
 * 〈画板模块〉
 * @author Zephon
 * @create 2018/11/28
 * @since 1.0.0
 */
public class Painter extends VBox {
    private Drawer drawer;
    private Button btPenSet;
    private Button btEraserSet;
    private ColorPicker btColorSet;
    private Button btClear;
    private double WIDTH=800,HEIGHT=600;

    public Painter(){
        init();
    }

    /**
     * 初始化布局与控件，并添加点击事件
     */
    public void init(){
        btPenSet = new Button("画笔");
        btPenSet.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btEraserSet = new Button("橡皮擦");
        btEraserSet.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btColorSet = new ColorPicker(Color.BLACK);
        btColorSet.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btClear = new Button("清空画布");
        btClear.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        HBox hBox = new HBox(10,btPenSet,btEraserSet,btColorSet,btClear);
        hBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(btColorSet, Priority.ALWAYS);
        HBox.setHgrow(btEraserSet, Priority.ALWAYS);
        HBox.setHgrow(btPenSet, Priority.ALWAYS);
        HBox.setHgrow(btClear, Priority.ALWAYS);

        drawer = new Drawer(WIDTH,480.0/500.0*HEIGHT);
        drawer.setColor(Color.BLACK);

        penClickedEvent();
        eraserClickedEvent();
        colorClickedEvent();
        clearClickedEvent();

        new Thread(){}.start();

        this.getChildren().addAll(hBox,drawer);
        this.setStyle("-fx-background-color: #fff");
    }

    /**
     * 设置笔刷点击事件
     */
    public void penClickedEvent(){
        btPenSet.setOnAction(event -> {
            drawer.setColor(Color.BLACK);
        });

    }

    /**
     * 设置橡皮擦工具点击事件
     */
    public void eraserClickedEvent(){
        btEraserSet.setOnAction(event -> {
            drawer.erase();
        });
    }

    /**
     * 设置颜色修改点击事件
     */
    public void colorClickedEvent(){
        btColorSet.setOnAction(event -> {
            drawer.setColor(btColorSet.getValue());
        });
    }

    public void clearClickedEvent(){
        btClear.setOnAction(event -> {
            drawer.clear();
        });
    }

    //获得Canvas图片文件
    //但无法获得流。。。
    public void getPic(Socket socket){
        WritableImage image = drawer.snapshot(new SnapshotParameters(), null);
        File file = new File("E:a.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = socket.getOutputStream();
            byte[] buf = new byte[1024];
            int len=0;
            while(-1!=(len=fis.read(buf))){
                os.write(buf, 0, len);
            }
            System.out.println("保存成功");
        } catch (IOException ex) {
            System.out.println("保存失败");
        }

    }
}
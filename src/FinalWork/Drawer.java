/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Drawer
 * Author:   Zephon
 * Date:     2018/11/27 23:44
 * Description: 画画者类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * 〈一句话功能简述〉<br> 
 * 〈画画者类〉
 *
 * @author Zephon
 * @create 2018/11/27
 * @since 1.0.0
 */
public class Drawer extends Canvas {
    private String name;
    private GraphicsContext gc;
    private double startX,startY,endX,endY;
    private Button btPenSet;
    private Button btEraserSet;
    private Button btColorSet;
    private Paint color;

    /**
     * 构造函数初始化
     * @param width
     * @param height
     */
    public Drawer(double width,double height){
        super(width,height);
        color = Color.BLACK;
        gc = getGraphicsContext2D();
        gc.save();
    }

    /**
     * 画笔---设置颜色---宽度
     */
    private void draw(){
        gc.setLineWidth(2);
        gc.setStroke(color);
        paint();
    }

    /**
     * 橡皮擦
     */
    public void erase(){
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(10);
        paint();
    }

    /**
     * 设置颜色
     * @param color
     */
    public void setColor(Paint color){
        this.color = color;
        draw();
    }

    /**
     * 绘图功能
     */
    public void paint(){
        setOnMousePressed(event -> {
            startX = event.getX();
            startY = event.getY();
        });
        setOnMouseDragged(event -> {
            endX = event.getX();
            endY = event.getY();
            gc.strokeLine(startX, startY, endX, endY);
            startX = endX;
            startY = endY;
        });
    }

    /**
     * 清屏
     */
    public void clear(){
       gc.setFill(Color.WHITE);
       gc.fillRect(0,0,getWidth(),getHeight());
    }
}
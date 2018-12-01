/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Guesser
 * Author:   Zephon
 * Date:     2018/11/27 23:45
 * Description: 猜测者的类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Iterator;


/**
 * 〈一句话功能简述〉<br> 
 * 〈猜测者的类〉
 *
 * @author Zephon
 * @create 2018/11/27
 * @since 1.0.0
 */
public class Guesser extends VBox {
    Canvas canvas;
    private double WIDTH=800,HEIGHT=600;
    private Socket picSocket;

    public Guesser(){
        try {
            picSocket = new Socket("localhost",9999);
        } catch (Exception e) {
            System.out.println("图片服务器连接失败");
        }

        this.setAlignment(Pos.CENTER);
        canvas = new Canvas(WIDTH,480.0/500.0*HEIGHT);
        canvas.maxHeight(Double.MAX_VALUE);
        canvas.maxWidth(Double.MAX_VALUE);
        this.getChildren().add(canvas);
        this.setVgrow(canvas, Priority.ALWAYS);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText("hello",10,10);
        new Thread(new ReceivePic(picSocket,gc)).start();
        //new Thread(new Draw(gc)).start();
        //Image img = new Image("file:E:a.png");
        //gc.drawImage(img, 0, 0);
    }
}
class ReceivePic implements Runnable {
    private boolean isRunning = true;
    private InputStream is;
    private Socket socket;
    private GraphicsContext gc;
    public  ReceivePic(Socket socket,GraphicsContext gc){
        this.socket = socket;
        try {
            this.gc = gc;
            is =socket.getInputStream();
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
        }
    }
    private void receive(){
        byte[] buf = new byte[1024];
        int len = 0;
        FileOutputStream fos = null;
        try {
            System.out.println("正在接收数据...");
            while((len = is.read(buf)) != -1){  //阻塞
                if(fos == null) { fos = new FileOutputStream("c.png"); }
                fos.write(buf, 0, len);
                //怎样判断文件接收完成？
                //1. 如果对方断开连接，本循环自动退出
                //2. 如果对方保持连接，用超时进行判断
                socket.setSoTimeout(50); //设置100ms超时
            }
        }  catch (SocketTimeoutException eTimeout) {
            gc.drawImage(new Image("file:c.png"),0,0);
        } catch (IOException e){
            isRunning = false;
        }
    }
    @Override
    public void run() {
        while(isRunning){
            receive();

        }
    }

}

class Draw implements Runnable{
    private GraphicsContext gc;
    boolean isRunning = true;
    public Draw(GraphicsContext gc){
        this.gc = gc;
    }

    @Override
    public void run() {
        while(isRunning){
            Image img = new Image("file:c.png");
            gc.drawImage(img, 0, 0);
            System.out.println("draw");
        }
    }
}
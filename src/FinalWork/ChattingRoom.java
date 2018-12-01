/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ChattingRoom
 * Author:   Zephon
 * Date:     2018/11/28 21:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈聊天室模块区〉
 *
 * @author Zephon
 * @create 2018/11/28
 * @since 1.0.0
 */
public class ChattingRoom extends VBox {
    private String name;
    private TextArea text;
    private TextField input;
    private Button send;
    private Socket socket;
    private boolean isDrawer;
    public ChattingRoom(String name,boolean isDrawer){
        super();
        this.isDrawer = isDrawer;
        text = new TextArea();
        input = new TextField();
        send = new Button("Send");

        this.getChildren().addAll(text,input,send);
        if(isDrawer){
            this.name = "画手:"+name;
        }else{
            this.name = "猜者"+name;
        }

        text.setMaxHeight(Double.MAX_VALUE);
        text.setMaxWidth(Double.MAX_VALUE);
        text.setWrapText(true);
        VBox.setVgrow(text, Priority.ALWAYS);

        try {
            socket = new Socket("localhost",8888);
            Send s = new Send(socket,input,send,this.name);
            s.send();
            new Thread(new Receive(socket,text)).start();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("聊天服务器连接失败");
        }
    }
}
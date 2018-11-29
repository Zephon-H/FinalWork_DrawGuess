/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Send
 * Author:   Zephon
 * Date:     2018/11/28 21:21
 * Description: 客户端发送线程类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 〈一句话功能简述〉<br> 
 * 〈客户端发送类〉
 *  不需要开辟线程
 *
 * @author Zephon
 * @create 2018/11/28
 * @since 1.0.0
 */
public class Send {
    private String name;
    private Socket socket;
    private TextField input;
    private Button send;
    private String msg="";
    DataOutputStream dos ;
    public Send(Socket socket,TextField input,Button send,String name){
        this.name = name;
        this.socket = socket;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            this.input = input;
            this.send = send;
        } catch (IOException e) {

        }
    }

    public String send(){
            send.setOnAction(event -> {
                try {
                    msg = input.getText();
                    dos.writeUTF(name + ":" + msg + "\n");

                } catch (IOException e) {

                }
                input.setText("");
                input.requestFocus();
            });
        return msg;
    }
}
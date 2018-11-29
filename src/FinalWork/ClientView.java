/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ClientView
 * Author:   Zephon
 * Date:     2018/11/28 14:56
 * Description: 用户界面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户界面总体模块〉
 *
 * @author Zephon
 * @create 2018/11/28
 * @since 1.0.0
 */
public class ClientView {
    private Stage client;
    private String username;
    private String pwd;
    private String title;
    private boolean isDrawer = false;
    private static double CLIENT_WIDTH=1000;
    private static double CLIENT_HEIGHT=500;

    /**
     * 界面设计初始化
     * @param username
     * @param pwd
     */
    public ClientView(String username,String pwd){
        client = new Stage();
        this.username = username;
        this.pwd = pwd;

        setRole();
        client.setTitle("欢迎您!"+username);
        client.setWidth(CLIENT_WIDTH);
        client.setHeight(CLIENT_HEIGHT);
        client.setResizable(false);
        client.show();
        client.setOnCloseRequest(event -> {
            System.exit(0);
        });

    }

    public void setTitle() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("选择题目");
        alert.setHeaderText("请从一下题目中选择一个");
        ButtonType btt1 = new ButtonType("1");
        ButtonType btt2 = new ButtonType("2");
        ButtonType btt3 = new ButtonType("3");
        ButtonType btt4 = new ButtonType("4");
        alert.getButtonTypes().setAll(btt1,btt2,btt3,btt4);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==btt1){
            title = btt1.getText();
        }else if(result.get()==btt2){
            title = btt2.getText();
        }else if(result.get()==btt3){
            title = btt3.getText();
        }else{
            title = btt4.getText();
        }
        HBox hBox = new HBox(new Paint(),new ChattingRoom(username,isDrawer));
        Title.setTitle(title);
        Scene scene = new Scene(hBox);
        client.setScene(scene);
    }

    public void setRole(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("登陆成功");
        alert.setHeaderText("请选择您的身份");
        ButtonType btt1 = new ButtonType("我是画手");
        ButtonType btt2 = new ButtonType("我是猜者");
        alert.getButtonTypes().setAll(btt1,btt2);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()==btt1){
            isDrawer = true;
            setTitle();
        }else{
            HBox hBox = new HBox(new Guesser(),new ChattingRoom(username,isDrawer));
            Scene scene = new Scene(hBox);
            client.setScene(scene);
        }
    }
}
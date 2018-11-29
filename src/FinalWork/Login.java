/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Login
 * Author:   Zephon
 * Date:     2018/11/28 14:27
 * Description: 登陆界面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package FinalWork;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 〈一句话功能简述〉<br> 
 * 〈登陆界面〉
 *
 * @author Zephon
 * @create 2018/11/28
 * @since 1.0.0
 */
public class Login extends Application {

    private Scene scene ;
    private TextField textUser;
    private PasswordField textpwd ;
    private Button btLogin ;
    private Button btCancel;
    private int count;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        btCancel.setOnAction(event -> Platform.exit());
        btLogin.setOnAction(event ->{
            loginClickedEvent(primaryStage);
        });
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER ), ()->{
            loginClickedEvent(primaryStage);
        });
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ESCAPE ), ()->{
            Platform.exit();
        });
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        primaryStage.setTitle("登陆");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        count = 0;
        Label u = new Label("用户名");
        Label p = new Label("密   码");
        u.setPrefWidth(50);
        p.setPrefWidth(50);
        textUser = new TextField();
        textpwd = new PasswordField();
        btLogin = new Button("登陆");
        btCancel = new Button("取消");

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #edffe1");
        gridPane.setHgap(5);
        gridPane.setVgap(15);

        gridPane.setMargin(btCancel,new Insets(0,0,0,150));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(u,0,0);
        gridPane.add(textUser,1,0);
        gridPane.add(p,0,1);
        gridPane.add(textpwd,1,1);
        gridPane.add(btLogin,0,2);
        gridPane.add(btCancel,1,2);
        scene = new Scene(gridPane);
    }

    private void loginClickedEvent(Stage primaryStage){
        new ClientView("","");//仅调试使用
        primaryStage.close();//仅调试使用

        /*
        count++;
        String username = textUser.getText();
        String pwd = textpwd.getText();
        if(username.equals("username")&&pwd.equals("123456")){
            try {
                ClientView c = new ClientView(username,pwd);
                primaryStage.close();
                System.out.println("启动");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(count<3){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"用户名或密码错误",new ButtonType("确定",ButtonBar.ButtonData.YES));
            alert.setTitle("提示");

            Optional<ButtonType> bt = alert.showAndWait();
            if(bt.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
                textUser.clear();
                textpwd.clear();
                textUser.requestFocus();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"用户名或密码错误次数过多",new ButtonType("退出",ButtonBar.ButtonData.YES));
            alert.setTitle("提示");
            Optional<ButtonType> bt = alert.showAndWait();
            if(bt.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
                Platform.exit();
            }
        }
        */
    }
}
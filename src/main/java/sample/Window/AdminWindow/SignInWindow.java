package sample.Window.AdminWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/SignInFXML.fxml"));
        primaryStage.setTitle("Đăng nhập hệ thống");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        LibraryBUS.setUpData();
        launch(args);
    }
}

package sample.Window.AdminWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.AddStaffDialogController;
import sample.Controller.AdminController.GroupBookDetailDialogController;

import java.io.IOException;

public class GroupBookDetailDialogWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(GroupBookDetailDialogController.class.getResource("/fxml/AdminFXML/GroupBookDetailDialog.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Xem chi tiết đầu sách");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 450));
        stage.showAndWait();
    }
}

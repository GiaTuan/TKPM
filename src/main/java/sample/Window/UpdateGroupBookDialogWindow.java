package sample.Window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.GroupBookDetailDialogController;
import sample.Controller.AdminController.UpdateGroupBookDialogController;

import java.io.IOException;

public class UpdateGroupBookDialogWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(UpdateGroupBookDialogController.class.getResource("/fxml/AdminFXML/UpdateGroupBookDialog.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Xem chi tiết đầu sách");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 450));
        stage.showAndWait();
    }
}

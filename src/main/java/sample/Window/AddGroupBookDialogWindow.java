package sample.Window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.AddGroupBookDialogController;
import sample.Controller.AdminController.AddStaffDialogController;

import java.io.IOException;

public class AddGroupBookDialogWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AddGroupBookDialogController.class.getResource("/fxml/AdminFXML/AddGroupBookDialog.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Thêm sách");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 450));
        stage.showAndWait();
    }
}

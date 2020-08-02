package sample.Window.AdminWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.AddStaffDialogController;
import sample.Controller.AdminController.UpdateRegulationDialogController;

import java.io.IOException;

public class AddStaffDialogWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AddStaffDialogController.class.getResource("/fxml/AdminFXML/AddStaffDialog.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Thêm thủ thư");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 450));
        stage.showAndWait();
    }
}

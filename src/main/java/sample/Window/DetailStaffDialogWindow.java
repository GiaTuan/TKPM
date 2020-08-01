package sample.Window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.StaffDetailDialogController;
import sample.Controller.AdminController.UpdateRegulationDialogController;

import java.io.IOException;

public class DetailStaffDialogWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StaffDetailDialogController.class.getResource("/fxml/AdminFXML/StaffDetailDialog.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Thông tin chi tiết");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 450));
        stage.showAndWait();
    }
}

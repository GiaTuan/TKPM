package sample.Window.LibrarianWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.UpdateRegulationDialogController;

import java.io.IOException;

public class SignUpReaderWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpReaderWindow.class.getResource("/fxml/LibrarianFXML/SignUpFXML.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Thêm độc giả");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 400, 500));
        stage.showAndWait();
    }
}

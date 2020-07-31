package sample.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateRuleDialogWindow {
    public static void display() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(UpdateRegulationDialogController.class.getResource("/fxml/UpdateRegulationDialog.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Cập nhật quy định");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 500, 400));
        stage.showAndWait();
    }
}

package sample.Window.AdminWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.DetailReaderController;
import sample.POJO.Reader;

import java.io.IOException;

public class DetailReaderWindow {
    public static void display(Reader selectedReader) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DetailReaderWindow.class.getResource("/fxml/AdminFXML/DetailReaderFXML.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Chi tiết độc giả");
        DetailReaderController detailReaderController = fxmlLoader.getController();
        detailReaderController.setSelectedReader(selectedReader);
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.showAndWait();
    }
}

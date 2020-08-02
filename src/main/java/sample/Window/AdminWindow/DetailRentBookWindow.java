package sample.Window.AdminWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.DetailRentBookController;
import sample.POJO.RentBook;

import java.io.IOException;

public class DetailRentBookWindow {
    public static void display(RentBook rentBook) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DetailReaderWindow.class.getResource("/fxml/AdminFXML/DetailRentBookFXML.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Chi tiết độc giả");
        DetailRentBookController detailRentBookController = fxmlLoader.getController();
        detailRentBookController.setRentBook(rentBook);
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.showAndWait();
    }
}

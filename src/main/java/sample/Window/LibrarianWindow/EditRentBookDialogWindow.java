package sample.Window.LibrarianWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.DetailRentBookController;
import sample.Controller.LibrarianController.EditRentBookDialog;
import sample.POJO.RentBook;
import sample.Window.AdminWindow.DetailReaderWindow;

import java.io.IOException;

public class EditRentBookDialogWindow {
    public static void display(RentBook rentBook) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DetailReaderWindow.class.getResource("/fxml/LibrarianFXML/EditRentBookDialog.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Chi tiết mượn sách");

        EditRentBookDialog editRentBookDialog = fxmlLoader.getController();
        editRentBookDialog.setRentBook(rentBook);

        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.showAndWait();
    }
}

package sample.Window.AdminWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller.AdminController.DetailRentBookController;
import sample.Controller.AdminController.DetailReportController;
import sample.POJO.RentBook;
import sample.POJO.Report;

import java.io.IOException;

public class DetailReportWindow {
    public static void display(Report report) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(DetailReportWindow.class.getResource("/fxml/AdminFXML/DetailReportFXML.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Chi tiết mượn sách");
        DetailReportController detailReportController = fxmlLoader.getController();
        detailReportController.setReport(report);
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.showAndWait();
    }
}

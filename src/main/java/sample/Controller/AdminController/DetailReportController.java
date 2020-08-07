package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.POJO.Report;

import java.net.URL;
import java.util.ResourceBundle;


public class DetailReportController implements Initializable {

    @FXML
    TextField idReportTextField;
    @FXML
    TextField idBookTextField;
    @FXML
    TextField idReaderTextField;
    @FXML
    TextArea detailReportTextArea;

    Report report;

    public void cancelBtnClick(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            idReportTextField.setText(String.valueOf(report.getIdReport()));
            idBookTextField.setText(report.getIdBook());
            idReaderTextField.setText(String.valueOf(report.getIdReader()));
            detailReportTextArea.setText(report.getDetailReport());
        });
    }


    public void setReport(Report report) {
        this.report= report;
    }
}

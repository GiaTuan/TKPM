package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.POJO.Regulation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateRegulationDialogController implements Initializable {
    @FXML
    private Text regulationId;
    @FXML
    private TextField regulationName;
    @FXML
    private TextField regulationDetail;

    private static Regulation selectedRegulation;
    private static Regulation cacheData = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(()->{
            regulationId.setText("Quy định " + Integer.toString(selectedRegulation.getId()));
            regulationName.setText(selectedRegulation.getName());
            regulationDetail.setText(Integer.toString(selectedRegulation.getDetail()));
        });
    }

    @FXML
    private void cancleBtnClick(ActionEvent e)
    {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void updateBtnClick(ActionEvent e)
    {
        cacheData = new Regulation(selectedRegulation.getId(), regulationName.getText(), Integer.parseInt(regulationDetail.getText()));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }



    public static void setSelectedRegulation(Regulation selectedItem)
    {
        selectedRegulation = selectedItem;
    }

    public static Regulation getCacheData()
    {
        return cacheData;
    }


}

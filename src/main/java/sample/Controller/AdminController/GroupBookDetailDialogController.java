package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.POJO.GroupBook;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupBookDetailDialogController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField typeBook;
    @FXML
    TextField author;
    @FXML
    TextField publisher;
    @FXML
    TextField publishDate;
    @FXML
    TextField importDate;
    @FXML
    TextField amount;
    @FXML
    TextField status;

    private static GroupBook groupBookSelected = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            name.setText(groupBookSelected.getNameBook());
            typeBook.setText(groupBookSelected.getTypeBook().toString());
            author.setText(groupBookSelected.getAuthor());
            publisher.setText(groupBookSelected.getPublisher());
            publishDate.setText(groupBookSelected.getPublishDate().toString());
            importDate.setText(groupBookSelected.getImportDate().toString());
            amount.setText(Integer.toString(groupBookSelected.getQuantity()));
            status.setText(groupBookSelected.getIsAvailable() == 0 ? "Chưa nhập" : "Sẵn sàng");
        });
    }

    public static void setGroupBookSelected(GroupBook selectedItem)
    {
        groupBookSelected = selectedItem;
    }

    @FXML
    private void cancelBtnClick(ActionEvent e)
    {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}

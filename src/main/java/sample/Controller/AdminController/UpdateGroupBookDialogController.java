package sample.Controller.AdminController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.GroupBook;
import sample.POJO.Publisher;
import sample.POJO.TypeBook;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateGroupBookDialogController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private ComboBox typeBook;
    @FXML
    private TextField author;
    @FXML
    private ComboBox publisher;
    @FXML
    private DatePicker publisherDate;
    @FXML
    private TextField amount;
    @FXML
    private TextField importDate;
    @FXML
    private ComboBox status;
    @FXML
    private Text nofication;

    private ObservableList<TypeBook> listTypeBook;
    private ObservableList<Publisher> listPublisher;
    private ObservableList<String> listStatus;

    private static GroupBook groupBookSelected;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(()->{
            name.setText(groupBookSelected.getNameBook());

            typeBook.getSelectionModel().select(groupBookSelected.getTypeBook().toString());
            author.setText(groupBookSelected.getAuthor());
            publisher.getSelectionModel().select(groupBookSelected.getPublisher());
            publisherDate.getEditor().setText(groupBookSelected.getPublishDate().toString());
            importDate.setText(groupBookSelected.getImportDate().toString());
            amount.setText(Integer.toString(groupBookSelected.getQuantity()));
            status.getSelectionModel().select(groupBookSelected.getIsAvailable() == 0 ? "Chưa nhập" : "Sẵn sàng");
        });
        loadInfo();
    }

    private void loadInfo()
    {
        listTypeBook = FXCollections.observableArrayList(LibraryBUS.getTypeBookList());
        typeBook.setItems(listTypeBook);

        listPublisher = FXCollections.observableArrayList(LibraryBUS.getPublisherList());
        publisher.setItems(listPublisher);

        listStatus = FXCollections.observableArrayList();
        listStatus.addAll("Chưa nhập", "Sẵn sàng");
        status.setItems(listStatus);
    }

    @FXML
    private void cancelBtnClick(ActionEvent e)
    {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean checkValidData()
    {
        if(name.getText().isEmpty() || author.getText().isEmpty() || publisherDate.getEditor().getText().isEmpty())
        {
            nofication.setText("Phải điền đầy đủ thông tin");
            return false;
        }

        return true;
    }
    @FXML
    private void updateBtnClick(ActionEvent e)
    {
        if(!checkValidData())
            return;


        GroupBook tempData = new GroupBook();
        tempData.setIdGroupBook(groupBookSelected.getIdGroupBook());
        tempData.setNameBook(name.getText());

        if(typeBook.getSelectionModel().isEmpty())
            tempData.setIdTypeBook(0);
        else
            tempData.setIdTypeBook(typeBook.getSelectionModel().getSelectedIndex() + 1);
        tempData.setAuthor(author.getText());

        if(publisher.getSelectionModel().isEmpty())
            tempData.setIdPublisher(0);
        else
            tempData.setIdPublisher(publisher.getSelectionModel().getSelectedIndex() + 1);

        if(status.getSelectionModel().isEmpty())
            tempData.setIsAvailable(-1);
        else
            tempData.setIsAvailable(status.getSelectionModel().getSelectedIndex());

        LibraryBUS.updateGroupBookDetail(tempData);

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public static void setGroupBookSelected(GroupBook selectedItem)
    {
        groupBookSelected = selectedItem;
    }
}

package sample.Controller.AdminController;

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

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddGroupBookDialogController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private ComboBox typeBook;
    @FXML
    private TextField author;
    @FXML
    private ComboBox publisher;
    @FXML
    private DatePicker date;
    @FXML
    private TextField amount;
    @FXML
    private ComboBox status;
    @FXML
    private Text nofication;

    private ObservableList<TypeBook> listTypeBook;
    private ObservableList<Publisher> listPublisher;
    private ObservableList<String> listStatus;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    private boolean checkInfoValid()
    {
        if(name.getText().isEmpty() || author.getText().isEmpty() || amount.getText().isEmpty()
        || typeBook.getSelectionModel().isEmpty() || publisher.getSelectionModel().isEmpty()
        || status.getSelectionModel().isEmpty() || date.getEditor().getText().isEmpty())
        {
            nofication.setText("Không được bỏ trống thông tin");
            return false;
        }

        if(!LibraryBUS.isNumber(amount.getText()))
        {
            nofication.setText("Số lượng không hợp lệ");
            return false;
        }
        return true;
    }

    @FXML
    private void addGroupBook(ActionEvent e)
    {
        if(!checkInfoValid())
            return;

        GroupBook newGroupBook = new GroupBook();

        newGroupBook.setNameBook(name.getText());
        newGroupBook.setIdTypeBook(typeBook.getSelectionModel().getSelectedIndex() + 1);
        newGroupBook.setAuthor(author.getText());
        newGroupBook.setPublishDate(Date.valueOf(date.getValue()));
        newGroupBook.setImportDate(Date.valueOf(LocalDate.now()));
        newGroupBook.setIdPublisher(publisher.getSelectionModel().getSelectedIndex() + 1);
        newGroupBook.setIsAvailable(status.getSelectionModel().getSelectedIndex());
        newGroupBook.setQuantity(Integer.parseInt(amount.getText()));

        LibraryBUS.addGroupBook(newGroupBook);

        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}

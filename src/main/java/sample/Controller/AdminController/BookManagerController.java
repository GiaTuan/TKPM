package sample.Controller.AdminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.BUS.LibraryBUS;
import sample.POJO.GroupBook;
import sample.POJO.Publisher;
import sample.POJO.TypeBook;
import sample.Window.AdminWindow.AddGroupBookDialogWindow;
import sample.Window.AdminWindow.GroupBookDetailDialogWindow;
import sample.Window.AdminWindow.UpdateGroupBookDialogWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookManagerController implements Initializable {

    @FXML
    TextField filterText;
    @FXML
    ComboBox displayMode;
    @FXML
    TableView table;
    @FXML
    TableColumn<GroupBook, Integer> id;
    @FXML
    TableColumn<GroupBook, String> name;
    @FXML
    TableColumn<GroupBook, Integer> amount;
    @FXML
    TableColumn<GroupBook, TypeBook> type;
    @FXML
    TableColumn<GroupBook, String> author;
    @FXML
    TableColumn<GroupBook, Publisher> publisher;
    @FXML
    TableColumn<GroupBook, Integer> status;

    private ObservableList<GroupBook> originalData = null;
    private ObservableList<GroupBook> filterList = null;
    private ObservableList<GroupBook> bindingList = null;
    @Override


    public void initialize(URL location, ResourceBundle resources)
    {
        originalData = FXCollections.observableArrayList();
        filterList = FXCollections.observableArrayList();
        bindingList = FXCollections.observableArrayList();
        loadInfo();
    }

    public void backBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ChooseAuthorizationFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    //tạo file fxml rồi nhét cái tên file vào thôi

    public void manageRentBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/RentBooksFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageBookBtnClick(ActionEvent actionEvent) {
//        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChooseAuthorizationFXML.fxml"));
//        stage.setTitle("Phân hệ quản lý");
//        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageLibrarianBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/StaffManagerFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void mangeRegulationBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/UpdateRuleFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageStatisticBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/AdminFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void manageReaderBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReaderFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    private void loadInfo()
    {
        originalData = FXCollections.observableArrayList(LibraryBUS.getGroupBookList(false));
        filterList.addAll(originalData);
        bindingList.addAll(originalData);
        setupTable();
        setupTextFilter();
        table.setItems(bindingList);
    }

    private void setupTable()
    {
        id.setCellValueFactory(new PropertyValueFactory<>("idGroupBook"));
        name.setCellValueFactory(new PropertyValueFactory<>("nameBook"));

        amount.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        type.setCellValueFactory(new PropertyValueFactory<>("typeBook"));
        type.setCellFactory(col -> new TableCell<GroupBook, TypeBook>(){
            @Override
            protected void updateItem(TypeBook item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : item.getNameType());
            }
        });
        status.setCellFactory(col -> new TableCell<GroupBook, Integer>(){
            @Override
            protected  void updateItem(Integer item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty ? null : LibraryBUS.getGroupBookStateName(item));
            }
        });
        author.setCellValueFactory(new PropertyValueFactory<>("author"));

        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        status.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));

    }

    @FXML
    private void addBook() throws IOException
    {
        AddGroupBookDialogWindow.display();
        originalData = FXCollections.observableArrayList(LibraryBUS.getGroupBookList(true));

        reset();
        table.refresh();
    }

    @FXML
    private void viewDetailGroupBook() throws IOException
    {
        if(table.getSelectionModel().isEmpty())
            return;

        GroupBookDetailDialogController.setGroupBookSelected((GroupBook)table.getSelectionModel().getSelectedItem());
        GroupBookDetailDialogWindow.display();
    }

    @FXML
    private void updateDetailGroupBook() throws IOException
    {
        if(table.getSelectionModel().isEmpty())
            return;

        UpdateGroupBookDialogController.setGroupBookSelected((GroupBook)table.getSelectionModel().getSelectedItem());
        UpdateGroupBookDialogWindow.display();
        originalData = FXCollections.observableArrayList(LibraryBUS.getGroupBookList(true)); //edit

        reset();
        table.refresh();
    }

    @FXML
    private void deleteGroupBook(ActionEvent e) throws IOException
    {
        if(table.getSelectionModel().isEmpty())
            return;
        GroupBook itemSelected = (GroupBook)(table.getSelectionModel().getSelectedItem());
        BooksController.setGroupBookId(itemSelected.getIdGroupBook());
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/BooksFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    @FXML
    private void comboBoxSlected()
    {
        filterText.setText("");
        if(displayMode.getSelectionModel().isEmpty())
            return;

        if(displayMode.getSelectionModel().getSelectedIndex() == 0)
        {
            filterList.clear();
            filterList.addAll(originalData);
        }
        else if(displayMode.getSelectionModel().getSelectedIndex() == 1)
        {
            filterList.clear();
            for(GroupBook item : originalData)
                if(item.getIsAvailable() == 1 || item.getIsAvailable() == 3)
                    filterList.add(item);
        }
        else if(displayMode.getSelectionModel().getSelectedIndex() == 2)
        {
            filterList.clear();
            for(GroupBook item : originalData)
                if(item.getIsAvailable() == 4)
                    filterList.add(item);
        }

        bindingList.clear();
        bindingList.addAll(filterList);
        table.refresh();
    }

    void setupTextFilter()
    {
        filterText.textProperty().addListener((v, oldValue, newValue) -> {

            if(newValue.isEmpty() || newValue.compareTo("") == 0)
            {
                bindingList.clear();
                bindingList.addAll(filterList);
                table.refresh();
                return;
            }

            ObservableList<GroupBook> cache = FXCollections.observableArrayList();
            String filterContent = newValue.toLowerCase();

            for(GroupBook item : filterList)
            {
                String id = Integer.toString(item.getIdGroupBook());
                String amount = Integer.toString(item.getQuantity());
                String status = LibraryBUS.getGroupBookStateName(item.getIsAvailable());

                if(id.indexOf(filterContent)!= -1 || item.getNameBook().toLowerCase().indexOf(filterContent) != -1
                || item.getTypeBook().getNameType().toLowerCase().indexOf(filterContent) != -1
                || amount.indexOf(filterContent) != -1 || item.getAuthor().toLowerCase().indexOf(filterContent) != -1
                || item.getPublisher().toLowerCase().indexOf(filterContent) != -1
                || status.toLowerCase().indexOf(filterContent) != -1)
                    cache.add(item);
            }
            bindingList.clear();
            bindingList.addAll(cache);
            table.refresh();
        });
    }

    @FXML
    private void printBtnClick()
    {
        LibraryBUS.printGroupBook(bindingList);
    }

    private void reset()
    {

        bindingList.clear();
        filterList.clear();
        filterText.setText("");

        if(displayMode.getSelectionModel().getSelectedIndex() == 0)
        {
            filterList.addAll(originalData);
            bindingList.addAll(originalData);
            table.setItems(bindingList);
            table.refresh();
        }
        else
            displayMode.getSelectionModel().select(0);

    }

    public void manangeCompensateBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/CompensateFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }

    public void mangeReportBtnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminFXML/ReportBookFXML.fxml"));
        stage.setTitle("Phân hệ quản lý");
        stage.setScene(new Scene(root, 1000, 600));
    }
}


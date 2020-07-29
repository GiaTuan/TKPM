package sample.Controller;

import javafx.fxml.Initializable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.Model.GroupBook;
import sample.SessionUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        GroupBook groupBook = session.get(GroupBook.class,2);
        transaction.commit();
        session.close();

    }
}

package sample.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sample.POJO.GroupBook;
import sample.POJO.Reader;
import sample.POJO.TypeBook;
import sample.SessionUtil;

import java.util.List;

public class LibraryDAO {

    private static List<TypeBook> typeBookList;
    private static List<GroupBook> groupBookList;
    private static List<Reader> readerList;

    public static void setUpData() {
        setUpTypeBookList();
        setUpReaderList();
    }

    private static void setUpTypeBookList()
    {
        Session session = SessionUtil.getSession();
        try {
            String hql = "select t from TypeBook t";
            Query query = session.createQuery(hql);
            typeBookList = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<TypeBook> getTypeBookList(boolean isRequery) {
        if(isRequery)
        {
            setUpTypeBookList();
            return  typeBookList;
        }
        else return typeBookList;
    }

    private static void setUpReaderList()
    {
        Session session = SessionUtil.getSession();
        try {
            String hql = "select r from Reader r";
            Query query = session.createQuery(hql);
            readerList = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static List<Reader> getReaderList() {
        return readerList;
    }

    public static int getNumberOfBooksByIdTypeBook(int id) {
        Session session = SessionUtil.getSession();
        int totalNumberOfBooks = 0;
        try {
            String hql = "select sum(b.quantity) from GroupBook b where b.idTypeBook = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            totalNumberOfBooks = Integer.parseInt(query.getResultList().get(0).toString());
        } catch (HibernateException ex) {
            ex.printStackTrace();;
        } finally {
            session.close();
            return totalNumberOfBooks;

        }
    }
}

package sample.DAO;

import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sample.POJO.GroupBook;
import sample.POJO.Reader;
import sample.POJO.Regulation;
import sample.POJO.RentBook;
import sample.POJO.TypeBook;
import sample.SessionUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    private static List<TypeBook> typeBookList;
    private static List<GroupBook> groupBookList;
    private static List<Reader> readerList;
    private static List<Regulation> regulationList;
    private static List<RentBook> rentBookList;

    public static void setUpData() {
        setUpTypeBookList();
        setUpReaderList();
        setUpRentBookList();
	    setupRegulationList();
    }

    private static void setUpRentBookList() {
        Session session = SessionUtil.getSession();
        try {
            String hql = "select r from RentBook r";
            Query query = session.createQuery(hql);
            rentBookList = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<RentBook> getRentBookList(boolean isRequery) {
        if(isRequery)
        {
            setUpRentBookList();
            return rentBookList;
        }
        else return rentBookList;
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

    public static List<Reader> getReaderList(boolean isRequery) {

        if(isRequery){
            setUpReaderList();
            return readerList;
        }
        else return readerList;
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

    private static void setupRegulationList() // khong co trong doc4
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "select t from Regulation t";
            Query query = session.createQuery(hql);
            regulationList = query.getResultList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Regulation> getRegulationList(boolean isRequery) // khong co trong doc4
    {
        if(isRequery)
        {
            setupRegulationList();
            return  regulationList;
        }
        else return regulationList;
    }

    public static void updateRegulation(ObservableList<Regulation> newRegulationData, ArrayList<Integer> listIdOfChangingRule)
    {
        for(int item : listIdOfChangingRule)
        {
            for(int i = 0; i < newRegulationData.size(); i++)
            {
                if(newRegulationData.get(i).getId() == item)
                {
                    Session session = SessionUtil.getSession();
                    Transaction transaction = session.beginTransaction();
                    try {
                        Regulation newRegulation = session.get(Regulation.class, item);
                        newRegulation.setName(newRegulationData.get(i).getName());
                        newRegulation.setDetail((newRegulationData.get(i).getDetail()));
                        transaction.commit();
                    } catch (HibernateException ex) {
                        ex.printStackTrace();
                    } finally {
                        session.close();
                    }
                }
            }
        }
    }

    public static boolean updateIsReturnedRentBook(int idRentBook, boolean isReturned) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            RentBook rentBook = session.get(RentBook.class,idRentBook);
            if(isReturned)
            {
                rentBook.setStateRent(1);
            }
            else
            {
                rentBook.setStateRent(0);
            }
            session.update(rentBook);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            res = false;
        } finally {
            session.close();
            return res;
        }
    }

    public static boolean updateReader(int idReader, String nameReader, String addReader, String phoneReader, String emailReader, Date dob, Date memDate, boolean isMarked, boolean isDeleted) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            Reader reader = session.get(Reader.class,idReader);

            reader.setNameReader(nameReader);
            reader.setAddressReader(addReader);
            reader.setPhoneReader(phoneReader);
            reader.setEmailReader(emailReader);
            reader.setDateOfBirth(dob);
            reader.setDateMember(memDate);
            if(isMarked)
            {
                reader.setIsMarked(1);
            }
            if(isDeleted)
            {
                reader.setIsDeleted(1);
            }
            session.update(reader);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            res = false;
        } finally {
            session.close();
            return res;
        }
    }



}

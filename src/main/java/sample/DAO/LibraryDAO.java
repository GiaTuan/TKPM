package sample.DAO;

import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sample.BUS.LibraryBUS;
import sample.POJO.*;
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
    private static List<Staff> staffList = null;
    private static List<Publisher> publisherList = null;

    public static void setUpData() {
        setUpTypeBookList();
        setUpReaderList();
        setUpRentBookList();
        setupRegulationList();
        setupStaffList();
        setupPublisherList();
        setupGroupBookList();
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

    private static void setupStaffList() // khong co trong doc4
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.getTransaction();
        try {
            String hql = "select t from Staff t";
            Query query = session.createQuery(hql);
            staffList = query.getResultList();
            // transaction.commit();
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

    public static List<Staff> getStaffList(boolean isRequery) // khong co trong doc4
    {
        if(isRequery)
        {
            setupRegulationList();
            return  staffList;
        }
        else return staffList;
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
            else
            {
                reader.setIsMarked(0);
            }
            if(isDeleted)
            {
                reader.setIsDeleted(1);
            }
            else
            {
                reader.setIsDeleted(0);
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



    public static boolean isEmailDuplicate(String emailChecked)
    {
        for(Staff item : staffList)
            if(item.getEmailStaff().compareTo(emailChecked) == 0)
                return true;

        return false;
    }
    public static boolean isUsernameDuplicate(String usernameChecked)
    {
        for(Staff item : staffList)
            if(item.getUsername().compareTo(usernameChecked) == 0)
                return true;

        return false;
    }

    public static boolean isPhoneDuplicate(String phoneCheck)
    {
        for(Staff item : staffList)
            if(item.getPhoneStaff().compareTo(phoneCheck) == 0)
                return true;

        return false;
    }

    public static boolean checkVerifyPassword(String password, int currentAcountId)
    {
        return true;
    }

    public static void addStaff(Staff newStaff)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(newStaff);
            transaction.commit();
            setupStaffList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static int getNumberOfBooksRemainByIdGroupBook(int idGroupBook) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        int result = 0;
        try {
            String hql = "select count(b) from Books b where b.idGroupBook = :id and b.state = 0";
            Query query = session.createQuery(hql);
            query.setParameter("id",idGroupBook);
            result = Integer.valueOf(query.getResultList().get(0).toString());
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return result;
        }
    }

    public static Reader getReaderFromPhone(String readerPhone) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Reader result = null;
        try {
            String hql = "select r from Reader r where r.phoneReader = :phone";
            Query query = session.createQuery(hql);
            query.setParameter("phone",readerPhone);
            result = (Reader) query.getResultList().get(0);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return result;
        }
    }

    public static boolean markReader(int idReader) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            Reader reader = session.get(Reader.class,idReader);
            reader.setIsMarked(1);
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

    public static void updateMarkedReaderList(int idReader) {
        for(Reader reader : readerList)
        {
            if(reader.getIdReader() == idReader) {
                reader.setIsMarked(1);
                break;
            }
        }
    }

    public static boolean updateInfoReader(int idReader, String name, String phone, String mail, String addr, Date dob) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            Reader reader = session.get(Reader.class,idReader);

            reader.setNameReader(name);
            reader.setDateOfBirth(dob);
            reader.setAddressReader(addr);
            reader.setPhoneReader(phone);
            reader.setEmailReader(mail);
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

    public static void updateInfoReaderInReaderList(int idReader, String name, String phone, String mail, String addr, Date dob) {
        for(Reader reader : readerList)
        {
            if(reader.getIdReader() == idReader){
                reader.setNameReader(name);
                reader.setPhoneReader(phone);
                reader.setEmailReader(mail);
                reader.setAddressReader(addr);
                reader.setDateOfBirth(dob);
                break;
            }
        }
    }

    public static boolean checkPhoneReader(String phone) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            String hql = "select r from Reader r where r.phoneReader = :phone";
            Query query = session.createQuery(hql);
            query.setParameter("phone",phone);
            List list = query.getResultList();
            if(list.size() != 0)
            {
                res = false;
            }
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            res = false;
        } finally {
            session.close();
            return res;
        }
    }

    public static boolean checkEmailReader(String mail) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            String hql = "select r from Reader r where r.emailReader = :mail";
            Query query = session.createQuery(hql);
            query.setParameter("mail",mail);
            List list = query.getResultList();
            if(list.size() != 0)
            {
                res = false;
            }
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            res = false;
        } finally {
            session.close();
            return res;
        }
    }

    public static boolean addReader(String name, String phone, String mail, String add, Date dob) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        boolean res = true;
        try {
            Reader reader = new Reader(name,phone,mail,add,dob);
            int id = (int) session.save(reader);
            reader.setIdReader(id);
            reader.setIsReceivedNofication(0);
            readerList.add(reader);
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            res = false;
        } finally {
            session.close();
            return res;
        }
    }


    private static void setupGroupBookList()
    {
        Session session = SessionUtil.getSession();
        try {
            String hql = "select t from GroupBook t";
            Query query = session.createQuery(hql);
            groupBookList = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<GroupBook> getGroupBookList(boolean isReQuery)
    {
        if(isReQuery)
            setupGroupBookList();

        return groupBookList;
    }

    private static void setupPublisherList()
    {
        Session session = SessionUtil.getSession();
        try {
            String hql = "select t from Publisher t";
            Query query = session.createQuery(hql);
            publisherList = query.getResultList();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static List<Publisher> getPublisherList(boolean isReQuery)
    {
        if(isReQuery)
            setupPublisherList();

        return publisherList;
    }

    public static void addGroupBook(GroupBook newGroupBook)
    {

        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            int amount = newGroupBook.getQuantity();
            int idGroupBook = (int)session.save(newGroupBook);
            for(int i = 0; i < amount; i++)
            {
                Books book = new Books();
                book.setIdGroupBook(idGroupBook);
                book.setIdBook(LibraryBUS.buildIdBook(idGroupBook, i + 1));
                book.setState(newGroupBook.getIsAvailable() == 0 ? "Chưa nhập" : "Sẵn sàng");
                session.save(book);
            }

            transaction.commit();
            setupGroupBookList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static void updateGroupBookDetail(GroupBook tempObject)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            GroupBook updateGroupBook = session.get(GroupBook.class, tempObject.getIdGroupBook());
            updateGroupBook.setNameBook(tempObject.getNameBook());

            if(tempObject.getIdTypeBook() != 0)
                updateGroupBook.setIdTypeBook(tempObject.getIdTypeBook());

            updateGroupBook.setAuthor(tempObject.getAuthor());

            if(tempObject.getIdPublisher() != 0)
                updateGroupBook.setIdPublisher(tempObject.getIdPublisher());

            if(tempObject.getIsAvailable() != -1)
                updateGroupBook.setIsAvailable(tempObject.getIsAvailable());

            transaction.commit();
            setupGroupBookList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Books> getBookList(int groupBookId)
    {
        Session session = SessionUtil.getSession();
        try {
            String hql = "select t from Books t where t.idGroupBook = :idGroup";
            Query query = session.createQuery(hql);
            query.setParameter("idGroup", groupBookId);

            List<Books> resultList = query.getResultList();
            return resultList;

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public static void deleteBook(int id, int numberOfAvailabe)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Books updateBook = session.get(Books.class, id);
            updateBook.setState("Xóa");

            if(numberOfAvailabe < 1)
            {
                GroupBook updateGroup = session.get(GroupBook.class, updateBook.getIdGroupBook());
                updateGroup.setIsAvailable(2);
            }
            transaction.commit();
            setupGroupBookList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteALLBookInGroup(List<Books> listBooksDelete, int numberOfAvailabe)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {

            for(Books item : listBooksDelete)
            {
                Books updateBook = session.get(Books.class, item.getId());
                updateBook.setState("Xóa");

            }
            if(numberOfAvailabe < 1)
            {
                GroupBook updateGroup = session.get(GroupBook.class, listBooksDelete.get(0).getIdGroupBook());
                updateGroup.setIsAvailable(2);

            }
            transaction.commit();
            setupGroupBookList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static RentBook getRentBookById(int id) {
        Session session = SessionUtil.getSession();
        RentBook result = null;
        try {
            result = session.get(RentBook.class,id);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return result;
        }
    }

    public static void updatePointReader(int idReader, int point) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Reader reader = null;
        try {
            reader = session.get(Reader.class,idReader);
            reader.setPoint(point);
            session.save(reader);
            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateReturnRentBook(int idRentBook, Date returnDay, double rentFee) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        RentBook rentBook = null;
        try {
            rentBook = session.get(RentBook.class,idRentBook);
            rentBook.setReturnDate(returnDay);
            rentBook.setRentFee(rentFee);
            rentBook.setStateRent(1);
            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();}}

    public static boolean isGroupBookIdValid(String idBook)
    {
        Session session = SessionUtil.getSession();
        //Transaction transaction = session.beginTransaction();
        try {
            String hql = "select t from Books t where t.idBook = :idBook";
            Query query = session.createQuery(hql);
            query.setParameter("idBook", idBook);

            List<Books> book = query.getResultList();

            if(book.size() != 0)
                return true;

            return false;

        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static void checkAvailableStatus(int groupBookId) throws HibernateException
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();


        String hql = "select t from Books t where t.idGroupBook = :idGroup AND t.state = :status";
        Query query = session.createQuery(hql);
        query.setParameter("idGroup", groupBookId);
        query.setParameter("status", "Sẵn sàng");

        List<Books> resultList = query.getResultList();
        if(resultList.size() == 0)
        {
            GroupBook groupBook = session.get(GroupBook.class, groupBookId);
            groupBook.setIsAvailable(3);
            session.save(groupBook);
        }
        transaction.commit();
        setupGroupBookList();
    }
    public static void addRentBookRecord(RentBook rentBookrecord, ArrayList<String> listRentBookId)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        int groupBookId = Integer.parseInt(listRentBookId.get(0).split("_")[0]);
        try {

            for(String item : listRentBookId)
            {
                String hql = "select t from Books t where t.idBook = :id";

                Query query = session.createQuery(hql);
                query.setParameter("id", item);
                List<Books> rentBook = query.getResultList();

                rentBook.get(0).setState("Đang mượn");
                session.save(rentBook.get(0));
            }

            session.save(rentBookrecord);

            transaction.commit();

            checkAvailableStatus(groupBookId);
            setUpRentBookList();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean extendCard(Bill bill, Reader reader)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            Reader updateReader = session.get(Reader.class, reader.getIdReader());

            updateReader.setDateMember(reader.getDateMember());
            session.save(updateReader);
            session.save(bill);

            transaction.commit();
            setUpReaderList();

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean noficationResgister(int idReader)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            Reader updateReader = session.get(Reader.class, idReader);

            updateReader.setIsReceivedNofication(1);
            session.save(updateReader);

            transaction.commit();
            setUpReaderList();

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static GroupBook getGroupBookFromId(int groupBookId) {
        Session session = SessionUtil.getSession();

        GroupBook result = null;
        try {
            String hql = "select r from GroupBook r where r.idGroupBook = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", groupBookId);
            result = (GroupBook) query.getResultList().get(0);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return result;
        }
    }

    public static boolean isReaderEnrollQueueBook(int idReader, int idGroupBook)
    {
        Session session = SessionUtil.getSession();
        List<QueueRentBook> result = null;
        try {
            String hql = "select r from QueueRentBook r where r.idGroupBook = :idGroupBook and r.idReader = :idReader";
            Query query = session.createQuery(hql);

            query.setParameter("idGroupBook", idGroupBook);
            query.setParameter("idReader", idReader);

            result = query.getResultList();

            return result.size() != 0;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            session.close();

        }
    }

    public static boolean queueRentBookRegister(QueueRentBook record)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        GroupBook result = null;
        try {
            session.save(record);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    public static boolean checkIsValidBookId(int bookId) {
        Session session = SessionUtil.getSession();
        boolean result = true;
        try {
            Books books = session.get(Books.class,bookId);
            if(books == null)
            {
                result = false;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return result;
        }
    }

    public static void addCompensate(int bookId, int idReader, double fee) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Compensate compensate = new Compensate(bookId,idReader,fee);
            session.save(compensate);
            tx.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static GroupBook getGroupBookFromId(int idGroupBook) {
        Session session = SessionUtil.getSession();
        GroupBook groupBook = null;
        try {
            groupBook = session.get(GroupBook.class,idGroupBook);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return groupBook;
        }
    }

    public static Books getBooksFromId(String s) {
        Session session = SessionUtil.getSession();
        Books book = null;
        try {
            String hql = "select b from Books b where b.idBook = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id",s);
            book = (Books) query.getResultList().get(0);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
            return book;
        }
    }

}

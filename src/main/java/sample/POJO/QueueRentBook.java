package sample.POJO;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "queuerentbook")
public class QueueRentBook {

    @Id
    @Column(name = "idqueuerentbook")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQueueRentBook;

    @Column(name = "idgroupbook")
    private int idGroupBook;

    @Column(name = "idreader")
    private int idReader;

    @Column(name = "rentdate")
    private Date rentDate;

    @Column(name = "isdeleted")
    private int isDeleted;

    @Column(name = "point")
    private double point;

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getIdQueueRentBook() {
        return idQueueRentBook;
    }

    public void setIdQueueRentBook(int idQueueRentBook) {
        this.idQueueRentBook = idQueueRentBook;
    }

    public int getIdGroupBook() {
        return idGroupBook;
    }

    public void setIdGroupBook(int idGroupBook) {
        this.idGroupBook = idGroupBook;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}

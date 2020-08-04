package sample.POJO;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "groupbook")
public class GroupBook {
    @Id
    @Column(name = "idgroupbook")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int idGroupBook;

    @Column(name = "namebook")
    String nameBook;

    @Column(name = "typebook")
    int idTypeBook;

    @Column(name = "authorbook")
    String author;

    @Column(name = "publishdate")
    Date publishDate;

    @Column(name = "importdate")
    Date importDate;

    @Column(name = "publisher")
    int idPublisher;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "isavailable")
    Integer isAvailable;

    @ManyToOne
    @JoinColumn(name = "typebook",insertable = false,updatable = false)
    TypeBook typeBook;

    @ManyToOne
    @JoinColumn(name = "publisher", insertable = false , updatable = false)
    Publisher publisher;

    @OneToMany(mappedBy = "groupBook", fetch = FetchType.EAGER)
    List<Books> booksList;

    public void addBookList(Books book)
    {
        booksList.add(book);
        book.setGroupBook(this);
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public TypeBook getTypeBook() {
        return typeBook;
    }

    public void setTypeBook(TypeBook typeBook) {
        this.typeBook = typeBook;
    }

    public int getIdGroupBook() {
        return idGroupBook;
    }

    public void setIdGroupBook(int idGroupBook) {
        this.idGroupBook = idGroupBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getIdTypeBook() {
        return idTypeBook;
    }

    public void setIdTypeBook(int idTypeBook) {
        this.idTypeBook = idTypeBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getPublisher() {
        return publisher.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }
    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }


}

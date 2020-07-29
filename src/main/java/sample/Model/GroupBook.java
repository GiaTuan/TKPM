package sample.Model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "groupbook")
public class GroupBook {
    @Id
    @Column(name = "idgroupbook", columnDefinition = "serial")
    @Generated(GenerationTime.INSERT)
    int idGroupBook;

    @Column(name = "namebook")
    String nameBook;

    @Column(name = "typebook")
    int typeBook;

    @Column(name = "authorbook")
    String author;

    @Column(name = "publishdate")
    Date publishDate;

    @Column(name = "importdate")
    Date importDate;

    @Column(name = "publisher")
    int publisher;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "isavailable")
    Integer isAvailable;

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

    public int getTypeBook() {
        return typeBook;
    }

    public void setTypeBook(int typeBook) {
        this.typeBook = typeBook;
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

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
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
}

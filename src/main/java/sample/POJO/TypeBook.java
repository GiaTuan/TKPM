package sample.POJO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "typebook")
public class TypeBook {
    @Id
    @Column(name = "idtypebook")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTypeBook;

    @Column(name = "nametypebook")
    String nameType;

    @OneToMany(mappedBy = "typeBook",fetch = FetchType.EAGER)
    List<GroupBook> groupBookList;

    public void addGroupBookList(GroupBook groupBook)
    {
        groupBookList.add(groupBook);
        groupBook.setTypeBook(this);
    }

    public int getIdtypebook() {
        return idTypeBook;
    }

    public void setIdtypebook(int idTypeBook) {
        this.idTypeBook = idTypeBook;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public List<GroupBook> getGroupBookList() {
        return groupBookList;
    }
}

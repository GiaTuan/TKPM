package sample.POJO;
import org.apache.poi.ss.formula.eval.UnaryMinusEval;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "idgroupbook")
    private int idGroupBook;

    @Column(name = "idbook")
    private String idBook;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "idgroupbook",insertable = false,updatable = false)
    GroupBook groupBook;


    public GroupBook getGroupBook()
    {
        return this.groupBook;
    }

    public void setGroupBook(GroupBook groupBook)
    {
        this.groupBook = groupBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGroupBook() {
        return idGroupBook;
    }

    public void setIdGroupBook(int idGroupBook) {
        this.idGroupBook = idGroupBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package sample.POJO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @Column(name = "idpublisher")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPublisher;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
    List<GroupBook> groupBookList;

    public void addGroupBookList(GroupBook groupBook)
    {
        groupBookList.add(groupBook);
        groupBook.setPublisher(this);
    }

    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}

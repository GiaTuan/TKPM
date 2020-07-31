package sample.POJO;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regulation")
public class Regulation {
    @Id
    @Column(name = "idregulation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private int detail;

    public Regulation(){};

    public Regulation(int id, String name, int detail)
    {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }

    public int getIdRegulation()
    {
        return this.id;
    }

    public String getNameRegulation()
    {
        return this.name;
    }

    public int getDetailInfoRegulation()
    {
        return this.detail;
    }
}

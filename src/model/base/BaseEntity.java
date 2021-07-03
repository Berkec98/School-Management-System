package model.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
//public abstract class BaseEntity  implements Comparable<BaseEntity>, Serializable   BUNU İncele kanka
public class BaseEntity implements Serializable {
    @Transient
    //Transient annotasyon sayesinde Entity sınıfında bulunan bir field'in veritabanında sütun karşılığı olmayacağı anlamına gelmektedir. Yani bu field persistent olmayacaktır.
    public static final long serialVersionUID = -4414630373383907195L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    public BaseEntity() {
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

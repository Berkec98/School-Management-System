package model.user;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Groups extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = -3722484717998238606L;
    @Column(length = 20,unique = true, nullable = false)
    private String grupName;

    public Groups() {
    }

    public Groups(String grupName) {
        this.grupName = grupName;
    }

    public String getGrupName() {
        return grupName;
    }

    public void setGrupName(String grupName) {
        this.grupName = grupName;
    }

    @Override
    public String toString() {
        return  grupName ;
    }
}

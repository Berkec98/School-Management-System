package model;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Faculty extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = 5570002690650927427L;
    private String name;

    //Constructor
    public Faculty() {
    }
    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name  ;
    }
}

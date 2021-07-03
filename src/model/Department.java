package model;

import model.base.BaseEntity;
import model.base.EntityInterface;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Department extends BaseEntity implements EntityInterface {

    private String name;
    @ManyToOne
    private Faculty faculty;



    public Department() {
    }

    public void setFaculty(Faculty faculty) {
        this.faculty=faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        if(faculty == null)
            faculty=new Faculty();
        return faculty;
    }



    @Override
    public String toString() {
        return name;
    }
}

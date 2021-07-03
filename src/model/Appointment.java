package model;

import model.base.BaseEntity;
import model.base.EntityInterface;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Appointment extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = -5719395667668707371L;

    @ManyToOne
    private Available available;

    @ManyToOne
    private Student student;




    public Appointment() {
    }

    public Appointment(Available available, Student student) {
        this.available = available;
        this.student = student;
    }

    public Available getAvailable() {
        if(available==null)
            available = new Available();
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public Student getStudent() {
        if(student==null)
            student=new Student();
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", available=" + available +
                ", student=" + student +
                '}';
    }
}

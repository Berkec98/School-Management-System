package model;

import model.base.BaseEntity;
import model.base.EntityInterface;
import utility.MyDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Available extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = -5719395667668707371L;

    @ManyToOne
    private Professor professor;
    @ManyToOne
    private Clazz aClassR;
    private long availableDate;
    private String availableTime;



    public Available() {
    }

    public Available(Professor professor, Clazz aClassR, long availableDate, String availableTime) {
        this.professor = professor;
        this.aClassR = aClassR;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
    }

    public Professor getProfessor() {
        if(professor==null)
            professor=new Professor();
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Clazz getaClassR() {
        if(aClassR==null)
            aClassR=new Clazz();
        return aClassR;
    }

    public void setaClassR(Clazz aClassR) {
        this.aClassR = aClassR;
    }

    public long getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(long availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }


    @Override
    public String toString() {
        return  professor + " - "
                +aClassR + " - "
                + new MyDate(availableDate).getMyDateAsString("dd/MM/yyyy")
                + " - " + availableTime ;
    }

}

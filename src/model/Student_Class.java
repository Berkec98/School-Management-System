package model;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Student_Class extends BaseEntity implements EntityInterface {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Clazz clazz;
    private String term;
    private String year;

    public Student_Class() {
    }

    public Student_Class(Student student, Clazz clazz, String term, String year) {
        this.student = student;
        this.clazz = clazz;
        this.term = term;
        this.year = year;
    }

    public Student getStudent() {
        if (student == null)
            student = new Student();
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Clazz getClazz() {
        if(clazz == null)
            clazz = new Clazz();
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Student_Class{" +
                "student=" + student +
                ", class=" + clazz +
                ", term='" + term + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}

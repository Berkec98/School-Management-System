package model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Student extends Person{


    @Transient
    private static final long serialVersionUID = -8605796509915245525L;

    private String schoolNumber;


    public Student() {
    }


    public Student(Person person, String schoolNumber,Department d) {
        super(person.getTcIdentity(), person.getName(), person.getSurname(), person.getPhoneNumber(), person.getEmail(), person.getDtarihi(),d);
        this.schoolNumber = schoolNumber;
    }



    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    @Override
    public String toString() {
        return  getName()+" "+getSurname();
    }
}

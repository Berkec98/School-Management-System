package model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Professor extends Person{

    @Transient
    private static final long serialVersionUID = -7050012074403777512L;

    private String profession;
    private double salary;

    public Professor() {
    }

    public Professor(Person person, String profession, double salary,Department d) {
        super(person.getTcIdentity(), person.getName(), person.getSurname(), person.getPhoneNumber(), person.getEmail(), person.getDtarihi(),d);
        this.profession = profession;
        this.salary = salary;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return getName()+" "+getSurname() +" - "+ profession;
    }
}

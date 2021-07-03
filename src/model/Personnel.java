package model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Personnel extends Person {
    @Transient
    private static final long serialVersionUID = -3395571176295341444L;
    private String mission;
    private double salary;

    public Personnel() {
    }

    public Personnel(Person person, Department workDepartment, String mission, double salary) {
        super(person.getTcIdentity(), person.getName(), person.getSurname(), person.getPhoneNumber(), person.getEmail(), person.getDtarihi(),workDepartment);
        this.mission = mission;
        this.salary = salary;
    }


    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return getName()+ " " + getSurname()+" - "+ mission;
    }
}

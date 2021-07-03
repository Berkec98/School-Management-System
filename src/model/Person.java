package model;

import model.base.CommonEntity;
import model.base.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@Entity
public class Person extends CommonEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = 2771585417661581180L;
    private String tcIdentity;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private long dtarihi;
    @ManyToOne
    private Department department;

    //constructur
    public Person() {
    }

    //constructure
    public Person(String tcIdentity, String name, String surname, String phoneNumber, String email, long dtarihi,Department d) {
        this.tcIdentity = tcIdentity;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dtarihi = dtarihi;
        this.department=d;
    }


    public Department getDepartment() {
        if(department==null)
            department = new Department();
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    //get set methods
    public String getTcIdentity() {
        return tcIdentity;
    }
    public void setTcIdentity(String tcIdentity) {
        this.tcIdentity = tcIdentity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getDtarihi() {
        return dtarihi;
    }
    public void setDtarihi(long dtarihi) {
        this.dtarihi = dtarihi;
    }

    @Override
    public String toString() {
        return "entities.Personnel{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", dtarihi=" + dtarihi +
                ", id=" + id +
                '}';
    }


    @Override
    public String toString2() {
        return  "\nPersonal Information" +
                "\n------------------"+
                "\nID\t\t\t: "+id+
                "\nTc Identity\t\t: " + tcIdentity + " " +
                "\nName\t\t\t\t: " + name +
                "\nSurname\t\t\t: " + surname +
                "\nBirth date\t\t: " + dtarihi +
                "\nPhone\t\t: " + phoneNumber +
                "\nEmail\t\t\t: " + email +"\n ";
    }

    public String toString3() {
        return
                "\nName\t: " + name +
                "\nSurname\t: " + surname+
                "\nPhone \t: " + phoneNumber +
                "\nEmail\t: " + email +
                "\nBirthDate\t: " + dtarihi ;
    }
}


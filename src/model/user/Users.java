package model.user;

import model.base.BaseEntity;
import model.base.EntityInterface;
import model.Person;

import javax.persistence.*;


@Entity
public class Users extends BaseEntity implements EntityInterface {
    @Transient
    private static final long serialVersionUID = -8152922109967425974L;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(length = 40)
    private String password;
    @ManyToOne
    private Groups groups;
    @ManyToOne
    private Person aitOldPers;

    public Users() {
    }

    public Users(String userName, String password, Groups groups, Person aitOldPers) {
        this.userName = userName;
        this.password = password;
        this.groups = groups;
        this.aitOldPers = aitOldPers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Groups getGroups() {
        if(groups==null)
            groups=new Groups();
        return groups;
    }

    public Person getAitOldPers() {
        if(aitOldPers==null)
            aitOldPers=new Person();
        return aitOldPers;
    }

    public void setAitOldPers(Person aitOldPers) {
        this.aitOldPers = aitOldPers;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    @Override
    public String toString() {
        return  this.getAitOldPers().getName()+" "+this.getAitOldPers().getSurname()+"("+userName+")" ; //userName + "--->" + groups +"("+this.getAitOldPers().getAdi()+" "+this.getAitOldPers().getSoyadi()+")";
    }

    public String toString3() {
        return
                aitOldPers.toString3() +
                        "\nUserName\t: " + userName +
                        "\nPermission Group\t: " + groups;
    }
}

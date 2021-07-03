package model;

import controller.ClazzController;
import model.base.BaseEntity;
import model.base.EntityInterface;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class Clazz extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = -276997099777153012L;


    @ManyToOne
    Department department;
    private String lessonName;
    private int courseCredit;
    @ManyToOne
    private Professor professor;
    private String day;
    private String hour;
    private String type;
    private String link;



    //Constructor
    public Clazz() {
    }

    public Clazz(Department department, String lessonName, int courseCredit, Professor professor, String day, String hour, String type, String link) {
        this.department = department;
        this.lessonName = lessonName;
        this.courseCredit = courseCredit;
        this.professor = professor;
        this.day = day;
        this.hour = hour;
        this.type = type;
        this.link = link;
    }

    public Department getDepartment() {
        if (department==null)
            department=new Department();
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //get set methods
    public String getLessonName() {
        return lessonName;
    }
    public int getCourseCredit() {
        return courseCredit;
    }
    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Professor getProfessor() {
        if(professor==null)
            professor=new Professor();
        return professor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        if(ClazzController.thisIsClasController){
            if(professor!=null)
                return  lessonName +" (" + courseCredit+")"+ professor.getName()+" "+professor.getSurname()+ " Day: "+day+" "+hour+ "Type: "+type;
            else return  lessonName +" (" + courseCredit+")"+" Day: "+day+" "+hour+"Type: "+type;
        }else return lessonName;

    }
}

package model;

import daolar.DaoRepositoryImp;
import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class StudentNotes extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = -8766095839188519764L;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Clazz sinif;

    @Column(columnDefinition = "integer default 0")
    private double quiz;
    @Column(columnDefinition = "integer default 0")
    private double midterm;
    @Column(columnDefinition = "integer default 0")
    private double finall;
    @Column(columnDefinition = "integer default 0")
    private double integration;
    private String year;
    private String term;
    @Transient
    private double grade;
    @Transient
    private String harfNotu;

    public StudentNotes() {
    }

    public StudentNotes(Student student, Clazz sinif, double quiz, double midterm, double finall, double integration,String year,String term) {
        this.student = student;
        this.sinif = sinif;
        this.quiz = quiz;
        this.midterm = midterm;
        this.finall = finall;
        this.integration = integration;
        this.term = term;
        this.year = year;
    }

    public Student getStudent() {
        if(student == null)
            student = new Student();
        return student;
    }

    public void setSinif(Clazz sinif) {
        this.sinif = sinif;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Clazz getSinif() {
        if(sinif==null)
            sinif=new Clazz();
        return sinif;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public double getGrade() {
        double note=0;
        if(quiz>0)note+=(quiz*0.1);
        if(midterm>0)note+=(midterm*0.35);
        if(integration>0){
            note+=(integration*0.55);
        }
        else if(finall>0)note+=(finall*0.55);
        return grade=note;
    }


    public String getHarfNotu() {
        DaoRepositoryImp<Note> noteDao=new DaoRepositoryImp(new Note().getClass());
        List<Note> lst=noteDao.getAll();
        for(Note note : lst){
            double start=note.getStartGrade();
            double end=note.getEndGrade();
            if(this.grade>=start && this.grade<=end) return note.getLetterGrade();
        }
        return harfNotu;
    }




    public double getQuiz() {
        return quiz;
    }

    public void setQuiz(double quiz) {
        this.quiz = quiz;
    }

    public double getMidterm() {
        return midterm;
    }

    public void setMidterm(double midterm) {
        this.midterm = midterm;
    }

    public double getFinall() {
        return finall;
    }

    public void setFinall(double finall) {
        this.finall = finall;
    }

    public double getIntegration() {
        return integration;
    }

    public void setIntegration(double integration) {
        this.integration = integration;
    }

    @Override
    public String toString() {
        return "StudentNotes{" +
                "id=" + id +
                ", student=" + student +
                ", sinif=" + sinif +
                ", quiz=" + quiz +
                ", midterm=" + midterm +
                ", final=" + finall +
                ", integration=" + integration +
                '}';
    }
}

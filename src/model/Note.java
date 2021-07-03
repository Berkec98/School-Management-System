package model;

import model.base.BaseEntity;
import model.base.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Note extends BaseEntity implements EntityInterface {

    @Transient
    private static final long serialVersionUID = -8766095839188519764L;

    private double startGrade;
    private double endGrade;
    private String letterGrade;



    public Note() {
    }

    public Note(double startGrade, double endGrade, String letterGrade) {
        this.startGrade = startGrade;
        this.endGrade = endGrade;
        this.letterGrade = letterGrade;
    }

    public double getStartGrade() {
        return startGrade;
    }

    public void setStartGrade(double startGrade) {
        this.startGrade = startGrade;
    }

    public double getEndGrade() {
        return endGrade;
    }

    public void setEndGrade(double endGrade) {
        this.endGrade = endGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    @Override
    public String toString() {
        return   startGrade +" - "+ endGrade +" = "+ letterGrade;
    }

}

package controller;

import model.Student;
import utility.error.ErrorMessage;
import utility.MyStagesShower;

public class SelectStudent {

    private static Student activeStudent;


    //null gitmesi özellikle gerekli
    public static Student getActiveStudent() {
        return activeStudent;
    }


    public static  void setActiveStudent(Student activeStudent)  {
        if (activeStudent == null) {ErrorMessage.NO_ACTIVE_STUDENT.printErrorMessages("can not be continued"); return;}
        SelectStudent.activeStudent = activeStudent;
        MyStagesShower.uiBarController.setLblAktif(activeStudent);
    }
}

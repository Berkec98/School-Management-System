package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Clazz;
import model.Department;
import model.Professor;
import model.base.BaseEntity;
import utility.ComboboxDoldur;
import utility.enums.OpType;
import utility.enums.WPATH;
import utility.error.MyErrorHelper;
import utility.listView.List_View;

import java.net.URL;
import java.util.ResourceBundle;


public class ClazzController extends List_View {
    /*** Variables ***/
    @FXML private TextField txtLessonName,txtCredit,txtHour,txtLink;
    @FXML private ComboBox<Professor> cmbProf;
    @FXML private ComboBox<String> cmbDay,cmbType;
    @FXML private ComboBox<Department> cmbDepartment;

    public ClazzController() {
        changeState(WPATH.clazz);
    }

    public static boolean thisIsClasController=true;

    @Override
    public void fromForm() {
        ((Clazz) getEntityInterface()).setLessonName(txtLessonName.getText());
        ((Clazz) getEntityInterface()).setCourseCredit(Integer.parseInt(txtCredit.getText()));
        ((Clazz) getEntityInterface()).setProfessor(cmbProf.getValue());
        ((Clazz) getEntityInterface()).setDay(cmbDay.getValue());
        ((Clazz) getEntityInterface()).setHour(txtHour.getText());
        ((Clazz) getEntityInterface()).setLink(txtLink.getText());
        ((Clazz) getEntityInterface()).setType(cmbType.getValue());
        ((Clazz) getEntityInterface()).setDepartment(cmbDepartment.getValue());
    }

    @Override
    public void toForm() {
        if (entityInterface != null) {
            this.txtLessonName.setText(((Clazz) entityInterface).getLessonName());
            this.txtCredit.setText(String.valueOf(((Clazz) entityInterface).getCourseCredit()));
            this.cmbProf.setValue(((Clazz) entityInterface).getProfessor());
            this.cmbDay.setValue(((Clazz) entityInterface).getDay());
            this.txtHour.setText(((Clazz) entityInterface).getHour());
            this.txtLink.setText(((Clazz) entityInterface).getLink());
            this.cmbType.setValue(((Clazz) entityInterface).getType());
            this.cmbDepartment.setValue(((Clazz) entityInterface).getDepartment());
        }
    }



    @Override
    public void clearFormFields() {
        clearNodes(txtCredit,txtLessonName,txtLink,txtHour,cmbType,cmbDay);
    }


    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (!err.isBosBirakilamaz(txtCredit,txtLessonName,cmbProf,cmbDepartment))
            err.uniqueDegerTekrarGirilemez(getDaoRepositoryImp(), "Lesson Name", "lessonName", txtLessonName.getText(), ((BaseEntity) getEntityInterface()).getId());
        err.boyutFazlaOlamaz(txtLessonName.getText(), "Lesson Name", 30);
        return err.hataVarsaMesajGosterReturnEt();
    }

    private void comboboxDoldur(){
        ComboboxDoldur.comboboxPopulate(cmbDay,"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        ComboboxDoldur.comboboxPopulateFromDao(cmbProf,Professor.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbDepartment,Department.class);
        ComboboxDoldur.comboboxPopulate(cmbType,"Compulsory","Optional");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxDoldur();
        super.initialize(null,null);
        addStrFocusLostListener(new Node[]{txtLessonName});
        addActivateDoOnlyDigitKeyTypedListeners(new Node[]{txtCredit});
        addActivateEnterKeyPressedListenerFor(new Node[]{txtLessonName,txtCredit});
    }
}

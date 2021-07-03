package controller;

import utility.enums.OpType;
import utility.listView.List_View;
import model.base.BaseEntity;
import model.Department;
import model.Faculty;
import utility.error.MyErrorHelper;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utility.ComboboxDoldur;
import utility.MyPredicateCreator;
import utility.enums.WPATH;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class DepartmentController extends List_View {
    /*** Variables ***/
    @FXML
    private TextField txtDepName;
    @FXML
    private ComboBox<Faculty> cmbFaculty;

    public DepartmentController() {
        changeState(WPATH.department);
    }


    @Override
    public void fromForm() {
        ((Department) getEntityInterface()).setName(txtDepName.getText());
        ((Department) getEntityInterface()).setFaculty(cmbFaculty.getValue());
    }

    @Override
    public void toForm() {
        /* özellikle getter kullanmadık null kontrolü yaptık */
        if (entityInterface != null) {
            this.txtDepName.setText(((Department) entityInterface).getName());
            this.cmbFaculty.setValue(((Department) getEntityInterface()).getFaculty());
        }
    }


    private void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbFaculty, Faculty.class);
    }

    @Override
    public void clearFormFields() {
        txtDepName.clear();
    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        err.isBosBirakilamaz(cmbFaculty);
        if (!err.isBosBirakilamaz(txtDepName))
            err.uniqueDegerTekrarGirilemez(getDaoRepositoryImp(), "Department Name", "name", txtDepName.getText(), ((BaseEntity) getEntityInterface()).getId());
        err.boyutFazlaOlamaz(txtDepName.getText(), "Department Name", 50);
        return err.hataVarsaMesajGosterReturnEt();
    }


    @FXML
    public void populateListViewData(){
        if(cmbFaculty.getValue()==null) return;
            ListProperty<Object> listProperty = new SimpleListProperty<>();
            try {
                MyPredicateCreator facultyPredicate = new MyPredicateCreator("faculty",cmbFaculty.getValue());
                List tmpList = getDaoRepositoryImp().getAll(facultyPredicate);
                listView.itemsProperty().bind(listProperty);
                listProperty.set(FXCollections.observableArrayList(tmpList));
            } catch (Exception e) {
                e.printStackTrace();
                //ErrorMessage.CANT_FILL_THE_LIST.printErrorMessages(e.getMessage());
            }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //super.initialize(null,null);
        populateListViewData();
        setButtonsVisibleAccordingToPermission(getActiveModulo().getDescription());

        comboboxlariDoldur();
        addStrFocusLostListener(new Node[]{txtDepName});
        addActivateEnterKeyPressedListenerFor(new Node[]{cmbFaculty,txtDepName});
    }

}

package controller;

import utility.enums.OpType;
import utility.listView.List_View;
import model.base.BaseEntity;
import model.Faculty;
import utility.error.MyErrorHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;


public class FacultyController extends List_View {
    /*********************************** Variables ***********************************************/
    @FXML
    private TextField txtName;



    public FacultyController() {
        changeState(WPATH.faculty);
    }


    @Override
    public void fromForm() {
        ((Faculty) getEntityInterface()).setName(txtName.getText());
    }

    @Override
    public void toForm() {
        /* özellikle getter kullanmadık null kontrolü yaptık */
        if (entityInterface != null) {
            this.txtName.setText(((Faculty) entityInterface).getName());
        }
    }



    @Override
    public void clearFormFields() {
        txtName.clear();
    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (!err.isBosBirakilamaz(txtName))
            err.uniqueDegerTekrarGirilemez(getDaoRepositoryImp(), "Faculty Name", "name", txtName.getText(), ((BaseEntity) getEntityInterface()).getId());
        err.boyutFazlaOlamaz(txtName.getText(), "Faculty Name", 50);
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null,null);
        addStrFocusLostListener(new Node[]{txtName});
        addActivateEnterKeyPressedListenerFor(new Node[]{txtName});
    }
}

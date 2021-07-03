package controller;

import utility.enums.OpType;
import utility.listView.List_View;
import model.base.BaseEntity;
import model.user.Groups;
import utility.error.MyErrorHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;


public class GroupsController extends List_View {
    @FXML
    private TextField txtGroupName;

    public GroupsController() {
        changeState(WPATH.groups);
    }


    @Override
    public void fromForm() {
        ((Groups) getEntityInterface()).setGrupName(txtGroupName.getText());
    }

    @Override
    public void toForm() {
        if (entityInterface != null) {
            this.txtGroupName.setText(((Groups) entityInterface).getGrupName());
        }
    }


    @Override
    public void clearFormFields() {
        txtGroupName.clear();
    }


    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (!err.isBosBirakilamaz(txtGroupName))
            err.uniqueDegerTekrarGirilemez(getDaoRepositoryImp(), "Group Name", "groupName", txtGroupName.getText(), ((BaseEntity) getEntityInterface()).getId());
        err.boyutFazlaOlamaz(txtGroupName.getText(), "Group Name", 20);
        return err.hataVarsaMesajGosterReturnEt();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null, null);
        addStrFocusLostListener(new Node[]{txtGroupName});
    }

}

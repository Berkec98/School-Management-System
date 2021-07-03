package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Department;
import model.Professor;
import model.base.BaseEntity;
import utility.ComboboxDoldur;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import utility.enums.OpType;
import utility.enums.WPATH;
import utility.error.MyErrorHelper;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorController extends Table_View<Professor> {

    @FXML
    private TextField txtSearch,txtTC,txtAd,txtSoyad,txtBirthDate,txtTel,txtEmail,txtProfession,txtSalary;
    @FXML
    private ComboBox<Department> cmbDepartment;


    @FXML
    void catchKeyPress(KeyEvent event) {
        populateTableCells(istenenAlanlariOlustur());
        refreshViewer(null);
    }

    public ProfessorController() { //contructure
        changeState(WPATH.professor);
    }

    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbDepartment,Department.class);
    }

    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Professor());
        DesiredField[] df = dh.buFieldleriOlustur("tcIdentity", "name", "surname", "phoneNumber","email","dtarihi","department","profession","salary");
        dh.fieldBasliklariSunlarOlsun("TC Identification", "Name", "Surname", "Phone Number","E-Mail"," Birth Date","","Profession", "Salary");
        dh.fieldBasliklariSunlarOlsun("TC Identification", "Name", "Surname", "Phone Number","E-Mail"," Birth Date","Department","Profession", "Salary");
        df[5].setDateTimeFormat("dd/MM/yyyy");
        return df;
    }



    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        MyPredicateCreator my = new MyPredicateCreator("name","surname", txtSearch.getText());
        return new MyPredicateCreator[]{my};
    }


    @Override
    protected void fromForm() {
        ((Professor) getEntityInterface()).setTcIdentity(txtTC.getText());
        ((Professor) getEntityInterface()).setName(txtAd.getText());
        ((Professor) getEntityInterface()).setEmail(txtEmail.getText());
        ((Professor) getEntityInterface()).setSurname(txtSoyad.getText());
        ((Professor) getEntityInterface()).setPhoneNumber(txtTel.getText());
        Long birthDate=new MyDate(txtBirthDate.getText(),"dd/MM/yyyy").getMyDateAsLong();
        if(birthDate!=null)
        ((Professor) getEntityInterface()).setDtarihi(birthDate);
        ((Professor) getEntityInterface()).setProfession(txtProfession.getText());
        if(!txtSalary.getText().isEmpty())
        ((Professor) getEntityInterface()).setSalary(Double.parseDouble(txtSalary.getText()));
        ((Professor) getEntityInterface()).setDepartment(cmbDepartment.getValue());
    }


    @Override
    protected void toForm() {
        if (entityInterface != null) {
            this.txtTC.setText(((Professor) getEntityInterface()).getTcIdentity());
            this.txtAd.setText(((Professor) getEntityInterface()).getName());
            this.txtSoyad.setText(((Professor) getEntityInterface()).getSurname());
            this.txtBirthDate.setText(new MyDate(((Professor) getEntityInterface()).getDtarihi()).getMyDateAsString("dd/MM/yyyy"));
            this.txtEmail.setText(((Professor) getEntityInterface()).getEmail());
            this.txtTel.setText(((Professor) getEntityInterface()).getPhoneNumber());
            this.txtProfession.setText(((Professor) getEntityInterface()).getProfession());
            this.txtSalary.setText(String.valueOf(((Professor) getEntityInterface()).getSalary()));
            this.cmbDepartment.setValue(((Professor) getEntityInterface()).getDepartment());
        }
    }


    @Override
    protected void clearFormFields() {
        clearNodes(txtAd, txtBirthDate, txtEmail, txtSearch, txtSoyad, txtTC, txtTel,txtProfession,txtSalary,this.cmbDepartment);
    }


    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();

        err.isBosBirakilamaz(txtAd);
        err.boyutSifirOlurAmaFazlaAzOlamaz(txtTC.getText(), "TC Identity", 11);
        err.boyutFazlaOlamaz(txtAd.getText(), "Name", 30);
        err.boyutFazlaOlamaz(txtSoyad.getText(), "Surname", 30);
        err.boyutSifirOlurAmaFazlaAzOlamaz(txtTel.getText(), "Phone Number", 10);
        if (!txtTC.getText().isEmpty())
            err.uniqueDegerTekrarGirilemez(getDaoRepositoryImp(), "TC Identity", "tcIdentity", txtTC.getText(), ((BaseEntity) getEntityInterface()).getId());

        return err.hataVarsaMesajGosterReturnEt();
    }


    public void keyListenerleriOlustur() {                          //bu fonksiyonu initializede invoke etmek gerekiyor.
        Node[] digitOrder = new Node[]{txtTC, txtTel,txtSalary};
        addActivateDoOnlyDigitKeyTypedListeners(digitOrder);
        Node[] focusOrder = new Node[]{
                txtTC, txtAd, txtSoyad, txtBirthDate, txtTel, txtEmail,txtProfession,txtSalary,this.cmbDepartment};
        addActivateEnterKeyPressedListenerFor(focusOrder);
        Node[] focusLost = new Node[]{
                txtTC, txtAd, txtSoyad, txtBirthDate, txtTel,txtProfession};
        addStrFocusLostListener(focusLost);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setButtonsVisibleAccordingToPermission(getActiveModulo().getDescription());
        populateTableCells(istenenAlanlariOlustur());
        keyListenerleriOlustur();
        comboboxlariDoldur();
    }
}



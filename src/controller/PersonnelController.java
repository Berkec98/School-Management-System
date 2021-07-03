package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Department;
import model.Personnel;
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

public class PersonnelController extends Table_View<Personnel> {

    @FXML private TextField txtSearch,txtTC,txtAd,txtSoyad,txtBirthDate,txtTel,txtEmail,txtMission,txtSalary;
    @FXML private ComboBox<Department> cmbDepartment;



    @FXML
    void catchKeyPress(KeyEvent event) {
        populateTableCells(istenenAlanlariOlustur());
        refreshViewer(null);
    }

    public PersonnelController() { //contructure
        changeState(WPATH.personnel);
    }

    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbDepartment, Department.class);
    }

    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Personnel());
        DesiredField[] df = dh.buFieldleriOlustur("tcIdentity", "name", "surname", "phoneNumber","email","dtarihi","mission","salary","department");
        dh.fieldBasliklariSunlarOlsun("TC Identification", "Name", "Surname", "Phone Number","E-Mail"," Birth Date","Pers. Mission", "Salary","Work Department");
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
        ((Personnel) getEntityInterface()).setTcIdentity(txtTC.getText());
        ((Personnel) getEntityInterface()).setName(txtAd.getText());
        ((Personnel) getEntityInterface()).setEmail(txtEmail.getText());
        ((Personnel) getEntityInterface()).setSurname(txtSoyad.getText());
        ((Personnel) getEntityInterface()).setPhoneNumber(txtTel.getText());
        Long birthDate=new MyDate(txtBirthDate.getText(),"dd/MM/yyyy").getMyDateAsLong();
        if(birthDate!=null)((Personnel) getEntityInterface()).setDtarihi(birthDate);
        ((Personnel) getEntityInterface()).setMission(txtMission.getText());
        ((Personnel) getEntityInterface()).setSalary(Double.parseDouble(txtSalary.getText()));
        ((Personnel) getEntityInterface()).setDepartment(cmbDepartment.getValue());
    }

    @Override
    protected void toForm() {
        if (entityInterface != null) {
            this.txtTC.setText(((Personnel) getEntityInterface()).getTcIdentity());
            this.txtAd.setText(((Personnel) getEntityInterface()).getName());
            this.txtSoyad.setText(((Personnel) getEntityInterface()).getSurname());
            this.txtBirthDate.setText(new MyDate(((Personnel) getEntityInterface()).getDtarihi()).getMyDateAsString("dd/MM/yyyy"));
            this.txtEmail.setText(((Personnel) getEntityInterface()).getEmail());
            this.txtTel.setText(((Personnel) getEntityInterface()).getPhoneNumber());
            this.txtMission.setText(((Personnel) getEntityInterface()).getMission());
            this.txtSalary.setText(String.valueOf(((Personnel) getEntityInterface()).getSalary()));
            this.cmbDepartment.setValue(((Personnel) getEntityInterface()).getDepartment());
        }
    }


    @Override
    protected void clearFormFields() {
        clearNodes(txtAd, txtBirthDate, txtEmail, txtSearch, txtSoyad, txtTC, txtTel,txtMission,txtSalary,cmbDepartment);
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
                txtTC, txtAd, txtSoyad, txtBirthDate, txtTel, txtEmail,cmbDepartment,txtMission,txtSalary};
        addActivateEnterKeyPressedListenerFor(focusOrder);
        Node[] focusLost = new Node[]{
                txtTC, txtAd, txtSoyad, txtBirthDate, txtTel, txtEmail,txtMission};
        addStrFocusLostListener(focusLost);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxlariDoldur();
        setButtonsVisibleAccordingToPermission(getActiveModulo().getDescription());
        populateTableCells(istenenAlanlariOlustur());
        keyListenerleriOlustur();
    }
}



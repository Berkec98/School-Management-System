
package controller;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Department;
import model.Student;
import model.base.BaseEntity;
import utility.ComboboxDoldur;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.MyStagesShower;
import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import utility.enums.CommandTipi;
import utility.enums.OpType;
import utility.enums.WPATH;
import utility.error.MyErrorHelper;
import java.net.URL;
import java.util.ResourceBundle;



public class StudentController extends Table_View<Student> {

    @FXML
    private TextField txtTC,txtId,txtAd,txtSoyad,txtPhone ,txtEMail,txtDTarihi,txtSchoolNumber, txtSearchStudent;
    @FXML
    private Button btnAppointment,btnPrint,btnNotEkle;
    @FXML
    private ComboBox<String> cmbAramaKriteri;
    @FXML
    private ComboBox<Department> cmbDepartment;

    public StudentController() {
        changeState(WPATH.student);
    }   //Contructure


    @FXML
    public void updateEntityFromSelectionModel(MouseEvent event) {
        super.updateEntityFromSelectionModel(event);
        SelectStudent.setActiveStudent((Student) getEntityInterface());
    }

    private void formGostermeSonrasiIslemler() {
        changeState(WPATH.student);
        setEntityInterface(SelectStudent.getActiveStudent());
        clearFormFields();
    }

    @FXML
    void showNotes() {
        new MyStagesShower().showFXML_ActiveGerekiyor(WPATH.studentNotes,false);
        formGostermeSonrasiIslemler();
    }

    @FXML
    void showAppointment() {
        new MyStagesShower().showFXML_ActiveGerekiyor(WPATH.appointment,false);
        formGostermeSonrasiIslemler();
    }


    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Student());
        DesiredField[] df = dh.buFieldleriOlustur("tcIdentity", "name", "surname", "phoneNumber", "dtarihi","schoolNumber","department","email");
        dh.fieldBasliklariSunlarOlsun("TC Identification", "Name" ,"Surname", "Phone Number","Birth Date","School Number","Department","E-Mail");

        df[4].setDateTimeFormat("dd/MM/yyyy");
        return df;
    }


    @FXML
    void yazdir() {

    }





    /** CRUD İŞLEMLERİ */

    @Override
    protected void fromForm() {
        ((Student) getEntityInterface()).setTcIdentity(txtTC.getText());
        ((Student) getEntityInterface()).setName(txtAd.getText());
        ((Student) getEntityInterface()).setSurname(txtSoyad.getText());
        ((Student) getEntityInterface()).setEmail(txtEMail.getText());
        ((Student) getEntityInterface()).setPhoneNumber(txtPhone.getText());
        Long birthDate=new MyDate(txtDTarihi.getText(),"dd/MM/yyyy").getMyDateAsLong();
        if(birthDate!=null)
            ((Student) getEntityInterface()).setDtarihi(birthDate);
        ((Student) getEntityInterface()).setSchoolNumber(txtSchoolNumber.getText());
        ((Student) getEntityInterface()).setDepartment(cmbDepartment.getValue());
    }


    @Override
    protected void toForm() {
        if (entityInterface != null) {
            this.txtTC.setText(((Student) getEntityInterface()).getTcIdentity());
            this.txtAd.setText(((Student) getEntityInterface()).getName());
            this.txtSoyad.setText(((Student) getEntityInterface()).getSurname());
            this.txtDTarihi.setText(new MyDate(((Student) getEntityInterface()).getDtarihi()).getMyDateAsString("dd/MM/yyyy"));
            this.txtEMail.setText(((Student) getEntityInterface()).getEmail());
            this.txtPhone.setText(((Student) getEntityInterface()).getPhoneNumber());
            txtSchoolNumber.setText(((Student) getEntityInterface()).getSchoolNumber());
            this.cmbDepartment.setValue(((Student) getEntityInterface()).getDepartment());
        }
    }



    @Override
    protected void clearFormFields() {
        clearNodes( this.txtId, this.txtTC, this.txtAd, this.txtSoyad, this.txtPhone, this.txtDTarihi, this.txtSchoolNumber, this.txtEMail,cmbDepartment);
    }



    @Override
    public void changeButtonsDisablingStateTo(boolean state) {
        //Süperdeki buton ayarlamaları geçerli olup ek 2 tane buton daha ekleniyor*/
        super.changeButtonsDisablingStateTo(state);
        this.btnAppointment.setDisable(!state);
        this.btnPrint.setDisable(!state);
        this.btnNotEkle.setDisable(!state);
    }


    private void keyListenerleriOlustur() {                          //bu fonksiyonu initializede invoke etmek gerekiyor.
        Node[] digitOrder = new Node[]{txtTC, txtPhone};
        addActivateDoOnlyDigitKeyTypedListeners(digitOrder);
        Node[] focusOrder = new Node[]{
                this.txtTC, this.txtAd, this.txtSoyad, this.txtPhone, this.txtDTarihi, this.txtSchoolNumber, this.txtEMail,this.cmbDepartment};
        addActivateEnterKeyPressedListenerFor(focusOrder);

        Node[] upcaseTrim = new Node[]{txtAd, txtSoyad};
        addStrFocusLostListener(upcaseTrim);
    }




    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        if (isEmptyOrNull(txtSearchStudent)) return null;
        final String aranilanMetin = txtSearchStudent.getText();
        final String cmbKriterValue = cmbAramaKriteri.getSelectionModel().getSelectedItem();
        final String kriter = cmbKriterValue == null ? "Name Surname" : cmbKriterValue;
        MyPredicateCreator[] my = null;

        switch (kriter) {
            case "Name Surname":
                my = new MyPredicateCreator[]{new MyPredicateCreator("name", "surname", aranilanMetin)};
                break;

            case "TC Identification":
                my = new MyPredicateCreator[]{new MyPredicateCreator("tcIdentity", aranilanMetin, CommandTipi.Like)};
                break;
        }

        return my;
    }


    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulate(cmbAramaKriteri, "TC Identification", "Name Surname");
        ComboboxDoldur.comboboxPopulateFromDao(cmbDepartment,Department.class);
    }


    //arama kutusunda her bir tuşa basıldığında bu fonksiyon çalışıp tableviewin içini dolduracak olan fonksiyon
    //datayı veritabanından değilde tablodan alırsa hıza çok katkısı olur
    @FXML
    void filterBySearchBox() {
        populateTableCells(istenenAlanlariOlustur());
    }



    /** HATA KONTROLÜ */
    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();

        err.isBosBirakilamaz(txtAd, txtSoyad);
        err.boyutSifirOlurAmaFazlaAzOlamaz(txtTC.getText(), "TC Identification", 11);
        err.boyutSifirOlurAmaFazlaAzOlamaz(txtPhone.getText(), "Phone Number", 10);
        if (!txtTC.getText().isEmpty())
            err.uniqueDegerTekrarGirilemez(getDaoRepositoryImp(), "TC Identification","tcIdentity",txtTC.getText(),  ((BaseEntity) getEntityInterface()).getId());
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null, null);
        keyListenerleriOlustur();
    }
}

      
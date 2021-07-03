package controller;

import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import model.*;
import utility.enums.OpType;
import utility.error.MyErrorHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import utility.ComboboxDoldur;
import utility.MyPermisions;
import utility.MyPredicateCreator;
import utility.MyStagesShower;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController extends Table_View<Appointment> {
    @FXML private Button btnRegAvailableTimes;
    @FXML private ComboBox<Student> cmbStudent;
    @FXML private ComboBox<Professor> cmbProf;
    @FXML private ComboBox<Available> cmbAvailable;
    @FXML private Text lblAvailableTime;

    @FXML
    void availableSelect(ActionEvent event) {

    }

    @FXML
    void showAvailableTime(ActionEvent event) {
        new MyStagesShower().showFXML_FitHeight(WPATH.available);
    }


    public AppointmentController() {
        changeState(WPATH.appointment);
    }


    @Override
    public void fromForm() {
        ((Appointment) getEntityInterface()).setAvailable(cmbAvailable.getValue());
        ((Appointment) getEntityInterface()).setStudent(cmbStudent.getValue());
    }


    @Override
    public void toForm() {
        if (entityInterface != null) {
            this.cmbStudent.setValue(((Appointment) entityInterface).getStudent());
            this.cmbProf.setValue((((Appointment) entityInterface).getAvailable().getProfessor()));
            this.cmbAvailable.setValue((((Appointment) entityInterface).getAvailable()));
        }
    }


    @Override
    public void clearFormFields() {
        clearNodes(this.cmbAvailable, this.cmbProf, this.cmbStudent);
    }



    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (err.isBosBirakilamaz(cmbStudent,cmbProf,cmbAvailable))
            return err.hataVarsaMesajGosterReturnEt(); //kesin hata. aşağıya gitmeden tedbir amaçlı
        //buSinifaOzguPredicateCalistir(err, opType);
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbStudent, Student.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbProf, Professor.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbAvailable, Available.class);
    }


    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        //NULL gönderirsem ne olur mutlaka bak return null;
        return new MyPredicateCreator[]{};  //herhangi bir kritere göre filtrelenmediğinden sadece returnle iş bitiyor
    }

    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Appointment());
        DesiredField[] df = dh.buFieldleriOlustur("student", "available");
        dh.fieldBasliklariSunlarOlsun("Student", "Appointments given the students");
        return df;
    }


    private void keyListenerleriOlustur() {
        Node[] focusOrder = new Node[]{cmbStudent, cmbProf, cmbAvailable};
        addActivateEnterKeyPressedListenerFor(focusOrder);   //nodeleri Listenere ekler
    }

    private void permissionKontrolEdilecekNodeleriBelirle() {
        if (LoginController.getActiveUser() == null) return;
        MyPermisions mp = new MyPermisions();
        mp.checkPermissionsForThisNodes(
                new Node[]{btnRegAvailableTimes},
                new String[]{WPATH.available.getDescription()}, "r");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbStudent.setValue(SelectStudent.getActiveStudent());
        permissionKontrolEdilecekNodeleriBelirle();
        super.initialize(null, null);
        keyListenerleriOlustur();
    }


}

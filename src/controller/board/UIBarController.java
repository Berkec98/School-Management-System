package controller.board;

import com.jfoenix.controls.JFXButton;
import controller.LoginController;
import controller.SelectStudent;
import model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utility.HelperCloseResource;
import utility.MyPermisions;
import utility.MyStagesShower;
import utility.enums.WPATH;
import java.net.URL;
import java.util.ResourceBundle;


public class UIBarController implements Initializable {
    @FXML
    private JFXButton btnStudent, btnRegisterForACourse, btnRecord, btnAppointment, btnSelectedStuNotes,btnAyarlar, btnLog, btnStudentSec;
    String aktifStudentAd = "", aktifStudentSoyad = "";


    public void setLblAktif(Student aktifKisi) {
        if (aktifKisi != null) {
            this.aktifStudentAd = aktifKisi.getName();
            this.aktifStudentSoyad = aktifKisi.getSurname();
        }
    }


    @FXML
    void openExit(ActionEvent event) {
        HelperCloseResource.helperClosePlatform();
    }

    @FXML
    public void openStudentIdentification() {
        new MyStagesShower().showFXML_Maximized(WPATH.student);
    }

    @FXML
    public void openAppointment() {
        new MyStagesShower().showFXML_ActiveGerekiyor(WPATH.appointment,false);
    }

    @FXML
    public void openRecordIdentification() {
        new MyStagesShower().showFXML_NoResizable(WPATH.boardPersonReg);
    }

    @FXML
    public void openRegisterForACourse() {
        new MyStagesShower().showFXML_ActiveGerekiyor(WPATH.student_class,false);
    }

    @FXML
    public void openSelectedStuNotes() {
        new MyStagesShower().showFXML_ActiveGerekiyor(WPATH.listStudentNotes, false);
    }

    @FXML
    public void openSettings() {
        new MyStagesShower().showFXML_NoResizable(WPATH.setting);
    }

    @FXML
    public void openLog() {
        new MyStagesShower().showFXML_Maximized(WPATH.log);
    }

    @FXML
    public void openSelect() {
        new MyStagesShower().showFXML_FitHeight(WPATH.select);
    }


    /*
      kullanıcının Read izninin olup olmadığını kontrol eder
      Active User null ise; null sisteme giremez eğer girmişse bu SüperAdmin'dir
      Süper admin için perm sınaması yapmıyor herşeye ulaşabiliriz
     */
    private void permissionKontrolEdilecekNodeleriBelirle() {
        if (LoginController.getActiveUser() == null) return;
        MyPermisions mp = new MyPermisions();
        mp.checkPermissionsForThisNodes(
                new Node[]{btnSelectedStuNotes,btnRegisterForACourse,btnStudent,  btnAyarlar, btnAppointment, btnLog, btnRecord, btnStudentSec},
                new String[]{
                        WPATH.listStudentNotes.getDescription(),
                        WPATH.student_class.getDescription(),
                        WPATH.student.getDescription(),
                        WPATH.setting.getDescription(),
                        WPATH.appointment.getDescription(),
                        WPATH.log.getDescription(),
                        WPATH.boardPersonReg.getDescription(),
                        WPATH.studentSec.getDescription()}, "r");
    }

    private void butonHoverAyarla() {

        Text m = new Text();
        m.setTextAlignment(TextAlignment.CENTER);
        final Node graphic = btnSelectedStuNotes.getGraphic();
        btnSelectedStuNotes.setOnMouseEntered(mouseEvent -> {
            if (SelectStudent.getActiveStudent() != null) {
                m.setText(aktifStudentAd + "\n" + aktifStudentSoyad);
                btnSelectedStuNotes.setGraphic(m);
            }
        });
        btnSelectedStuNotes.setOnMouseExited(mouseEvent -> {
            if (SelectStudent.getActiveStudent() != null)
                btnSelectedStuNotes.setGraphic(graphic);
        });


        final Node graphic1 = btnAppointment.getGraphic();
        btnAppointment.setOnMouseEntered(mouseEvent -> {
            if (SelectStudent.getActiveStudent() != null) {
                m.setText(aktifStudentAd + "\n" + aktifStudentSoyad);
                btnAppointment.setGraphic(m);
            }
        });
        btnAppointment.setOnMouseExited(mouseEvent -> {
            if (SelectStudent.getActiveStudent() != null)
                btnAppointment.setGraphic(graphic1);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        permissionKontrolEdilecekNodeleriBelirle();
        butonHoverAyarla();
    }

}
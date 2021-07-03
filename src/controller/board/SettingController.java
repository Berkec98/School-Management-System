package controller.board;


import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import utility.MyPermisions;
import utility.MyStagesShower;
import utility.enums.WPATH;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML    private Button btnFaculty;
    @FXML    private Button btnDepartment;
    @FXML    private Button btnClazz;
    @FXML    private Button btnNote;
    @FXML    private Button btnVeriYedekle;
    @FXML    private Button btnGeriYukle;
    @FXML    private Button btnOtoYedekleme;
    @FXML    private Button btnYedeklemeGecmisi;
    @FXML    private Button btnUpdate;
    @FXML    private Button btnHakkinda;
    @FXML    private Button btnGrupTanimla;
    @FXML    private Button btnKullanicilar;
    @FXML    private Button btnVarsayilanlar;


    @FXML
    private Button btnYetkilendirme;

    @FXML
    void showDialogFaculties() {
        new MyStagesShower().showFXML_FitHeight(WPATH.faculty);
    }
    @FXML
    void showDialogCrudDepartment() {
        new MyStagesShower().showFXML_FitHeight(WPATH.department);
    }
    @FXML
    void showDialogHakkinda() {
        new MyStagesShower().showFXML_FitHeight(WPATH.hakkinda);
    }
    @FXML
    void showDialogCrudUsers() {
        new MyStagesShower().showFXML_NoResizable(WPATH.kullaniciTanimla);
    }
    @FXML
    void showDialogCrudGroups() {
        new MyStagesShower().showFXML_FitHeight(WPATH.groups);
    }
    @FXML
    void showDialogCrudClazz() {
        new MyStagesShower().showFXML_FitHeight(WPATH.clazz);
    }
    @FXML
    void showDialogCrudNote() {
        new MyStagesShower().showFXML_FitHeight(WPATH.note);
    }
    @FXML
    void showDialogPermissions() {
        new MyStagesShower().showFXML_Maximized(WPATH.permissions);
    }
    @FXML
    void showDialogVerileriYedekle() {
        new MyStagesShower().showFXML_FitHeight(WPATH.verileriYedekle);
    }
    @FXML
    void showDialogOtomatikYedekle() {
        new MyStagesShower().showFXML_FitHeight(WPATH.otomatikYedekleme);
    }
    @FXML
    void showDialogYedeklemeGecmisi() {
        new MyStagesShower().showFXML_FitHeight(WPATH.backupHistory);
    }
    @FXML
    void showDialogVerileriGeriYukle() {
        new MyStagesShower().showFXML_FitHeight(WPATH.verileriGeriYukle);
    }
    @FXML
    void showGecmisSistemMesajlari() {
        new MyStagesShower().showFXML_FitHeight(WPATH.gecmisMesajlar);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //süper adminin hepsini görüntülemesine izin veriyoruz yoksa ilk kurulumda tablolar boş olduğundan hiç bir bölümde çalışılamaz
        if (LoginController.getActiveUser() == null) return; //null sisiteme giremez eğer girmişse bu SüperAdmindir

        new MyPermisions().checkPermissionsForThisNodes(
                new Node[]{
                        btnFaculty,
                        btnDepartment,
                        btnClazz,
                        btnNote,
                        btnVeriYedekle,
                        btnGeriYukle,
                        btnYetkilendirme,
                        btnKullanicilar,
                        btnGrupTanimla,
                        btnHakkinda,
                        btnOtoYedekleme,
                        btnYedeklemeGecmisi
                },
                new String[]{
                        WPATH.faculty.getDescription(),
                        WPATH.department.getDescription(),
                        WPATH.clazz.getDescription(),
                        WPATH.note.getDescription(),
                        WPATH.verileriYedekle.getDescription(),
                        WPATH.verileriGeriYukle.getDescription(),
                        WPATH.permissions.getDescription(),
                        WPATH.kullaniciTanimla.getDescription(),
                        WPATH.groups.getDescription(),
                        WPATH.hakkinda.getDescription(),
                        WPATH.otomatikYedekleme.getDescription(),
                        WPATH.backupHistory.getDescription()
                }, "r");
    }
}

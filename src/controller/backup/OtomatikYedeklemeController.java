package controller.backup;

import utility.enums.OpType;
import utility.listView.List_View;
import utility.error.MyErrorHelper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.backup.OtoBackup;
import utility.ComboboxDoldur;
import utility.MyDate;
import utility.enums.WPATH;
import utility.mesajlar.MyAlert;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OtomatikYedeklemeController extends List_View {

    public OtomatikYedeklemeController() {
        changeState(WPATH.otomatikYedekleme);
    }

    @FXML
    private TextField txtPath;
    @FXML
    private ComboBox<String> cmbPeriod;
    @FXML
    private DatePicker dPickerYedBTarihi;

    @Override
    public void fromForm() {
        ((OtoBackup) getEntityInterface()).setBackupPath(this.txtPath.getText());
        ((OtoBackup) getEntityInterface()).setBackupPeriod(this.cmbPeriod.getValue());
        if( dPickerYedBTarihi.getValue()!=null)
            ((OtoBackup) getEntityInterface()).setBackupDate(new MyDate(dPickerYedBTarihi.getValue()).getMyDateAsUtilDate());
    }

    @Override
    public void toForm() {
        if (entityInterface != null) {
            this.txtPath.setText(((OtoBackup) getEntityInterface()).getBackupPath());
            this.dPickerYedBTarihi.setValue(new MyDate(((OtoBackup) getEntityInterface()).getBackupDate()).getMyDateAsLocalDate());
            this.cmbPeriod.setValue(((OtoBackup) getEntityInterface()).getBackupPeriod());
        }
    }


    @FXML
    void backupToPostgreSQL() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(new Stage());
        if (directory != null)
            txtPath.setText(directory.getPath());
    }

    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulate(cmbPeriod, "Daily", "Weekly", "Monthly");
    }

    @Override
    protected void clearFormFields() {
        clearNodes(this.txtPath, this.cmbPeriod);
        dPickerYedBTarihi.setValue(null);
    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        err.isBosBirakilamaz(txtPath, cmbPeriod, dPickerYedBTarihi);
        LocalDate simdi = LocalDate.now();
        if (dPickerYedBTarihi.getValue() != null)
            if (dPickerYedBTarihi.getValue().isBefore(simdi)) {
                new MyAlert().showErrorAlert("History must belong to a time after now", "Incorrect Operation");
                return true;
            }
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null, null);
        comboboxlariDoldur();
    }
}
package controller;

import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import model.user.Groups;
import model.user.Permissionlar;
import utility.enums.OpType;
import utility.error.MyErrorHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import utility.ComboboxDoldur;
import utility.MyPredicateCreator;
import utility.enums.WPATH;

import java.util.ArrayList;
import java.util.List;

enum Durum {
    VAR("YES", true),
    YOK("NO", false);
    private String strValue;
    private boolean boolValue;

    Durum(String strValue, boolean boolValue) {
        this.strValue = strValue;
        this.boolValue = boolValue;
    }


    boolean getBoolValue() {
        return boolValue;
    }

    @Override
    public String toString() {
        return strValue;
    }
}

public class PermissionsController extends Table_View<Permissionlar> {
    @FXML    private ComboBox<String> cmbZones;
    @FXML    private ComboBox<Groups> cmbGroups;
    @FXML    private ComboBox<Durum> cmbSave;
    @FXML    private ComboBox<Durum> cmbDelete;
    @FXML    private ComboBox<Durum> cmbUpdate;
    @FXML    private ComboBox<Durum> cmbRead;

    @FXML
    void fillC() {
        if (cmbSave.getValue() == Durum.VAR) {
            cmbSave.setValue(Durum.YOK);
            cmbDelete.setValue(Durum.YOK);
            cmbRead.setValue(Durum.YOK);
            cmbUpdate.setValue(Durum.YOK);
        } else {
            cmbSave.setValue(Durum.VAR);
            cmbDelete.setValue(Durum.VAR);
            cmbRead.setValue(Durum.VAR);
            cmbUpdate.setValue(Durum.VAR);
        }
    }

    /** GİRİŞ */
    public PermissionsController() {
        changeState(WPATH.permissions);
    }

    /** DAO İŞLEMLERİ */
    @Override
    public void fromForm() {
        ((Permissionlar) getEntityInterface()).setZoneNames(cmbZones.getValue());
        ((Permissionlar) getEntityInterface()).setGrup(cmbGroups.getValue());
        ((Permissionlar) getEntityInterface()).setC(cmbSave.getValue().getBoolValue());
        ((Permissionlar) getEntityInterface()).setR(cmbRead.getValue().getBoolValue());
        ((Permissionlar) getEntityInterface()).setU(cmbUpdate.getValue().getBoolValue());
        ((Permissionlar) getEntityInterface()).setD(cmbDelete.getValue().getBoolValue());
    }

    @Override
    public void toForm() {
        /** özellikle getter kullanmadık null kontrolü yaptık */
        if (entityInterface != null) {
            this.cmbZones.setValue(((Permissionlar) getEntityInterface()).getZoneNames());
            this.cmbGroups.setValue(((Permissionlar) getEntityInterface()).getGrup());
            this.cmbSave.setValue(((Permissionlar) getEntityInterface()).isC() ? Durum.VAR : Durum.YOK);
            this.cmbRead.setValue(((Permissionlar) getEntityInterface()).isR() ? Durum.VAR : Durum.YOK);
            this.cmbUpdate.setValue(((Permissionlar) getEntityInterface()).isU() ? Durum.VAR : Durum.YOK);
            this.cmbDelete.setValue(((Permissionlar) getEntityInterface()).isD() ? Durum.VAR : Durum.YOK);
        }
    }


    @Override
    public void clearFormFields() {
        this.cmbSave.setValue(null);
        this.cmbDelete.setValue(null);
        this.cmbUpdate.setValue(null);
        this.cmbRead.setValue(null);
        this.cmbZones.setValue(null);
    }



    /** HATA KONTROLÜ */
    @Override
    // fonksiyon herhangi bir node null ise mesaj verilecek ve true dönecek isErrorBeforeDatabaseyi çağıran metod ise true aldığından işlem yapmayacak
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        err.isBosBirakilamaz(cmbRead, cmbUpdate, cmbDelete, cmbSave, cmbGroups, cmbZones);
        return err.hataVarsaMesajGosterReturnEt();
    }

    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh=new DFHelper(new Permissionlar());
        DesiredField[] df =dh.buFieldleriOlustur( "grup", "zoneNames", "c", "r", "u", "d");
        dh.fieldBasliklariSunlarOlsun("Group Name","Part","Permission to save","Permission to read","Permission to update","Permission to delete");
        return df;
    }


    private void cmbZoneDoldur() {
        List<String> liste = new ArrayList<>();
        for (WPATH w : WPATH.values()) {
            liste.add(w.getDescription());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(liste);
        cmbZones.getItems().setAll(observableList);
    }

    @Override
    protected void comboboxlariDoldur() {
        cmbZoneDoldur();
        ComboboxDoldur.comboboxPopulateFromDao(cmbGroups, Groups.class);
        ComboboxDoldur.comboboxPopulate(cmbDelete, Durum.VAR, Durum.YOK);
        ComboboxDoldur.comboboxPopulate(cmbUpdate, Durum.VAR, Durum.YOK);
        ComboboxDoldur.comboboxPopulate(cmbSave, Durum.VAR, Durum.YOK);
        ComboboxDoldur.comboboxPopulate(cmbRead, Durum.VAR, Durum.YOK);
    }

    @FXML
    public void cmbZoneGuncelle() {
        final ObservableList<Permissionlar> pList = tableView.getItems();
        ObservableList<String> zoneListInTableView = FXCollections.observableArrayList();//tabloda kayıtlı zoneler tutulacak
        final Groups g = cmbGroups.getValue();
        if (g == null) return;
        for (Permissionlar p : pList) {// eğer aynı grupsa
            if (p.getGrup().equals(g)) zoneListInTableView.add(p.getZoneNames());
        }
        // zone tablosundan zoneListte olmayan verileri al
        List<String> obsZones = new ArrayList<>();
        for (WPATH w : WPATH.values()) {
            if (w.getDescription() != null)   //listeye alınmasın istediğim kayıtlar wpath de null olarak işlendi
                if (!zoneListInTableView.contains(w.getDescription()))
                    obsZones.add(w.getDescription());
        }
        if (obsZones != null)
            cmbZones.setItems(FXCollections.observableList(obsZones));
    }

    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        return new MyPredicateCreator[]{};
    }
}

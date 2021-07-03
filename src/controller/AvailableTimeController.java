package controller;

import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import model.Available;
import model.Clazz;
import model.Professor;
import utility.enums.OpType;
import utility.error.MyErrorHelper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utility.ComboboxDoldur;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;


public class AvailableTimeController extends Table_View<Available> {

    @FXML private ComboBox<Professor> cmbProf;
    @FXML private ComboBox<Clazz> cmbClass;

    @FXML
    private DatePicker datePickerDate;

    @FXML
    private TextField txtAvailableTime;

   

    @FXML
    void classSelected() {

    }

 
    @FXML
    void profSelected() {

    }
    
   

    public AvailableTimeController() {
        changeState(WPATH.available);
    }




    @Override
    public void fromForm() {
        ((Available) getEntityInterface()).setProfessor(cmbProf.getValue());
        ((Available) getEntityInterface()).setaClassR(cmbClass.getValue());
        ((Available) getEntityInterface()).setAvailableTime(txtAvailableTime.getText());
        if (datePickerDate.getValue() != null ) {
            ((Available) getEntityInterface()).setAvailableDate(new MyDate(datePickerDate.getValue()).getMyDateAsLong());
        }
    }


    @Override
    public void toForm() {
        if (entityInterface != null) {
            this.cmbProf.setValue(((Available) entityInterface).getProfessor());
            this.cmbClass.setValue(((Available) entityInterface).getaClassR());
            this.txtAvailableTime.setText(((Available) entityInterface).getAvailableTime());
            this.datePickerDate.setValue(new MyDate(((Available) entityInterface).getAvailableDate()).getMyDateAsLocalDate());
        }
    }


    @Override
    public void clearFormFields() {
        clearNodes(this.cmbClass, this.cmbProf, this.txtAvailableTime);
    }



    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (err.isBosBirakilamaz(cmbProf,cmbClass,txtAvailableTime))
            return err.hataVarsaMesajGosterReturnEt(); //kesin hata! Aşağıya gitmeden tedbir amaçlı
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbClass,Clazz.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbProf,Professor.class);
    }



    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        return new MyPredicateCreator[]{};  //herhangi bir kritere göre filtrelenmediğinden sadece returnle iş bitiyor
    }



    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Available());
        DesiredField[] df = dh.buFieldleriOlustur("professor", "aClassR", "availableDate","availableTime");
        dh.fieldBasliklariSunlarOlsun("Professor", "Class Name", "Available Date", "Available Time");
        df[2].setDateTimeFormat("dd/MM/yyyy");
        return df;
    }


    private void keyListenerleriOlustur() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null, null);
        keyListenerleriOlustur();
    }
}

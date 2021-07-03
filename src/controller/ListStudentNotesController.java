package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.StudentNotes;
import utility.ComboboxDoldur;
import utility.MyPermisions;
import utility.MyPredicateCreator;
import utility.MyStagesShower;
import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import utility.enums.CommandTipi;
import utility.enums.OpType;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;


public class ListStudentNotesController extends Table_View<StudentNotes> {
    @FXML private Text lblStudent;
    @FXML private ComboBox<String> cmbTerm;
    @FXML private TextField txtYear;
    @FXML private Button btnRegNotes;




    public ListStudentNotesController() {
        changeState(WPATH.listStudentNotes);
        ClazzController.thisIsClasController=false;
    }


    @FXML
    private void showRegisterNoteModule(){
        new MyStagesShower().showFXML_FitHeight(WPATH.studentNotes);
    }


    private void permissionKontrolEdilecekNodeleriBelirle(){
        if (LoginController.getActiveUser() == null) return;
        MyPermisions mp = new MyPermisions();
        mp.checkPermissionsForThisNodes(
                new Node[]{btnRegNotes},
                new String[]{WPATH.studentNotes.getDescription()}, "r");
    }



    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulate(cmbTerm,"Spring","Autumn");
    }


    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        final MyPredicateCreator student = new MyPredicateCreator("student", SelectStudent.getActiveStudent());//not null field gönder
        final MyPredicateCreator term = new MyPredicateCreator("year",txtYear.getText(), CommandTipi.Equal);
        final MyPredicateCreator year = new MyPredicateCreator("term", cmbTerm.getValue(),  CommandTipi.Equal);
        return new MyPredicateCreator[]{student,term,year};  //herhangi bir kritere göre filtrelenmediğinden sadece returnle iş bitiyor
    }



    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new StudentNotes());
        DesiredField[] df = dh.buFieldleriOlustur("term","year","student", "sinif","quiz","midterm","finall","integration","grade","harfNotu");
        dh.fieldBasliklariSunlarOlsun("Term","Year","Student", "Class","Quiz","Midterm","Final","Integration","Grade","Letter Grade");
        df[8].setFormatOndalik(true);
        return df;
    }






    @FXML
    void showNotes() {
        populateTableCells(istenenAlanlariOlustur());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        permissionKontrolEdilecekNodeleriBelirle();
        lblStudent.setText(SelectStudent.getActiveStudent().toString());
        comboboxlariDoldur();
    }



    @Override
    public void fromForm() {

    }


    @Override
    public void toForm() {

    }



    @Override
    public void clearFormFields() {
    }



    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        return false;
    }

}

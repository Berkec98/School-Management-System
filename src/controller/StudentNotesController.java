package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Clazz;
import model.StudentNotes;
import utility.ComboboxDoldur;
import utility.MyPredicateCreator;
import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import utility.enums.OpType;
import utility.enums.WPATH;
import utility.error.MyErrorHelper;

import java.net.URL;
import java.util.ResourceBundle;




public class StudentNotesController extends Table_View<StudentNotes> {
    @FXML private Text lblStudent;
    @FXML private ComboBox<Clazz> cmbClass;
    @FXML private ComboBox<String> cmbTerm;
    @FXML private TextField txtQuiz,txtMidterm,txtFinal,txtIntegration,txtGrade,txtHaffNotu, txtYear;




    public StudentNotesController() {
        changeState(WPATH.studentNotes);
        ClazzController.thisIsClasController=false;
    }


    @Override
    public void fromForm() {
        try {
            ((StudentNotes) getEntityInterface()).setStudent(SelectStudent.getActiveStudent());
            ((StudentNotes) getEntityInterface()).setSinif(cmbClass.getValue());
            ((StudentNotes) getEntityInterface()).setQuiz(Double.parseDouble(txtQuiz.getText()));
            ((StudentNotes) getEntityInterface()).setMidterm(Double.parseDouble(txtMidterm.getText()));
            ((StudentNotes) getEntityInterface()).setFinall(Double.parseDouble(txtFinal.getText()));
            ((StudentNotes) getEntityInterface()).setIntegration(Double.parseDouble(txtIntegration.getText()));
            ((StudentNotes) getEntityInterface()).setTerm(cmbTerm.getValue());
            ((StudentNotes) getEntityInterface()).setYear(txtYear.getText());
        }catch(Exception ex){

        }
    }


    @Override
    public void toForm() {
        if (entityInterface != null) {

            try{
                this.cmbClass.setValue(((StudentNotes) entityInterface).getSinif());
                this.txtQuiz.setText(String.valueOf(((StudentNotes) entityInterface).getQuiz()));
                this.txtMidterm.setText(String.valueOf(((StudentNotes) entityInterface).getMidterm()));
                this.txtFinal.setText(String.valueOf(((StudentNotes) entityInterface).getFinall()));
                this.txtIntegration.setText(String.valueOf(((StudentNotes) entityInterface).getIntegration()));
                this.txtYear.setText(((StudentNotes) entityInterface).getYear());
                this.cmbTerm.setValue(((StudentNotes) entityInterface).getTerm());
            }catch(Exception ex) {

            }
            this.txtGrade.setText(String.valueOf(((StudentNotes) entityInterface).getGrade()));
            this.txtHaffNotu.setText(((StudentNotes) entityInterface).getHarfNotu());
        }
    }



    @Override
    public void clearFormFields() {
        clearNodes(this.cmbClass, this.txtQuiz, this.txtMidterm, this.txtFinal, this.txtIntegration, this.txtGrade, this.txtHaffNotu);
    }



    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (err.isBosBirakilamaz(cmbClass,cmbTerm,txtYear))
            return err.hataVarsaMesajGosterReturnEt(); //kesin hata. aşağıya gitmeden tedbir amaçlı
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulate(cmbTerm,"Spring","Autumn");
        ComboboxDoldur.comboboxPopulateFromDao(cmbClass, Clazz.class);
    }


    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        final MyPredicateCreator student = new MyPredicateCreator("student", SelectStudent.getActiveStudent());//not null field gönder
        return new MyPredicateCreator[]{student};  //herhangi bir kritere göre filtrelenmediğinden sadece returnle iş bitiyor
    }



    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new StudentNotes());
        DesiredField[] df = dh.buFieldleriOlustur("term","year","student", "sinif","quiz","midterm","finall","integration","grade","harfNotu");
        dh.fieldBasliklariSunlarOlsun("Term","Year","Student", "Class","Quiz","Midterm","Final","Integration","Grade","Letter Grade");
        df[8].setFormatOndalik(true);
        return df;
    }


    private void keyListenerleriOlustur() {
        Node[] focusOrder = new Node[]{this.cmbClass, this.txtYear,this.cmbTerm,this.txtQuiz, this.txtMidterm, this.txtFinal, this.txtIntegration, this.txtGrade, this.txtHaffNotu};
        Node[] digit = new Node[]{ this.txtQuiz, this.txtMidterm, this.txtFinal, this.txtIntegration,this.txtYear};
        addActivateEnterKeyPressedListenerFor(focusOrder);   //nodeleri Listenere ekler
        addActivateDoOnlyDigitKeyTypedListeners(digit);
    }

    private void permissionKontrolEdilecekNodeleriBelirle() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblStudent.setText(SelectStudent.getActiveStudent().toString());
        permissionKontrolEdilecekNodeleriBelirle();
        super.initialize(null, null);
        keyListenerleriOlustur();
    }

}

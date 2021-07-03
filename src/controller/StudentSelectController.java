package controller;

import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import model.Student;
import utility.enums.OpType;
import utility.error.ErrorMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utility.HelperCloseResource;
import utility.ComboboxDoldur;
import utility.MyPredicateCreator;
import utility.enums.CommandTipi;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentSelectController extends Table_View<Student> {
    @FXML
    private Text lblSelected;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<String> cmbAramaKriteri;


    public StudentSelectController() {
        changeState(WPATH.student);
    }

    @FXML
    void bilgileriniTablodanAl(MouseEvent event) {
        Student tmpEntity = tableView.getSelectionModel().getSelectedItem();
        if (tmpEntity != null) {
            setActiveStudent(tmpEntity);
        }


        if (event.getClickCount() == 2) {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }

    @FXML
    void tableViewYukariAsagi(KeyEvent event) {  //klavyenin yukarı ve aşağı ok tuşlarıyla tableviewde gezinmek için keyRelease olayı
        if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.DOWN))
            bilgileriniTablodanAl(null);
    }

    @FXML
    void btnTamam(ActionEvent event) {

        if (SelectStudent.getActiveStudent() == null) {
            ErrorMessage.NO_ACTIVE_STUDENT.printErrorMessages();
            return;
        }
        HelperCloseResource.helperCloseBtn((Node) event.getSource());
        //((Stage)((Node) event.getSource()).getScene().getWindow()).close();
        //dialogu kapat
    }


    public void setActiveStudent(Student activeStudent) {
        SelectStudent.setActiveStudent(activeStudent);
        lblSelected.setText(SelectStudent.getActiveStudent().getName() + " " + SelectStudent.getActiveStudent().getSurname());
    }


    @FXML
    void filterByTxtSearch(KeyEvent event) {
        //datayı veritabanından değilde tablodan alırsa hıza çok katkısı olur
        populateTableCells(istenenAlanlariOlustur());
    }


    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulate(cmbAramaKriteri, "TC Identification", "Name Surname");
    }


    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Student());
        DesiredField[] df = dh.buFieldleriOlustur("tcIdentity", "name", "surname", "phoneNumber");
        dh.fieldBasliklariSunlarOlsun("TC Identification", "Student Name", "Surname",  "Phone Number");
        return df;
    }

    @Override
    protected void fromForm() {
    }

    @Override
    protected void toForm() {

    }

    @Override
    protected void clearFormFields() {

    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        return false;
    }


    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        if (isEmptyOrNull(txtSearch)) return null;
        final String aranilanMetin = txtSearch.getText();
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
    public void initialize(URL location, ResourceBundle resources) {
        // txtSearch.setOnKeyReleased();
        comboboxlariDoldur();
        populateTableCells(istenenAlanlariOlustur());
        addStrFocusLostListener(new Node[]{txtSearch});
    }
}

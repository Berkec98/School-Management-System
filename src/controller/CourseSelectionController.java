package controller;

import daolar.DaoRepositoryImp;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Clazz;
import model.Student_Class;
import utility.ComboboxDoldur;
import utility.MyPredicateCreator;
import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import utility.enums.OpType;
import utility.enums.WPATH;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseSelectionController extends Table_View<Student_Class>  {

    @FXML
    private Text lblFaculty,lblDepartment,lblStudent,lblStudentNo,lblLink;
    @FXML
    private TextField txtYear;
    @FXML
    private ComboBox<Clazz> cmbClass;
    @FXML
    private ComboBox<String> cmbTerm;
    @FXML
    private ListView<Clazz> listViewAvailableCourses;

    @FXML void goLink() {
        String link=lblLink.getText();
        if(link.contains("Course Material Link: ....")) return;
        try{
            Runtime rt = Runtime.getRuntime();
            String url = lblLink.getText();
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Web sayfasına bağlanılamadı\n" +
                    "Detaylar: " +e.getMessage());
        }
    }

    public CourseSelectionController() {
        changeState(WPATH.student_class);
    }

    private void fillListview() {
        MyPredicateCreator departmentPredicate = new MyPredicateCreator("department", SelectStudent.getActiveStudent().getDepartment());
        List<Clazz> tmpList = new DaoRepositoryImp(new Clazz().getClass()).getAll(departmentPredicate);
        if(tmpList==null)
            JOptionPane.showMessageDialog(null, SelectStudent.getActiveStudent().getDepartment()+"Course are not defined in the department");
        else listViewAvailableCourses.getItems().addAll(tmpList);
    }

    @Override
    protected void fromForm() {
        ((Student_Class) getEntityInterface()).setTerm(cmbTerm.getValue());
        ((Student_Class) getEntityInterface()).setYear(txtYear.getText());
        ((Student_Class) getEntityInterface()).setStudent(SelectStudent.getActiveStudent());
        ((Student_Class) getEntityInterface()).setClazz(cmbClass.getValue());
    }

    @Override
    protected void toForm() {
        if (entityInterface != null) {
            this.cmbClass.setValue(((Student_Class) entityInterface).getClazz());
            this.cmbTerm.setValue(((Student_Class) entityInterface).getTerm());
            this.txtYear.setText(((Student_Class) entityInterface).getYear());
            this.lblLink.setText(((Student_Class) entityInterface).getClazz().getLink());
        }
    }

    @Override
    protected void clearFormFields() {
        clearNodes( this.txtYear, this.cmbClass, this.cmbTerm);
        this.lblLink.setText("Course Material Link: ....");
    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        return false;
    }

    @Override
    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbClass,Clazz.class);
        ComboboxDoldur.comboboxPopulate(cmbTerm,"Spring","Autumn");

    }

    @Override
    protected DesiredField[] istenenAlanlariOlustur() {
            DFHelper dh = new DFHelper(new Student_Class());
            DesiredField[] df = dh.buFieldleriOlustur("clazz");
            dh.fieldBasliklariSunlarOlsun("Selected Course");
            return df;
    }

    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        return new MyPredicateCreator[0];
    }

    private void lbl_doldur(){
        lblStudent.setText(SelectStudent.getActiveStudent().getName()+" "+ SelectStudent.getActiveStudent().getSurname());
        lblStudentNo.setText(SelectStudent.getActiveStudent().getSchoolNumber());
        lblFaculty.setText(SelectStudent.getActiveStudent().getDepartment().getFaculty().getName());
        lblDepartment.setText(SelectStudent.getActiveStudent().getDepartment().getName());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbl_doldur();
        fillListview();
        comboboxlariDoldur();
        setButtonsVisibleAccordingToPermission(getActiveModulo().getDescription());
        populateTableCells(istenenAlanlariOlustur());
    }
}

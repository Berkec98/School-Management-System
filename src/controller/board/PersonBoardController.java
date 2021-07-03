package controller.board;


import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import utility.MyPermisions;
import utility.MyStagesShower;
import utility.enums.WPATH;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonBoardController implements Initializable {

    @FXML private Button btnRegProfessor;
    @FXML private Button btnRegPersonnel;
    @FXML private Button btnRegStudent;


    @FXML void showRegisterOtherPersonnel(ActionEvent event) {
        new MyStagesShower().showFXML_FitHeight(WPATH.personnel);
    }

    @FXML void showRegisterProfessor(ActionEvent event) {
        new MyStagesShower().showFXML_FitHeight(WPATH.professor);
    }

    @FXML void showRegisterStudent(ActionEvent event) {
        new MyStagesShower().showFXML_FitHeight(WPATH.student);
    }

    @FXML
    private Button btnYetkilendirme;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //süper adminin hepsini görüntülemesine izin vermek zorundayız yoksa ilk kurulumda tablolar boş olduğundan hiç bir bölümde çalışılamaz
        if (LoginController.getActiveUser() == null) return; //null sisiteme giremez eğer girmişse bu SüperAdmindir

        new MyPermisions().checkPermissionsForThisNodes(
                new Node[]{
                        btnRegPersonnel,
                        btnRegProfessor,
                        btnRegStudent
                },
                new String[]{
                        WPATH.personnel.getDescription(),
                        WPATH.professor.getDescription(),
                        WPATH.student.getDescription()
                }, "r");
    }
}

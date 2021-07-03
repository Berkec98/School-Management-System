package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.backup.LoginVTYSBackupImpl;
import daolar.DaoRepositoryImp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.user.Users;
import org.apache.commons.codec.digest.DigestUtils;
import utility.MyPredicateCreator;
import utility.MyStagesShower;
import utility.Txt;
import utility.enums.CommandTipi;
import utility.enums.WPATH;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML private JFXTextField userName;
    @FXML private JFXPasswordField password;
    @FXML private JFXButton btnGiris;
    @FXML private JFXButton btnCancel;

    private final Txt txt = new Txt("login.txt");

    private static Users activeUser;

    public static Users getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(Users activeUser) {
        LoginController.activeUser = activeUser;
    }

    @FXML
    public void handleCancelButtonAction() {
        System.exit(0);
    }

    @FXML
    void handleLoginButtonAction() {

        final String uName = this.userName.getText();
        final String pswd = DigestUtils.shaHex(this.password.getText());

        //Not: sadece 1 için sha1 hash kodu: 356a192b7913b04c54574d18c28d46e6395428ab dir.
        //her zaman ki için sha1 hash kodu: 2ed45c6c963a76b31b6fd3c1bbcdc5faabd18fee
            if((uName.equals("SuperAdmin") && pswd.equals("356a192b7913b04c54574d18c28d46e6395428ab"))||(isUserHave(uName, pswd))) {
                closeStage();
                new LoginVTYSBackupImpl().vtysOtoYedZamaniysaYedekle();
                showUIBar();
            }
    }






    private boolean isUserHave(String userName, String password) {
        final DaoRepositoryImp<Users> dao = new DaoRepositoryImp<>(Users.class);
        final MyPredicateCreator uniqueUserName=new MyPredicateCreator("userName", userName,CommandTipi.Equal);
        activeUser = dao.getSingleStringResult(uniqueUserName);
        return activeUser != null && activeUser.getPassword().equals(password);
    }


    private void closeStage() {
        ((Stage) this.userName.getScene().getWindow()).close();
    }



    void showUIBar() {
        MyStagesShower mss = new MyStagesShower();
        mss.showFXML_UIBar(WPATH.uiBar);
    }








    private void keyListenerleriOlustur() {
        userName.focusedProperty().addListener((ov, oldV, newV) -> {  //miktar focus kaybedince çarpma yapsın
            if (!newV) { // focus lost
                try {
                    txt.toTXT(userName.getText());
                } catch (IOException e) {
                    System.out.println("Error while saving userName.txt");
                    e.printStackTrace();
                }
            }
        });
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keyListenerleriOlustur();
        try {
            userName.setText(txt.fromTXT());
        } catch (FileNotFoundException e) {
            System.out.println("LOGIN CONTROLLER:no txt file to read username");
            e.printStackTrace();
        }
        btnGiris.setDefaultButton(true);
        btnCancel.setCancelButton(true);
    }
}
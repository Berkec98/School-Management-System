package utility.mesajlar;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MyAlert {


    public Boolean isAlertResponseOK(String mesaj, String header) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(mesaj);
        a.setTitle(null);
        a.setHeaderText(header);
        Optional<ButtonType> buttonType=a.showAndWait();
        return buttonType.filter(type -> type == ButtonType.OK).isPresent();
    }



    public void showErrorAlert(String mesaj, String header) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(mesaj);
        a.setTitle(null);
        a.setResizable(true);
        a.setHeaderText(header);
        a.show();
    }



}

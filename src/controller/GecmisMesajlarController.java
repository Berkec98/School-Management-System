package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utility.mesajlar.Notify;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GecmisMesajlarController implements Initializable {

    @FXML
    private ListView<String> listView;


    public void listeyeEkle() throws IOException {
        for (String notifies:Notify.getNotifyList()) {
            listView.getItems().add(0,"--------------------------------------------------------------------");
            listView.getItems().add(0,notifies);

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listeyeEkle();
        } catch (IOException e) {
            new Notify().showErrorNotify("Runtime Error","An unknown error occurred while listing the Past Messages.");
        }
    }
}

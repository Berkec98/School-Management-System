package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utility.mesajlar.Notify;
import utility.Txt;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HakkindaController implements Initializable {

    @FXML
    private ListView<String> listViewHakkinda;


    /**
     * Hakkind.txt dosyasını list viewe yükler
     *
     * @throws IOException giriş çıkış hatası oluşabilir. En genel durumda dosyanın klsörde olmamasıdır
     */
    public void dosyadanAl() throws IOException {
        Txt txt = new Txt("Hakkinda.txt");
        final List<String> txtDosyaIcerigi = txt.readAllFromFileToList();
        for (String m : txtDosyaIcerigi) {
            listViewHakkinda.getItems().add(m);
        }
        listViewHakkinda.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dosyadanAl();
        } catch (IOException e) {
            new Notify().showErrorNotify("FILE NOT FOUND","Hakkinda.txt not found\n Make sure the file is in the working folder.");
        }
    }
}

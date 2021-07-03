package controller.backup;


import javafx.fxml.FXML;
import javafx.stage.Stage;
import utility.yedekleme.FBase;
import utility.yedekleme.Restore;
import utility.mesajlar.MyAlert;

import java.io.File;
import java.io.IOException;


public class VerileriGeriYukleController extends FBase {

    @FXML
    public void restore() throws IOException {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            this.lblPath.setText(file.getPath());
            boolean isOkResponse =new MyAlert().isAlertResponseOK("All existing data will be deleted and new data will be loaded.\nDo you confirm?","Confirmation of deletion");
            if (!isOkResponse) return;
            //-c paremetresi eski datayı silmesi için
            final Restore restore=new Restore();
            final Process p=restore.restoreIt(file.getPath());
            listeyeEkle(p);
            restore.sonucuKaydet("Restore","Manual",file.getPath(),true);
            //BAŞARILI VE BAŞARISIZ MESAJLARINI EKLE
        }
    }
}

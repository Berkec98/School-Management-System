package controller.backup;


import javafx.fxml.FXML;
import javafx.stage.Stage;
import utility.yedekleme.Backup;
import utility.yedekleme.FBase;
import java.io.File;

public class VerileriYedekleController extends FBase {

    @FXML
    public void backupToPostgreSQL() {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            final String path = file.getPath();
            this.lblPath.setText(path);
            if (file.exists()) {
                file.delete();
            }
            final Backup backup=new Backup();
            final Process p = backup.backupIt(path);
            if (p != null) { //null olmaması başarılı olduğunu gösterir
                listeyeEkle(p);
                backup.sonucuKaydet("backup","manual",file.getPath(),true);
                //İŞLEM BAŞARILI MESAJI VER
            }
        }
    }
}

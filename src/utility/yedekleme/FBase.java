package utility.yedekleme;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FBase {
    @FXML  protected ListView<String> listView;
    @FXML  protected Label lblPath;
    final protected FileChooser fileChooser = new FileChooser();



    protected void listeyeEkle(final Process p) {
        try {
            final InputStream is = p.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            final BufferedReader br = new BufferedReader(isr);
            listView.getItems().clear();
            String ll;
            while ((ll = br.readLine()) != null) {
                listView.getItems().add(0, ll);
            }
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            listView.getItems().add(0, e.getMessage());
            listView.getItems().add(0, "Error occurred during backup");
            e.printStackTrace();
        }
        if (p.exitValue() == 1) {
            listView.getItems().add(0, "***************ERROR OCCURRED*******************");
            listView.getItems().add(1, "** DETAILS OF THE ERROR OCCURRED BELOW ********");
        } else if (p.exitValue() == 0) listView.getItems().add(0, "Transaction Completed Successfully....");
    }
}

package controller.log;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import utility.HelperCloseResource;

public class LogDetailController {

    @FXML private Text txtUser;
    @FXML private Text txtYapilanIslem;
    @FXML private Text txtTablo;
    @FXML private Text txtIsleminTarihi;
    @FXML private Text txtIsleminSaati;
    @FXML private Text txtEskiVeri;
    @FXML private Text txtYeniVeri;
    @FXML protected void closeDialog(MouseEvent event) {
        HelperCloseResource.helperCloseBtn((Node) event.getSource());
    }

    public void setTxtUser(String txtUser) {
        this.txtUser.setText(txtUser);
    }

    public void setTxtYapilanIslem(String txtYapilanIslem) {
        this.txtYapilanIslem.setText(txtYapilanIslem);
    }

    public void setTxtTablo(String txtTablo) {
        this.txtTablo.setText(txtTablo);
    }

    public void setTxtIsleminTarihi(String txtIsleminTarihi) {
        this.txtIsleminTarihi.setText(txtIsleminTarihi);
    }

    public void setTxtIsleminSaati(String txtIsleminSaati) {
        this.txtIsleminSaati.setText(txtIsleminSaati);
    }

    public void setTxtEskiVeri(String txtEskiVeri) {
        this.txtEskiVeri.setText(txtEskiVeri);
    }

    public void setTxtYeniVeri(String txtYeniVeri) {
        this.txtYeniVeri.setText(txtYeniVeri);
    }
}

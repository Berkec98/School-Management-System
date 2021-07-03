package controller;


import utility.enums.OpType;
import utility.listView.List_View;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import model.user.Groups;
import model.user.Users;
import model.Person;
import utility.error.ErrorMessage;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;
import utility.ComboboxDoldur;
import utility.enums.WPATH;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KullaniciTanimlaController extends List_View {
    @FXML
    private FontAwesomeIconView parolaOk;
    @FXML
    private ComboBox<Person> cmbAitOldPers;
    @FXML
    private FontAwesomeIconView parolaRepeatOk;
    @FXML
    private TextField txtKullaniciAdi;
    @FXML
    private PasswordField txtParola;
    @FXML
    private PasswordField txtParolaRepeat;
    @FXML
    private ComboBox<Groups> cmbGroup;

    public KullaniciTanimlaController() {
        changeState(WPATH.kullaniciTanimla);
    }

    @Override
    protected void fromForm() {
        ((Users) getEntityInterface()).setAitOldPers(this.cmbAitOldPers.getValue());
        ((Users) getEntityInterface()).setUserName(this.txtKullaniciAdi.getText());
        ((Users) getEntityInterface()).setPassword(DigestUtils.shaHex(this.txtParola.getText()));
        ((Users) getEntityInterface()).setGroups(this.cmbGroup.getValue());
    }

    @Override
    protected void toForm() {
        if (entityInterface != null) {
            this.cmbAitOldPers.setValue(((Users) entityInterface).getAitOldPers());
            this.txtKullaniciAdi.setText(((Users) entityInterface).getUserName());
            this.txtParola.setText(((Users) entityInterface).getPassword());
            this.txtParolaRepeat.setText(((Users) entityInterface).getPassword());
            this.cmbGroup.setValue(((Users) entityInterface).getGroups());
        }
    }

    public void populateCombobox() {
        ComboboxDoldur.comboboxPopulateFromDao(cmbAitOldPers, Person.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbGroup, Groups.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null, null);
        populateCombobox();
        keyListenerleriOlustur();
    }

    @Override
    protected void clearFormFields() {
        clearNodes(this.txtKullaniciAdi, this.txtParola, this.txtParolaRepeat, this.cmbGroup, this.cmbAitOldPers);
        parolaRepeatOk.setVisible(false);
        parolaOk.setVisible(false);
    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        if (!parolaOk.isVisible() || !parolaRepeatOk.isVisible()) {
            ErrorMessage.ANOTHER_ERROR.printErrorMessages("Please ensure Password is valid");
            return true;
        }
        return false;
    }


    private boolean isPatternHave(String regex, String password) {
        Pattern upperCasePattern = Pattern.compile(regex);
        Matcher m = upperCasePattern.matcher(password);
        return m.find();
    }

    public String isParolaValid() {
        String hatalar = "";
        String parola = txtParola.getText();
        if (parola.length() < 8) hatalar += "* Password can not be less than 8 character \n";
        if (parola.length() > 10) hatalar += "* Password can not be more than 10 character\n";
        if (!isPatternHave("[A-Z]", parola)) hatalar += "* Password must contain at least 1 uppercase letter\n";
        if (!isPatternHave("[a-z]", parola)) hatalar += "* Password must contain at least 1 lowercase letter\n";
        if (!isPatternHave("[0-9]", parola)) hatalar += "* Password must contain at least 1 number";
        if (!isPatternHave("[\\p{P}\\p{S}]", parola))
            hatalar += "* Password must contain at least 1 special character.\n";

        return hatalar;
    }

    private void keyListenerleriOlustur() {
        //nodeleri Listenere ekler 2 çeşit listener var digit ve focus
        Node[] focusOrder = new Node[]{cmbAitOldPers, txtKullaniciAdi, txtParola, txtParolaRepeat, cmbGroup};
        addActivateEnterKeyPressedListenerFor(focusOrder);   //nodeleri Listenere ekler
        txtKullaniciAdi.focusedProperty().addListener((ov, oldV, newV) -> {
                if (!newV) { // focus lost
                    txtKullaniciAdi.setText(txtKullaniciAdi.getText().trim());
                }
        });
        //addStrFocusLostListener(new Node[]{txtKullaniciAdi});
        //lost focus için listener
        txtParola.focusedProperty().addListener((observableValue, oldValue, newValue) -> {  //parola focus kaybedince kontrol yapsın
            if (!newValue) { // focus lost
               // checkRepeat();//repeatteki check simgeyi yeniden düzenleniyor
                String hatalar = isParolaValid();//parola kontrol edilir string döndürür. empty ise hiç hata mesajı oluşmamış hatasız.
                if (!hatalar.isEmpty()) ErrorMessage.ANOTHER_ERROR.printErrorMessages(hatalar);
            }
        });
        //change listener
        txtParola.textProperty().addListener((observableValue, oldValue, newValue) -> {  //parola focus kaybedince kontrol yapsın
            String hatalar = isParolaValid();
            if (hatalar.isEmpty()) parolaOk.setVisible(true);
            else parolaOk.setVisible(false);

        });
        //change listener
        txtParolaRepeat.textProperty().addListener((observableValue, oldValue, newValue) -> {  //parola focus kaybedince kontrol yapsın
            checkRepeat();
        });
    }

    private void checkRepeat() {  //repeat check simgesinin visible olup olmamasını belirler
        if (txtParola.getText().equals(txtParolaRepeat.getText()) && !txtParolaRepeat.getText().isEmpty() && (parolaOk.isVisible())) {
            parolaRepeatOk.setVisible(true);
        } else {
            parolaRepeatOk.setVisible(false);
        }
    }

}

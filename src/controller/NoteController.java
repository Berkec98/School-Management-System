package controller;

import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import model.Note;
import utility.enums.OpType;
import utility.error.MyErrorHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import utility.MyPredicateCreator;
import utility.enums.WPATH;

import java.net.URL;
import java.util.ResourceBundle;


public class NoteController extends Table_View<Note> {

    /** Variables */

    @FXML private TextField txtGrade;
    @FXML private TextField txtStartingNote;
    @FXML private TextField txtEndingNote;

    public NoteController() {
        changeState(WPATH.note);
    }


    @Override
    public void fromForm() {
        ((Note) getEntityInterface()).setStartGrade(Double.parseDouble(txtStartingNote.getText()));
        ((Note) getEntityInterface()).setEndGrade(Double.parseDouble(txtEndingNote.getText()));
        ((Note) getEntityInterface()).setLetterGrade(txtGrade.getText());
    }


    @Override
    public void toForm() {
        if (entityInterface != null) {
            this.txtStartingNote.setText(String.valueOf(((Note) entityInterface).getStartGrade()));
            this.txtEndingNote.setText(String.valueOf(((Note) entityInterface).getEndGrade()));
            this.txtGrade.setText(((Note) entityInterface).getLetterGrade());
        }
    }


    @Override
    public void clearFormFields() {
        clearNodes(this.txtEndingNote, this.txtStartingNote, this.txtGrade);
    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        MyErrorHelper err = new MyErrorHelper();
        if (err.isBosBirakilamaz(txtGrade))
            return err.hataVarsaMesajGosterReturnEt(); //kesin hata. Aşağıya gitmeden tedbir amaçlı
        return err.hataVarsaMesajGosterReturnEt();
    }


    @Override
    protected void comboboxlariDoldur() {
    }


    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        return new MyPredicateCreator[]{};  //herhangi bir kritere göre filtrelenmediğinden sadece returnle iş bitiyor
    }

    protected DesiredField[] istenenAlanlariOlustur() {
        DFHelper dh = new DFHelper(new Note());
        DesiredField[] df = dh.buFieldleriOlustur("startGrade", "endGrade", "letterGrade");
        dh.fieldBasliklariSunlarOlsun("Start Note", "End Note", "Letter Grade");
        df[0].setFormatOndalik(true);
        df[1].setFormatOndalik(true);
        return df;
    }


    private void keyListenerleriOlustur() {
        Node[] digitOrder = new Node[]{txtStartingNote, txtEndingNote};
        addActivateDoOnlyDigitKeyTypedListeners(digitOrder); //nodeleri Listenere ekler 2 çeşit listener var digit ve focus
        Node[] focusOrder = new Node[]{txtGrade, txtStartingNote, txtEndingNote};
        addActivateEnterKeyPressedListenerFor(focusOrder);   //nodeleri Listenere ekler
        addStrFocusLostListener(new Node[]{txtGrade});

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(null, null);
        keyListenerleriOlustur();
    }
}

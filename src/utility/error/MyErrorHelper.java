package utility.error;

import daolar.DaoRepositoryImp;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.base.BaseEntity;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.enums.CommandTipi;
import utility.mesajlar.MyAlert;

import java.util.List;


public class MyErrorHelper {

    private StringBuilder mesaj = new StringBuilder();


    public boolean hataVarsaMesajGosterReturnEt() {
        if (getMesaj().length() != 0) {
            final String msg_baslik = "DETECTED ERRORS\n-------------------------------------\n";
            new MyAlert().showErrorAlert(msg_baslik + mesaj,"incorrect data / data");
            return true;
        } else
            return false;
    }


    public void tarihVerisiDogruGirildiMi(TextField textField, String fieldDescription) {
        if (isEmptyOrNull(textField)) bosBirakilamaz_MESSAGE(fieldDescription);
        else {
            final MyDate tmpDate = new MyDate(textField.getText(), "dd/MM/yyyy");
            if (tmpDate.getMyDateAsUtilDate() == null)
                tarihVerisiDogruGirildiMi_MESSAGE(textField.getText(), fieldDescription);
        }
    }




    public void boyutSifirOlurAmaFazlaAzOlamaz(String value, String nodeDescription, int length) {
        if (value != null && value.length() != 0)
            if (value.length() != length)
                boyutSifirOlurAmaFazlaAzOlamaz_MESSAGE(nodeDescription, length, value.length());
    }


    public void boyutFazlaOlamaz(String value, String nodeDescription, int length) {
        if (value.length() > length) boyutFazlaOlamaz_MESSAGE(nodeDescription, length, value.length());
    }


    public void uniqueDegerTekrarGirilemez(DaoRepositoryImp dao, String nodeDescription, String fieldName, String fieldValue, int id) {
        final MyPredicateCreator savePredicate = new MyPredicateCreator(fieldName, fieldValue, CommandTipi.Equal);
        List<BaseEntity> tmp;
        if (id == 0) {//save
            tmp = dao.getAll(savePredicate);
        } else { //update
            MyPredicateCreator updatePredicate = new MyPredicateCreator("id", String.valueOf(id), CommandTipi.NotEqual);
            tmp = dao.getAll(savePredicate, updatePredicate);
        }

        if (tmp!=null && tmp.size() > 0) ayniDegerVar_MESSAGE(fieldValue, nodeDescription);
    }


    /*
     @param dao
     @param fieldValues     hata oluşursa mesaj verirken görüntülenecek data
     @param nodeDescription alanlarla ilgili tanımlama yapılır
     @param predicates
     */
    public void sinifaOzgunPredicateCalistir(DaoRepositoryImp dao, String fieldValues, String nodeDescription, MyPredicateCreator... predicates) {
        List<BaseEntity> tmp;
        tmp = dao.getAll(predicates);
        if (tmp.size() > 0) ayniDegerVar_MESSAGE(fieldValues, nodeDescription);
    }


    //try cath sebebi string gelirse doubleye parse edemez
    //Bu metod kullanılacaksa öncesinde empty kontrolüne gerek yok ben buarada alıyorum
    public void sifirOlamaz(TextField textField, String nodeDescription) {
        try {
            if (textField.getText().isEmpty() || Double.parseDouble(textField.getText()) < 1)
                sifirOlamaz_MESSAGE(nodeDescription);
        } catch (NumberFormatException e) {
            rakamOlmali_MESSAGE(nodeDescription);
        }
    }


    public boolean isBosBirakilamaz(Node... nodes) {
        for (Node node : nodes) {

            if (node instanceof TextField)
                if (isEmptyOrNull((TextField) node)) {
                    bosBirakilamaz_MESSAGE(node.getId());
                }

            if (node instanceof ComboBox)
                if (isEmptyOrNull((ComboBox) node)) {
                    bosBirakilamaz_MESSAGE(node.getId());
                }
            if (node instanceof DatePicker)
                if (((DatePicker) node).getValue() == null) {
                    bosBirakilamaz_MESSAGE(node.getId());
                }
        }
        return mesaj.length() > 0; // >0 ise true yani evet hata var
    }


    //DESTEK METODLARI
    private boolean isEmptyOrNull(TextField textField) {
        return textField.getText().isEmpty() || textField.getText() == null;
    }


    private boolean isEmptyOrNull(ComboBox comboBox) {
        return comboBox.getValue() == null || comboBox.getSelectionModel().getSelectedIndex() < 0  ;
    }


    private StringBuilder getMesaj() {
        return mesaj;
    }


    //MESAJ METODLARI

    private void sifirOlamaz_MESSAGE(String nodeDescription) {
        this.mesaj.append(nodeDescription).append(" You cannot enter 0 (zero) or NULL as a value in the field.\n");
    }



    private void rakamOlmali_MESSAGE(String nodeDescription) {
        this.mesaj.append(nodeDescription).append(" You only have to enter numbers in the field. Check it out. \n");
    }

    private void boyutSifirOlurAmaFazlaAzOlamaz_MESSAGE(String nodeDescription, int length, int valueLength) {
        this.mesaj.append(nodeDescription).append(" field was entered incorrectly. ").append(length).append(" cannot be more or less than the characters (you enter:").append(valueLength).append(" character)\n");
    }


    private void boyutFazlaOlamaz_MESSAGE(String nodeDescription, int length, int valueLength) {
        this.mesaj.append(nodeDescription).append(" field was entered incorrectly. ").append(length).append(" cannot be more or less than the characters (you enter:").append(valueLength).append(" character )\n");
    }


    private void ayniDegerVar_MESSAGE(String fieldValue, String nodeDescription) {
        this.mesaj.append(fieldValue).append(" ").append(nodeDescription).append(" already entered before. You cannot use the same \n");
    }

    private void tarihVerisiDogruGirildiMi_MESSAGE(String fieldValue, String nodeDescription) {
        this.mesaj.append(nodeDescription).append(" you entered the field '").append(fieldValue).append("' the date is incorrect in the form . \n Correct it to be dd/MM/yyyy(Day / Month / Year). \n");
    }

    private void bosBirakilamaz_MESSAGE(String alanAdi) {
        this.mesaj.append(alanAdi).append(" Cannot be left blank\n");
    }
}
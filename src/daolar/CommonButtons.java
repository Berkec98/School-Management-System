package daolar;

import Crud.CrudI;
import Crud.CrudImpDelete;
import Crud.CrudImpSave;
import Crud.CrudImpUpdate;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.base.CommonEntity;
import model.base.EntityInterface;
import model.user.Users;
import utility.Confirm;
import utility.MyDate;
import utility.MyPermisions;
import utility.enums.OpType;
import utility.listeners.MyCatchKeyEnter;
import utility.listeners.MyDigitAndDotAllowed;

import java.util.Date;

import static utility.enums.OpType.DELETE;

public abstract class CommonButtons extends DAO {

    protected abstract void fromForm();
    protected abstract void toForm();
    protected abstract void clearFormFields();
    protected abstract boolean isErrorBeforeDatabase(OpType opType);
    protected abstract void refreshViewer(OpType opType);


    /**VARIABLES*/

    @FXML
    protected Button btnCancel;

    @FXML
    protected Button btnSave;

    @FXML
    protected Button btnDelete;

    @FXML
    protected Button btnUpdate;

    public Button getBtnSave() {
        return btnSave;
    }

    /*
      @param nodes bu fonksiyon kendisine gelen nodeleri temizler
     */
    protected void clearNodes(Node... nodes) {
        for (Node node : nodes) {
            if (node instanceof TextField) ((TextField) node).clear();
            if (node instanceof Label) ((Label) node).setText(null);
            if (node instanceof Text) ((Text) node).setText(null);
            if (node instanceof ComboBox) ((ComboBox) node).setValue(null);
        }
    }




    /*
    @param nodes null ve isEmpty kontrolü yapılacak nodeler
    @return eğer null veya isEmty ise true döner
     */
    protected boolean isEmptyOrNull(Node... nodes) {
        for (Node node : nodes) {
            if (node instanceof TextField)
                if (((TextField) node).getText().isEmpty() || ((TextField) node).getText() == null) return true;
            if (node instanceof ComboBox) if (((ComboBox) node).getValue() == null) return true;
        }
        return false;
    }



    protected void addActivateDoOnlyDigitKeyTypedListeners(Node[] digitOrders) {
        for (Node node : digitOrders) {
            node.setOnKeyTyped(new MyDigitAndDotAllowed());
        }
    }

    protected void addActivateEnterKeyPressedListenerFor(Node[] focusOrders) {
        for (Node node : focusOrders) {
            MyCatchKeyEnter my=new MyCatchKeyEnter(this,focusOrders);
            node.setOnKeyPressed(my);
        }
    }


    /*
     Bu Listener componentteki harfleri büyütür. ve trimle metnin ön ve arkasındaki boşlukları siler
     böylece kullanıcıdan kaynaklı ileride arama vs de sorun oluşturabilecek gereksiz boşluklar yok edilir.
     @param nodes Textfield dizini
     */
    protected void addStrFocusLostListener(Node[] nodes) {
        for (Node node : nodes) {
            node.focusedProperty().addListener((ov, oldV, newV) -> {
                if (node instanceof TextField)
                    if (!newV) { // focus lost
                        ((TextField) node).setText(((TextField) node).getText().trim().toUpperCase());
                    }
            });
        }
    }


    /*
     Bu metod mevcut kullanıcının dahil olduğu yetki grubuna göre Create, Update, Delete yetkisinin olup olmadığına bakar ve eğer
     yetki verilmemişse false ise butonu görünmez yapar
     @param zoneName: check edilecek bölüm adı zone tablosundan bir ad olmalı
     */
    protected void setButtonsVisibleAccordingToPermission(String zoneName) {
        if (LoginController.getActiveUser() == null) return;//eğer null ise SuperAdmindir o yüzden tamamını göster
        new MyPermisions().checkPermissionsForThisNodes(new Node[]{btnSave}, new String[]{zoneName}, "c");
        new MyPermisions().checkPermissionsForThisNodes(new Node[]{btnUpdate}, new String[]{zoneName}, "u");
        new MyPermisions().checkPermissionsForThisNodes(new Node[]{btnDelete}, new String[]{zoneName}, "d");
    }


    public void changeButtonsDisablingStateTo(boolean state) {
        try {
            this.btnSave.setDisable(state);
            this.btnUpdate.setDisable(!state);
            this.btnDelete.setDisable(!state);
            this.btnCancel.setDisable(!state);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }




    /**
     CRUD İŞLEMLERİ
     Template Pattern Desing kullanıldı
     @param crudI  : CrudI'ye implement eden CrudImpDelete,CrudImpSave,CrudImpUpdate sınıflarından biridir. Yani save, delete, update işlemlerini yapmaktadır
     @param opType : tek amacı Log kaydederken işlemin türünü kaydetmesi içindir. "SAVE", "DELETE", "UPDATE" string değerlerinden birini alır
     */
    private void executeCrudRequest(CrudI crudI, OpType opType, boolean isNeedRefresh) {
        String oldEntity = entityInterface == null ? null : entityInterface.toString();

        fromForm();

        final EntityInterface currentEntity = getEntityInterface();
        final long islemTarihi = new MyDate(new Date()).getMyDateAsLong();
        final Users islemiYapan = LoginController.getActiveUser();

        if (currentEntity instanceof CommonEntity) {
            switch (opType) {
                case SAVE:
                    ((CommonEntity) currentEntity).setCreateTime(islemTarihi);
                    ((CommonEntity) currentEntity).setWhoCreate(islemiYapan);
                    break;
                case DELETE:
                    ((CommonEntity) currentEntity).setDelTime(islemTarihi);
                    ((CommonEntity) currentEntity).setWhoDel(islemiYapan);
                    break;
                case UPDATE:
                    ((CommonEntity) currentEntity).addUp(islemiYapan, islemTarihi);
                    break;
            }
        }
        EntityInterface tmp = (EntityInterface) crudI.islemiGerceklestir(currentEntity, oldEntity, getDaoRepositoryImp());
        if (tmp != null) {
            changeButtonsDisablingStateTo(false);
            if (isNeedRefresh) refreshViewer(opType);
            clearFormFields();
            setEntityInterface(getNewBlankEntity());
        }
    }


    /*
     işlemden önce hata kontrolü yapılır ve sonra false dönerse executeCrudRequest metoduna işlem talepli gönderilir.
     */
    @FXML
    public void save() {
        if (!isErrorBeforeDatabase(OpType.SAVE))
            executeCrudRequest(new CrudImpSave(), OpType.SAVE, true);
    }


    @FXML
    protected void delete() {
        if (Confirm.isConfirmed(1, getEntityInterface().toString(), DELETE))//burada silinecek kayıt gönderilebilse harika olur
            executeCrudRequest(new CrudImpDelete(), DELETE, true);
    }

    @FXML
    protected void update() {
        update(true);
    }

    protected void update(boolean isNeedRefresh) {
        if (!isErrorBeforeDatabase(OpType.UPDATE))
            executeCrudRequest(new CrudImpUpdate(), OpType.UPDATE, isNeedRefresh);
    }

    @FXML
    protected void cancel() {
        setEntityInterface(getNewBlankEntity());
        changeButtonsDisablingStateTo(false);
        clearFormFields();
    }

    /*
     Bu fonksiyon eğer comboboxa otomatik tamamlama için MyAutoCompleteListener sınıfı kullanması için yazıldı
     çünkü EDITABLE modda olan comboboxın value değeri alınamıyor

     @param comboBox value değeri alınacak combobox
     @param <T>      Entity türü (Ex:String,Integer,MalzemeCinsi)
     @return geri dönen değer ise combodaki entitidir.
     */
    protected <T> T getComboBoxValue(ComboBox<T> comboBox) {
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            return null;
        } else {
            return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
        }
    }

}

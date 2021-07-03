package utility.listView;

import com.jfoenix.controls.JFXListView;
import daolar.CommonButtons;
import model.base.EntityInterface;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utility.enums.OpType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public abstract class List_View extends CommonButtons implements Initializable {


    @FXML
    protected JFXListView<Object> listView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateListView();
        setButtonsVisibleAccordingToPermission(getActiveModulo().getDescription());
    }




    private void populateListView() {
        ListProperty<Object> listProperty = new SimpleListProperty<>();
        try {
            List tmpList = getDaoRepositoryImp().getAll();
            listView.itemsProperty().bind(listProperty);
            listProperty.set(FXCollections.observableArrayList(tmpList));
        } catch (Exception e) {
            e.printStackTrace();
            //ErrorMessage.CANT_FILL_THE_LIST.printErrorMessages(e.getMessage());
        }
    }




    @FXML
    protected void listView_ClickOn() {
        Object tmpEntity = listView.getSelectionModel().getSelectedItem();//listView_GetCurrent();
        if (tmpEntity != null) {
            setEntityInterface((EntityInterface) tmpEntity);
            toForm();
            changeButtonsDisablingStateTo(true);
        } //else ErrorMessage.NULL_ENTITY.printErrorMessages("AbstractClass sınıfı listView_ClickOn fonksiyonu ");
    }




    public void refreshViewer(OpType opType) {
        List_ViewImpl list_viewImpl = new List_ViewImpl();
        list_viewImpl.guncelle(listView, getEntityInterface(), opType);
    }
}

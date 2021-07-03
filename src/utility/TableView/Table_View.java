package utility.TableView;

import daolar.CommonButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.base.CommonEntity;
import model.base.EntityInterface;
import utility.MyPredicateCreator;
import utility.enums.OpType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public abstract class Table_View<T extends EntityInterface> extends CommonButtons implements Initializable {

    protected abstract void comboboxlariDoldur();

    protected abstract DesiredField[] istenenAlanlariOlustur();

    public abstract MyPredicateCreator[] filtrelemePredicateleriniOlustur();

    @FXML
    protected TableView<T> tableView;

    @FXML
    public void populateTableCells(DesiredField[] desiredFields) {  //
        tableView.getColumns().clear();
        new TableViewHelper().createColumns(getEntityInterface(), desiredFields, tableView);
        MyPredicateCreator[] mp = filtrelemePredicateleriniOlustur();
        ObservableList<T> liste = getDataFromDatabase(mp);
        tableView.setItems(liste);
    }

    //parametrelere göre veri tabanından data çekerek filtreler
    public ObservableList<T> getDataFromDatabase(MyPredicateCreator... parameters) {
        List<T> liste = getDaoRepositoryImp().getAll(parameters);
        return liste == null ? null : FXCollections.observableArrayList(liste);
    }

    protected void refreshViewer(OpType opType) {
        populateTableCells(istenenAlanlariOlustur());// hızı etkileyip etkilemediğine bak
        tableView.refresh();
    }


    @FXML
    void tableViewYukariAsagi(KeyEvent event) {  //klavyenin yukarı ve aşağı ok tuşlarıyla tableviewde gezinmek için keyRelease olayı
        if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.DOWN))
            updateEntityFromSelectionModel(null);
    }

    @FXML
    public void updateEntityFromSelectionModel(MouseEvent event) {
        //tableViewYukariAsagi fonksiyonu tarafından çağrılmaktadır
        //ayrıca tabloya clicklenince scene builder çağırmaktadır.
        final Object tmpEntity = tableView.getSelectionModel().getSelectedItem();    //listView_GetCurrent();
        if (tmpEntity != null) {
            setEntityInterface((EntityInterface) tmpEntity);
            toForm();
            changeButtonsDisablingStateTo(true);
        }

        //double click olayını yakalama
        if(event==null || tmpEntity==null|| !(tmpEntity instanceof CommonEntity))return;
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            showDetays((CommonEntity) tmpEntity);
        }

    }

    private void showDetays(CommonEntity tmpEntity){
       //ileride tablolarda çift kliklendiğinde kaydın detayını gösterecek metod
        }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxlariDoldur();
        setButtonsVisibleAccordingToPermission(getActiveModulo().getDescription());
        populateTableCells(istenenAlanlariOlustur());
    }
}
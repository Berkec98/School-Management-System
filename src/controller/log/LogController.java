package controller.log;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.event.EventTableDesc;
import model.event.EventType;
import model.event.LOG_View;
import model.user.Users;
import utility.ComboboxDoldur;
import utility.MyDate;
import utility.MyPredicateCreator;
import utility.TableView.DFHelper;
import utility.TableView.DesiredField;
import utility.TableView.Table_View;
import utility.enums.CommandTipi;
import utility.enums.OpType;
import utility.enums.WPATH;
import utility.error.ErrorMessage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class LogController extends Table_View<LOG_View> {

    @FXML
    private Text lblViewerSize;
    @FXML
    private DatePicker datePickerFirst;
    @FXML
    private DatePicker datePickerSecond;
    @FXML
    private ComboBox<String> cmbPeriod;
    @FXML
    private ComboBox<Users> cmbUsers;
    @FXML
    private ComboBox<EventTableDesc> cmbBolum;
    @FXML
    private ComboBox<EventType> cmbCrud;

    public LogController() {
        changeState(WPATH.log);
    }


    protected void comboboxlariDoldur() {
        ComboboxDoldur.comboboxPopulate(cmbPeriod, "Son 1 Saat", "Son 1 Gün", "Son 1 Hafta", "Son 1 Ay", "Son 1 Yıl");
        ComboboxDoldur.comboboxPopulateFromDao(cmbCrud, EventType.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbBolum, EventTableDesc.class);
        ComboboxDoldur.comboboxPopulateFromDao(cmbUsers, Users.class);
    }


    protected DesiredField[] istenenAlanlariOlustur() {//Not: buradaki fieldnameler lOG_View entitisinin isimleri
        DFHelper dh = new DFHelper(new LOG_View());
        DesiredField[] df = dh.buFieldleriOlustur("username", "eventName", "tableName", "eventDateTARIH", "eventDateSAAT", "currentdata", "olddata");
        dh.fieldBasliklariSunlarOlsun("Kullanıcı Adı", "Yapılan İşlem", "Tablo Adı", "Tarihi", "Saati", "Yeni Data", "Eski Data");
        df[5].setMaxWidth(350);
        df[6].setMaxWidth(350);
        return df;
    }


    @Override
    public MyPredicateCreator[] filtrelemePredicateleriniOlustur() {
        final EventType crudValue = cmbCrud.getValue(); //kullanıcının seçim yapmama yani nesnenin null durumu yakalanıyor, null kontrolu yapılacak. NULLPOINTER yememek için
        //ve aşağıda (MyPredicateCreator satırında)ise null değilse fielde ulaşılmaya çalışıyor
        final MyPredicateCreator crudPredicate = new MyPredicateCreator("eventName", crudValue == null ? null : crudValue.getEventName(), CommandTipi.Equal);//not null field gönder
        final EventTableDesc tables = cmbBolum.getValue();
        final MyPredicateCreator bolumPredicate = new MyPredicateCreator("tableName", tables == null ? null : tables.getTableName(), CommandTipi.Equal);
        final Users user = cmbUsers.getValue(); //USER
        final MyPredicateCreator userPredicate = new MyPredicateCreator("username", user == null ? null : user.getUserName(), CommandTipi.Equal);

        //datepicker predicate
        MyPredicateCreator datePickerPredicate = null; //tarihler seçilmemişsse fonksiyona null olarak gitmesini sağlayacağım
        if (datePickerFirst.getValue() != null && datePickerSecond.getValue() != null) { //null değillerse çalışsın
            datePickerPredicate = new MyPredicateCreator("eventDate", new MyDate(datePickerFirst.getValue()).getMyDateAsLong(), new MyDate(datePickerSecond.getValue()).getMyDateAsLong());

        }

        //period predicate
        MyPredicateCreator periodPredicate = null;
        LocalDateTime simdikiZaman = LocalDateTime.now();
        LocalDateTime oncekiZaman = LocalDateTime.now();
        ;
        if (cmbPeriod.getValue() != null) {
            switch (cmbPeriod.getValue()) {
                case "Son 1 Saat":
                    oncekiZaman = simdikiZaman.minusHours(1);
                    break;
                case "Son 1 Gün":
                    oncekiZaman = simdikiZaman.minusDays(1);
                    break;
                case "Son 1 Hafta":
                    oncekiZaman = simdikiZaman.minusDays(7);
                    break;
                case "Son 1 Ay":
                    oncekiZaman = simdikiZaman.minusMonths(1);
                    break;
                case "Son 1 Yıl":
                    oncekiZaman = simdikiZaman.minusYears(1);
                    break;
            }

            datePickerPredicate = new MyPredicateCreator("eventDate", new MyDate(oncekiZaman).getMyDateAsLong(), new MyDate(simdikiZaman).getMyDateAsLong());
        }
        //özel durum var eğer hem datepicker seçilmiş hem de period seçilirse datepicker görmezden gelinir
        //bu durumu disabledlerle kontrol altına al
        return new MyPredicateCreator[]{crudPredicate, bolumPredicate, userPredicate, datePickerPredicate};
    }

    private int tabloElemanSayisi() {
        ObservableList<LOG_View> liste =  tableView.getItems();
        return liste== null ? 0 :liste.size();
    }
    @FXML
    public void populateTableCells() { //GOSTER butonu actionu
        populateTableCells(istenenAlanlariOlustur());
        lblViewerSize.setText("Gösterilen Kayıt: "+String.valueOf(tabloElemanSayisi())+" Adet");
    }

    @Override
    protected void fromForm() {

    }

    @Override
    protected void toForm() {

    }

    @Override
    protected void clearFormFields() {

    }

    @Override
    protected boolean isErrorBeforeDatabase(OpType opType) {
        return false;
    }

    @FXML
    void showLogDetails(MouseEvent event) {
        final LOG_View log_view = this.tableView.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && (log_view != null)) {
            try {
               ///logların detayları gösterilecektir
            } catch (Exception e) {
                ErrorMessage.IO_FORM_LOAD.printErrorMessages("show log detay:", e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboboxlariDoldur();
    }

}
package utility.TableView;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.listeners.MyStringCellFactory;

class TableViewHelper {


    void createColumns(Object t, DesiredField[] desiredFields, TableView tableView) {
        final int kolonsayisi = desiredFields.length;
        final TableColumn[] kolonDizini = new TableColumn[kolonsayisi];
        for (int i = 0; i < kolonsayisi; i++) {
            if(desiredFields[i].isHide())continue;
            kolonDizini[i] = new TableColumn<>(desiredFields[i].getFieldTitle());                                //kullanıcıdan gelen kolon adlarıyla TableColumn lar oluşturuluyor
            tableView.getColumns().add(kolonDizini[i]);                                                         // Kolon tabloya ekleniyor
            if (desiredFields[i].getMaxWidth() != 0)
                kolonDizini[i].setMaxWidth(desiredFields[i].getMaxWidth());
            if (desiredFields[i].getMinWidth() != 0)
                kolonDizini[i].setMaxWidth(desiredFields[i].getMinWidth());
            if (desiredFields[i].getDateTimeFormat() != null) {
                kolonDizini[i].setCellFactory(new LocalDateCellFactory(desiredFields[i].getDateTimeFormat()));  //tarih ve saat için sütunun formatını değiştirir
            }
            if (desiredFields[i].isFormatOndalik()) {                                                               //para birimi
                kolonDizini[i].setCellFactory(new MyStringCellFactory());
            }

           // if(t instanceof Identity)
          //  kolonDizini[i].setCellFactory(new ColoringCellFactory<EntityInterface>()); //bu çalışıyor ancak tüm kolonlara yaptığından entitiler sorun oluyor

            kolonDizini[i].setCellValueFactory(new PropertyValueFactory<>(desiredFields[i].getField().getName()));  //kolonlara data dolduruluyor
        }
    }



}

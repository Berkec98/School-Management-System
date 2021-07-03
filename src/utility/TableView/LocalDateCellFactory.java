package utility.TableView;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import utility.MyDate;


public class LocalDateCellFactory<T> implements Callback<TableColumn<T, Long>, TableCell<T, Long>> {
    private String format;

    LocalDateCellFactory(String format) {
        this.format = format;
    }
    //private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    @Override
    public TableCell<T, Long> call(final TableColumn<T, Long> column) {
        return new TableCell<T, Long>() {

            @Override
            protected void updateItem(final Long value, final boolean empty) {
                super.updateItem(value, empty);
                setText(getCellText(value, empty));
            }
        };
    }


    private String getCellText(final Long value, final boolean empty) {
        MyDate md = new MyDate(value);
        return empty || value == null ||value ==0 ? null : md.getMyDateAsString(this.format);
    }

}
